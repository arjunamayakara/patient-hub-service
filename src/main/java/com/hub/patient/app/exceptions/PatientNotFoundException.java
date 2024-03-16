package com.hub.patient.app.exceptions;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(String message){
        super(message);
    }
}
