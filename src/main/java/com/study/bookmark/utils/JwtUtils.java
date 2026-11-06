package com.study.bookmark.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private final String SECRET = "my_secret_key_for_job_tracker_1234567890";
    private final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    private static final long EXPIRATION_TIME = 24 * 60 * 1000L;

    public String generateToken(Long userId, String username) {
        return Jwts.builder().claim("userId", userId).claim("username", username)
                .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY).compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload();
    }
}
