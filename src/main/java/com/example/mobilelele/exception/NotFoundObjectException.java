package com.example.mobilelele.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundObjectException extends RuntimeException{
    public NotFoundObjectException(String message) {
        super(message);
    }
}
