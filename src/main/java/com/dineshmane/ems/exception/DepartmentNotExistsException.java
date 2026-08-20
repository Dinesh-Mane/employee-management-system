package com.dineshmane.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepartmentNotExistsException extends RuntimeException {
    public DepartmentNotExistsException(String message) {
        super(message);
    }
}
