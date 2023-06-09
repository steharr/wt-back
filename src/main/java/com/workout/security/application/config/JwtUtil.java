package com.workout.security.application.config;

import com.workout.security.infrastructure.repository.entity.AccountEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

public class JwtUtil {

    //    @Value("${jwt.secret}")
    private static String secret = "DCA32A83C1BC58AAF79327AA49857B9F3FDBCD7AF8F9BD8A9DA9D363CF5CC52B5231CCDACAEFBD686C247361187EFA77339D188E89E9464AB3DB";

    public static String parseToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    public static String generateToken(AccountEntity account) {
        Claims claims = Jwts.claims().setSubject(account.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static String generateToken(Authentication account) {
        Claims claims = Jwts.claims().setSubject((String) account.getPrincipal());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
