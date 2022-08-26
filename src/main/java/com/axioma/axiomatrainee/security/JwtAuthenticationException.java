package com.axioma.axiomatrainee.security;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthenticationException extends AuthenticationException {

    private final HttpStatus httpStatus;

    public JwtAuthenticationException(String detail) {
        super(detail);
        this.httpStatus = HttpStatus.UNAUTHORIZED;
    }

}
