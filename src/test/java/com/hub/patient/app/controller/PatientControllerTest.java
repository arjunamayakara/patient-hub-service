package com.hub.patient.app.controller;

import com.hub.patient.app.dto.PatientDTO;
import com.hub.patient.app.exceptions.BadRequestException;
import com.hub.patient.app.service.PatientService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {
    @InjectMocks
    private PatientController patientController;
    @Mock
    private PatientService patientService;


    @Test
    public void testGetPatient() {
        PatientDTO patientDTO = PatientDTO.builder().id(1).name("John Doe").age(30).build();

        when(patientService.getPatient(1)).thenReturn(patientDTO);

        assertEquals(patientDTO, patientController.getPatient(1));
    }

    @Test
    public void testGetPatients() {
        List<PatientDTO> patients = new ArrayList<>();
        PatientDTO patient1 = PatientDTO.builder().id(1).name("John Doe").age(30).build();
        patients.add(patient1);

        when(patientService.getPatients()).thenReturn(patients);

        assertEquals(patients, patientController.getPatients());
    }

    @Test
    public void testCreatePatient() {
        PatientDTO patientDTO = PatientDTO.builder().id(1).name("John Doe").age(30).build();

        when(patientService.createPatient(patientDTO)).thenReturn(patientDTO);

        assertEquals(patientDTO, patientController.createPatient(patientDTO));
    }

    @Test
    public void testUpdatePatient() {
        PatientDTO patientDTO = PatientDTO.builder().id(1).name("John Doe").age(30).disease("abcd").build();

        when(patientService.updatePatient(patientDTO)).thenReturn(patientDTO);
        PatientDTO patientDTO1 = patientController.updatePatient(patientDTO);
        assertEquals("abcd", patientDTO1.getDisease());
    }

    @Test
    public void testCreatePatientWithInvalidData() {
        PatientDTO patientDTO = PatientDTO.builder().build();

        assertThrows(BadRequestException.class, () -> {
            patientController.createPatient(patientDTO);
        });
    }

}
