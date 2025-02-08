package fr.gharrowbm.occhatopbackend.controllers;

import fr.gharrowbm.occhatopbackend.exceptions.ChatopUserNotFoundException;
import fr.gharrowbm.occhatopbackend.exceptions.RentalNotFoundException;
import fr.gharrowbm.occhatopbackend.exceptions.UserEmailIsAlreadyTakenException;
import fr.gharrowbm.occhatopbackend.models.BaseExceptionResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
@Hidden
public class ErrorController {
    @ExceptionHandler(ChatopUserNotFoundException.class)
    public ResponseEntity<BaseExceptionResponse> handleChatopUserNotFoundException(ChatopUserNotFoundException e) {
        return ResponseEntity.status(404).body(new BaseExceptionResponse(
                e.getMessage(),
                new Date().toString(),
                "path",
                404
        ));
    }

    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseEntity<BaseExceptionResponse> handleRentalNotFoundException(RentalNotFoundException e) {
        return ResponseEntity.status(404).body(new BaseExceptionResponse(
                e.getMessage(),
                new Date().toString(),
                "path",
                404
        ));
    }

    @ExceptionHandler(UserEmailIsAlreadyTakenException.class)
    public ResponseEntity<BaseExceptionResponse> handleUserEmailIsAlreadyTakenException(UserEmailIsAlreadyTakenException e) {
        return ResponseEntity.status(400).body(new BaseExceptionResponse(
                e.getMessage(),
                new Date().toString(),
                "path",
                400
        ));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<BaseExceptionResponse> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(401).body(new BaseExceptionResponse(
                e.getMessage(),
                new Date().toString(),
                "path",
                401
        ));
    }
}
