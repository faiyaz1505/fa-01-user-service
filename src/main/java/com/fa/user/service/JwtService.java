package com.fa.user.service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkeymysecretkey";

    public String extractUsername(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(
                        Keys.hmacShaKeyFor(
                                SECRET.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
}
