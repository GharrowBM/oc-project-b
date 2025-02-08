package fr.gharrowbm.occhatopbackend.models;

public record BaseExceptionResponse(
        String message,
        String timestamp,
        String path,
        Long statusCode
) {
}
