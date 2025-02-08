package fr.gharrowbm.occhatopbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ChatopUserNotFoundException extends RuntimeException {
    public ChatopUserNotFoundException(String message) {
        super(message);
    }
}
