package com.axioma.axiomatrainee.exceptions;

import com.axioma.axiomatrainee.security.JwtAuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception,
                                                                WebRequest request) {
        ApiError error = ApiError
                .builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .errorPath(request.getContextPath())
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(
                exception,
                error,
                new HttpHeaders(),
                error.getHttpStatus(),
                request
        );
    }

    @ExceptionHandler(value = {JwtAuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(JwtAuthenticationException exception,
                                                                WebRequest request) {
        ApiError error = ApiError
                .builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message(exception.getMessage())
                .errorPath(request.getContextPath())
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(
                exception,
                error,
                new HttpHeaders(),
                error.getHttpStatus(),
                request
        );
    }


}
