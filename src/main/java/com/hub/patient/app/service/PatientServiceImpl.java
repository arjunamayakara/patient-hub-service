package com.hub.patient.app.service;

import com.hub.patient.app.dto.PatientDTO;
import com.hub.patient.app.entity.Patient;
import com.hub.patient.app.exceptions.PatientNotFoundException;
import com.hub.patient.app.repository.PatientRepository;
import com.hub.patient.app.util.PatientTransformerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;
    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Cacheable(value = "patients", key = "#id")
    @Override
    public PatientDTO getPatient(long id) {
        log.info("getPatient inside patientServiceImpl");
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.map(PatientTransformerUtil::convertToDTO).orElseThrow(() -> new PatientNotFoundException("No patient found with id "+id));
    }


    @CacheEvict(value = "patients", allEntries = true)
    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        log.info("createPatient inside patientServiceImpl");
        Patient patient = patientRepository.save(PatientTransformerUtil.convertToEntity(patientDTO));
        return PatientTransformerUtil.convertToDTO(patient);
    }

    @CacheEvict(value = "patients", allEntries = true)
    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientDTO.getId());
        if (optionalPatient.isPresent()) {

            Patient patient = patientRepository.save(PatientTransformerUtil.convertToEntity(patientDTO));
            return PatientTransformerUtil.convertToDTO(patient);
        } else {
            throw new PatientNotFoundException("No patients found to update");
        }
    }

    @CacheEvict(value = "patients", allEntries = true)
    @Override
    public void deletePatient(long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            patientRepository.deleteById(id);
        } else {
            throw new PatientNotFoundException("No patients found to delete");
        }
    }

    @Cacheable(value = "patients")
    @Override
    public List<PatientDTO> getPatients() {
        log.info("inside getPatients serviceImpl");
        List<Patient> patients = patientRepository.findAll();
        if(patients.isEmpty()) throw new PatientNotFoundException("No patients found");
        return PatientTransformerUtil.convertToDTOList(patients);
    }

}
