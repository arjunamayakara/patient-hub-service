package com.hub.patient.app.util;

import com.hub.patient.app.dto.PatientDTO;
import com.hub.patient.app.entity.Patient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientTransformerUtil {

    public static PatientDTO convertToDTO(Patient patient) {
        return PatientDTO.builder().id(patient.getId())
                .name(patient.getName())
                .age(patient.getAge())
                .disease(patient.getDisease())
                .gender(patient.getGender())
                .build();
    }

    // Static method to convert a list of entities to a list of DTOs
    public static List<PatientDTO> convertToDTOList(List<Patient> entities) {
        return entities.stream()
                .map(PatientTransformerUtil::convertToDTO)
                .collect(Collectors.toList());
    }

    public static Patient convertToEntity(PatientDTO patient) {
        return Patient.builder().id(patient.getId())
                .name(patient.getName())
                .age(patient.getAge())
                .disease(patient.getDisease())
                .gender(patient.getGender())
                .build();
    }
}
