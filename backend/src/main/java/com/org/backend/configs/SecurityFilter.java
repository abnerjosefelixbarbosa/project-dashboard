package com.org.backend.configs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.org.backend.interfaces.IToken;
import com.org.backend.repositories.AccountRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	 @Autowired
	 private IToken tokenMethods;
	 @Autowired
	 private AccountRepository accountRepository;

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
        var token = this.recoverToken(request);	
        if(token != null){
            var email = tokenMethods.validateToken(token);
            var user = accountRepository.findByUserEmail(email).orElseThrow(() -> {
    			throw new UsernameNotFoundException("Login not found");
    		});
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        filterChain.doFilter(request, response);
	}
	
	private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) { 
        	return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
