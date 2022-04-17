package com.william.yataco.testservice.domain.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DemoTemplateException extends RuntimeException{
    private final HttpStatus statusCode;
    private final String error;
    private final String message;

    public DemoTemplateException(final HttpStatus statusCode, String error, String message) {
        super(error);
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
    }
}
