package com.cabservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(code= BAD_REQUEST)
public class InvalidSeatingCapacityException extends RuntimeException{
    public InvalidSeatingCapacityException(String message) {
        super(message);
    }
}
