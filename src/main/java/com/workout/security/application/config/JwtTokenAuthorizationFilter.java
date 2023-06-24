package com.workout.security.application.config;

import com.workout.security.application.AccountService;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private AccountService accountService;
    private static String secret = "DCA32A83C1BC58AAF79327AA49857B9F3FDBCD7AF8F9BD8A9DA9D363CF5CC52B5231CCDACAEFBD686C247361187EFA77339D188E89E9464AB3DB";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        if (token.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        String userToken = JwtUtil.parseToken(token);

        if (userToken == null) {
            throw new MalformedJwtException("Error!");
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(userToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (token != null) {
            return new UsernamePasswordAuthenticationToken(token, null, new ArrayList<>());
        }
        return null;
    }


}
