package com.shop.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class AuthFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokwnHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		 String requestToken = request.getHeader("Authorization");
	        
	        String username = null;
	        String token = null;
	        
	        if (requestToken != null && requestToken.startsWith("Bearer")) {
	            
	            token = requestToken.substring(7);
	            try {
	            	username = this.jwtTokwnHelper.extractUsername(token);
	            } catch (IllegalArgumentException | ExpiredJwtException | MalformedJwtException e) {
	                e.printStackTrace();
	            }
	                
	        } else {
	            System.out.println("Token not found or invalid format");
	        }
	        
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            
	            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	            if (token != null && this.jwtTokwnHelper.validateToken(token, userDetails)) {
	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities());
	                
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	                
	            } else {
	                System.out.println("Invalid token or user details");
	            }
	            
	        } else {
	            System.out.println("Username is null or authentication already exists");
	        }
	        
	        filterChain.doFilter(request, response);
			
	}

}
