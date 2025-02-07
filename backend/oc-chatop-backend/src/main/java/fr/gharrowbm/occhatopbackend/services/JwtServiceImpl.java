package fr.gharrowbm.occhatopbackend.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${chatop.app.jwtSecret}")
    private String jwtSecret;

    @Value("${chatop.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generate(String email) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        String token = Jwts.builder()
                .subject(email)
                .issuer("Chatop App")
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(secretKey)
                .compact();

        return token;
    }

    @Override
    public boolean validate(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public String extractEmail(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
