package com.jsp.ecommerce.serviceimpl;

import java.util.Date;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.ecommerce.cache.CacheStore;
import com.jsp.ecommerce.entity.Customer;
import com.jsp.ecommerce.entity.Seller;
import com.jsp.ecommerce.entity.User;
import com.jsp.ecommerce.exceptions.DuplicateEmailException;
import com.jsp.ecommerce.exceptions.InvalidOTPException;
import com.jsp.ecommerce.exceptions.UserSessionExpiredException;
import com.jsp.ecommerce.exceptions.WrongOTPException;
import com.jsp.ecommerce.repository.CustomerRepo;
import com.jsp.ecommerce.repository.SellerRepo;
import com.jsp.ecommerce.repository.UserRepo;
import com.jsp.ecommerce.requestdto.OTPModel;
import com.jsp.ecommerce.requestdto.UserRequestDTO;
import com.jsp.ecommerce.responsedto.UserResponseDTO;
import com.jsp.ecommerce.service.AuthServiceI;
import com.jsp.ecommerce.util.MessageStructure;
import com.jsp.ecommerce.util.ResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthServiceI {

	private UserRepo userRepo;

	private SellerRepo sellerRepo;

	private CustomerRepo customerRepo;

	private PasswordEncoder passwordEncoder;

	private ResponseStructure<UserResponseDTO> responseStructure;

	private CacheStore<String> otpCacheStore;

	private CacheStore<User> userCacheStore;

	private JavaMailSender javaMailSender;// bean provided by spring it will comes with the dependency only

	public UserResponseDTO mapToResponse(User user) {
		return UserResponseDTO.builder().email(user.getEmail()).userId(user.getUserId()).isDeleted(user.isDeleted())
				.isEmailVerified(user.isEmailVerified()).userName(user.getUserName()).userRole(user.getUserRole())
				.build();
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDTO>> register(UserRequestDTO userRequestDTO) {

		if (userRepo.existsByEmail(userRequestDTO.getEmail()))
			throw new DuplicateEmailException(" Duplicate Email!!!");
		String OTP = randomOTP();

		User user = mapToUser(userRequestDTO);

		userCacheStore.add(userRequestDTO.getEmail(), user);
		otpCacheStore.add(userRequestDTO.getEmail(), OTP);

		try {
			sendOTPToMail(user, OTP);
		} catch (MessagingException e) {
			log.error(" the email address is doesnot exist ");
		}

		return new ResponseEntity<ResponseStructure<UserResponseDTO>>(responseStructure.setData(mapToResponse(user))
				.setMessage("please verify  the OTP is already sent to your mail ID ")
				.setStatus(HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
	}

	@SuppressWarnings("unchecked")
	public <T extends User> T saveUser(User user) {
		User user2 = null;
		if (user instanceof Seller)
			user2 = sellerRepo.save((Seller) user);
		else if (user instanceof Customer)
			user2 = customerRepo.save((Customer) user);

		return (T) user2;
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDTO>> verifyOTP(OTPModel otpModel) {

		User user = userCacheStore.get(otpModel.getEmail());
		String OTP = otpCacheStore.get(otpModel.getEmail());

		if (OTP == null)
			throw new InvalidOTPException("invalid OTP !!!");
		if (user == null)
			throw new UserSessionExpiredException(" empty user not allowed here !!!");

		if (!OTP.equals(otpModel.getOtp()))
			throw new WrongOTPException("wrong otp ");
		else {
			responseStructure.setData(mapToResponse(userRepo.save(user)));
			responseStructure.setMessage(" user successfully added ");
			responseStructure.setStatus(HttpStatus.OK.value());
			try {
				sendResponseMessage(user);
			} catch (MessagingException e) {
				log.error(" confirmation  mail  sent successfully   ");
			}

		}

		return new ResponseEntity<ResponseStructure<UserResponseDTO>>(responseStructure, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	private <T extends User> T mapToUser(UserRequestDTO userRequestDTO) {
		User user = null;
		switch (userRequestDTO.getUserRole()) {
		case SELLER -> {
			user = new Seller();
		}
		case CUSTOMER -> {
			user = new Customer();
		}
		}

		user.setEmail(userRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
		user.setUserRole(userRequestDTO.getUserRole());
		user.setUserName(user.getEmail().split("@")[0]);
		return (T) user;
	}

	private String randomOTP() {
//		return (int) (Math.random() * 900000) + 100000+"";
		return String.valueOf(new Random().nextInt(100000, 999999));
	}

	@Async
	private void sendMail(MessageStructure message) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // to send the mail in theform of HTML pages or
																		// any

		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		messageHelper.setTo(message.getTo());
		messageHelper.setSubject(message.getSubject());
		messageHelper.setSentDate(message.getSentDate());
		messageHelper.setText(message.getText(), true); // set true is becuasse i am sending the HTML code along with
														// the message
		javaMailSender.send(mimeMessage);
	}

	private void sendOTPToMail(User user, String otp) throws MessagingException {
		sendMail(MessageStructure.builder().to(user.getEmail()).subject("Complete Your Resgistration To FlipKart")
				.sentDate(new Date())
				.text("hey  , " + user.getUserName() + " Good to see you intreseted in flipkart, "
						+ " Complete your regetration using the OTP <br>" + " <h1>" + otp + "<h1> <br>"
						+ " Note: the OTP expires in 1 minutes " + "<br> <br>" + " with best regards <br>" + " FlipKart"

				).build());

	}

	private void sendResponseMessage(User user) throws MessagingException {
		sendMail(MessageStructure.builder().subject("Regestration completed !!!").to(user.getEmail())
				.sentDate(new Date())
				.text("welcome to flipkart Application ... <br>  you have successfully logged in to the"
						+ " <style color='red' > FlipKart <style/> Application  ")
				.build());
	}

}