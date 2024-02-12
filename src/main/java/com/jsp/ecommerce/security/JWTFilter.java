package com.jsp.ecommerce.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jsp.ecommerce.entity.AccessToken;
import com.jsp.ecommerce.repository.AccessTokenRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

	private AccessTokenRepo accessTokenRepo;

	private JWTService jwtService;

	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String at = null, rt = null;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("accessToken"))
					at = cookie.getValue();
				if (cookie.getName().equals("refreshToken"))
					rt = cookie.getValue();
			}
			// both sould be present here or thorow exception
//		if (at == null || rt == null)
//		{
//			throw new UserNotLoggedInException("user not logged in !!!");
//
//		}
			String userName = null;
			if (at != null && rt != null) {
				Optional<AccessToken> accessToken = accessTokenRepo.findByTokenAndIsBlocked(at, false);

				if (accessToken == null)
					throw new RuntimeException("access token is null");

				else {
					userName = jwtService.extractUserName(at);
					if (userName == null)
						throw new RuntimeException();

					UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, null,
							userDetails.getAuthorities());
					token.setDetails(new WebAuthenticationDetails(request));
					SecurityContextHolder.getContext().setAuthentication(token);
				}
			}
		}
		filterChain.doFilter(request, response);// it will delegates the current filter to the main filter
	}
}