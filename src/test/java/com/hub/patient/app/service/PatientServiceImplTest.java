package com.hub.patient.app.service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.hub.patient.app.dto.PatientDTO;
import com.hub.patient.app.exceptions.PatientNotFoundException;
import com.hub.patient.app.entity.Patient;
import com.hub.patient.app.repository.PatientRepository;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;


    @Test
    void testGetPatient() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        PatientDTO result = patientService.getPatient(1L);

        assertNotNull(result);
        assertEquals(patient.getName(), result.getName());
    }

    @Test
    void testGetPatientNotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> {
            patientService.getPatient(1L);
        });
    }

    @Test
    void testCreatePatient() {
        PatientDTO patientDTO = PatientDTO.builder().name("John Doe").build();

        when(patientRepository.save(any(Patient.class))).thenReturn(new Patient());

        PatientDTO result = patientService.createPatient(patientDTO);

        assertNotNull(result);
    }

    @Test
    void testUpdatePatient() {
        PatientDTO patientDTO = PatientDTO.builder().id(1L).name("John Doe").build();

        when(patientRepository.findById(1L)).thenReturn(Optional.of(new Patient()));
        when(patientRepository.save(any(Patient.class))).thenReturn(new Patient());

        PatientDTO result = patientService.updatePatient(patientDTO);

        assertNotNull(result);
    }

    @Test
    void testUpdatePatientNotFound() {
        PatientDTO patientDTO = PatientDTO.builder().id(1L).name("John Doe").build();

        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> {
            patientService.updatePatient(patientDTO);
        });
    }

    @Test
    void testDeletePatient() {
        Patient patient = new Patient();
        patient.setId(1L);

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        patientService.deletePatient(1L);

        verify(patientRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletePatientNotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());


        assertThrows(PatientNotFoundException.class, () -> {
            patientService.deletePatient(1L);
        });
    }

    @Test
    void testGetPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());

        when(patientRepository.findAll()).thenReturn(patients);

        List<PatientDTO> result = patientService.getPatients();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetPatientsNotFound() {
        when(patientRepository.findAll()).thenReturn(new ArrayList<>());


        assertThrows(PatientNotFoundException.class, () -> {
            patientService.getPatients();
        });
    }
}
