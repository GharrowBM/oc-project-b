package fr.gharrowbm.occhatopbackend.services;

public interface JwtService {
    String generate(String email);
    boolean validate(String token);
    String extractEmail(String token);
}
