package com.example.school_management_system.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String email, List<String> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 ساعة (بالمللي ثانية)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public String extractEmail(String token) {
        return getClaims(token).getSubject(); // Retrieve the email from the token subject
    }

    public List<String> extractRoles(String token) {
        return getClaims(token).get("roles", List.class); // Extract roles from the token
    }

    public boolean validateToken(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .setAllowedClockSkewSeconds(60) // السماح بانحراف زمني يصل إلى 60 ثانية
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
