package com.inaya.collab.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddException extends RuntimeException{
    public AddException(String message) {
        super(message);
    }}
