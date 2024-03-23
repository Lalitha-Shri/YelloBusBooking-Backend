package com.lalitha.busReservationBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExist extends RuntimeException {
    String message;

    public EmailAlreadyExist(String message) {
        super(message);
        this.message = message;
    }
}
