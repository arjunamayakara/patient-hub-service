package com.hub.patient.app.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PatientDTO implements Serializable {
    private long id;
    private String name;
    private int age;
    private String gender;
    private String disease;
}
