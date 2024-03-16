package com.hub.patient.app.util;

import static org.junit.Assert.*;

import com.hub.patient.app.dto.PatientDTO;
import com.hub.patient.app.entity.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PatientTransformerUtilTest {

    @Test
    public void testConvertToDTO() {
        Patient patient = Patient.builder()
                .id(1L)
                .name("John Doe")
                .age(30)
                .disease("Fever")
                .gender("Male")
                .build();

        PatientDTO patientDTO = PatientTransformerUtil.convertToDTO(patient);

        assertEquals(patient.getId(), patientDTO.getId());
        assertEquals(patient.getName(), patientDTO.getName());
        assertEquals(patient.getAge(), patientDTO.getAge());
        assertEquals(patient.getDisease(), patientDTO.getDisease());
        assertEquals(patient.getGender(), patientDTO.getGender());
    }

    @Test
    public void testConvertToEntity() {
        PatientDTO patientDTO = PatientDTO.builder()
                .id(1L)
                .name("John Doe")
                .age(30)
                .disease("Fever")
                .gender("Male")
                .build();

        Patient patient = PatientTransformerUtil.convertToEntity(patientDTO);

        assertEquals(patientDTO.getId(), patient.getId());
        assertEquals(patientDTO.getName(), patient.getName());
        assertEquals(patientDTO.getAge(), patient.getAge());
        assertEquals(patientDTO.getDisease(), patient.getDisease());
        assertEquals(patientDTO.getGender(), patient.getGender());
    }

    @Test
    public void testConvertToDTOList() {
        List<Patient> patients = new ArrayList<>();
        patients.add(Patient.builder()
                .id(1L)
                .name("John Doe")
                .age(30)
                .disease("Fever")
                .gender("Male")
                .build());

        patients.add(Patient.builder()
                .id(2L)
                .name("Jane Doe")
                .age(25)
                .disease("Cough")
                .gender("Female")
                .build());

        List<PatientDTO> patientDTOs = PatientTransformerUtil.convertToDTOList(patients);

        assertEquals(patients.size(), patientDTOs.size());

        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            PatientDTO patientDTO = patientDTOs.get(i);

            assertEquals(patient.getId(), patientDTO.getId());
            assertEquals(patient.getName(), patientDTO.getName());
            assertEquals(patient.getAge(), patientDTO.getAge());
            assertEquals(patient.getDisease(), patientDTO.getDisease());
            assertEquals(patient.getGender(), patientDTO.getGender());
        }
    }
}
