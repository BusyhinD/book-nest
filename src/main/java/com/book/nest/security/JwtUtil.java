package com.book.nest.security;

import com.book.nest.dto.user.response.UserLoginResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final Key secret;
    @Value("${jwt.expiration}")
    private long expiration;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.secret = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public UserLoginResponseDto getToken(String login) {
        String token = Jwts
                .builder()
                .setSubject(login)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secret)
                .compact();
        return new UserLoginResponseDto(token);
    }

    public boolean isValid(String token) {
        try {
            Date expiration = getClaimFromToken(token, Claims::getExpiration);
            return expiration.after(new Date(System.currentTimeMillis()));
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Expired or invalid JWT");
        }
    }

    public String getLogin(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
