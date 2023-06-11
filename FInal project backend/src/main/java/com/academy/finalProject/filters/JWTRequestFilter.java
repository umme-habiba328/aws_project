package com.academy.finalProject.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.academy.finalProject.model.JWTUtil;
import com.academy.finalProject.repository.LoggedInUserRepository;
import com.academy.finalProject.service.CustomUserDetailsService;
import com.academy.finalProject.service.LoggedInUserService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter{
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtutil;

	@Autowired
	private LoggedInUserService loggedInUserService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("cookie: "+" " +request.getServletPath());
		//System.out.println(WebUtils.getCookie(request, "refreshToken").getValue());
		if(request.getServletPath().equals("/authenticate") || request.getServletPath().equals("/userLogout")  || request.getServletPath().equals("/refreshToken")) {
			filterChain.doFilter(request, response);
		}else {
			
			try {
				String username = null;
				
				String accessToken = WebUtils.getCookie(request, "accessToken").getValue();
				System.out.println("access: "+ accessToken);
				//String refreshToken = WebUtils.getCookie(request, "refreshToken").getValue();
				if(accessToken != null) {
					username = jwtutil.getUsernameFromToken(accessToken);
				}
				if(loggedInUserService.isAccessTokenRight(username, accessToken) && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
					if(jwtutil.validateToken(accessToken, userDetails)) {
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
								(userDetails.getUsername(),null,userDetails.getAuthorities());
						usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
					else {
						throw new IOException("Token is invalid");
					}
				}
			}catch(ExpiredJwtException ex) {
				
			}
			filterChain.doFilter(request, response);
		}
	}
}
