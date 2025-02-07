package fr.gharrowbm.occhatopbackend.services;

import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {
    public String generate() {
        return "";
    }

    @Override
    public boolean validate(String token) {
        return false;
    }

    @Override
    public String extractUsername(String token) {
        return "";
    }
}
