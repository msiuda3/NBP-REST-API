package com.example.NBPRESTService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResultNotFoundException extends ResponseStatusException {
    public ResultNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
