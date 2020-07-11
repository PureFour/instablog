package com.purefour.instablog.myprofile.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyFileException extends Exception {
    public EmptyFileException(String message) {
        super(message);
    }
}
