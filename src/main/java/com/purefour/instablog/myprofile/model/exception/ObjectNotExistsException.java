package com.purefour.instablog.myprofile.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotExistsException extends Exception {
    public ObjectNotExistsException(String message) {
        super(message);
    }
}
