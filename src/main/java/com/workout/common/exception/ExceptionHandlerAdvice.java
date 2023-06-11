package com.workout.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity handleException(ApplicationException e) {
        return ResponseEntity.status(e.httpStatus()).body(e.getMessage());
    }
}
