package com.workout.security.application.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    //    @Value("${jwt.secret}")
    private static String secret = "test";

    public static boolean parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (JwtException | ClassCastException e) {
            return false;
        }
    }

    public static String generateToken() {
        Claims claims = Jwts.claims().setSubject("test");
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
