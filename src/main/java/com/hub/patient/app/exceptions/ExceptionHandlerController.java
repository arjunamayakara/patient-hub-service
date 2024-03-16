package com.hub.patient.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<?> handlePatientNotFoundException(PatientNotFoundException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(BadRequestException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
