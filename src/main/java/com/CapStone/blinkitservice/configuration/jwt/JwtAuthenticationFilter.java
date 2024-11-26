package com.CapStone.blinkitservice.configuration.jwt;

import com.CapStone.blinkitservice.auth.model.JwtAuthResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtManager jwtManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = extractToken(request);

        if (token == null && request.getRequestURI().startsWith("/auth/")) {  // skipping filter for public API
// TODO: Improve this code
            filterChain.doFilter(request, response);  // Continue without authentication

        } else if (token != null && jwtManager.validateToken(token)) {

           JwtAuthResponse jwtAuthResponse = jwtManager.getUserInfo(token);


            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    jwtAuthResponse.getEmail(), null, null);
               SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);    //to ensure request move to next filter in the security chain

        } else {

            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write("{\"Error\": \"Sorry, Invalid token\"}");

        }


    }

    private String extractToken(HttpServletRequest request) {

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;

    }
}