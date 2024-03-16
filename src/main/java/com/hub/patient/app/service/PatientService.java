package com.hub.patient.app.service;

import com.hub.patient.app.dto.PatientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    PatientDTO getPatient(long id);
    PatientDTO createPatient(PatientDTO patientDTO);
    PatientDTO updatePatient(PatientDTO patientDTO);
    void deletePatient(long id);
    List<PatientDTO> getPatients();
}
