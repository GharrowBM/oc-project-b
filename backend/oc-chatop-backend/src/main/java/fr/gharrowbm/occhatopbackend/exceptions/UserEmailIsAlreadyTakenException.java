package fr.gharrowbm.occhatopbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserEmailIsAlreadyTakenException extends RuntimeException {
    public UserEmailIsAlreadyTakenException(String email) {
        super("User with email: [" + email + "] is already taken!");
    }
}
