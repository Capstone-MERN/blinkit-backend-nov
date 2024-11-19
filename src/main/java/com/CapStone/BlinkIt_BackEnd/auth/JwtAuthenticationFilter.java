package com.CapStone.BlinkIt_BackEnd.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
@WebFilter("/api/*")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

//    private final JwtManager jwtManager;
//
//    public JwtAuthenticationFilter(JwtManager jwtManager) {
//        this.jwtManager = jwtManager;
//    }

    @Autowired
    JwtManager jwtManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = extractToken(request);

        if (token != null && jwtManager.validateToken(token)) {
            UserAuthResponse userAuthResponse = jwtManager.getUserInfo(token);

            //Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_" + userAuthResponse.getRole().toString());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userAuthResponse.getId(), null, null);
                                                                                //to create authentication object

            SecurityContextHolder.getContext().setAuthentication(authentication);  // stores logged in / curr session details
        }

        filterChain.doFilter(request, response);    //to ensure request move to next phase in the chain
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}