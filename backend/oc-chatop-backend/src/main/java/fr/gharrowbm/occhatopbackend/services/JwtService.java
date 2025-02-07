package fr.gharrowbm.occhatopbackend.services;

public interface JwtService {
    String generate();
    boolean validate(String token);
    String extractUsername(String token);
}
