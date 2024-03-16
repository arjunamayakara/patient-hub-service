package com.hub.patient.app.controller;

import com.hub.patient.app.dto.PatientDTO;
import com.hub.patient.app.exceptions.BadRequestException;
import com.hub.patient.app.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patient-hub/api/v1/patients")
@Slf4j
public class PatientController {


    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("{id}")
    public PatientDTO getPatient(@PathVariable long id) {
        return patientService.getPatient(id);
    }

    @GetMapping
    public List<PatientDTO> getPatients() {
        log.info("getPatients");
        return patientService.getPatients();
    }

    @PostMapping
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO) {
        log.info("Saving patient");
        validate(patientDTO);
        return patientService.createPatient(patientDTO);
    }

    @PutMapping
    public PatientDTO updatePatient(@RequestBody PatientDTO patientDTO) {
        log.info("Updating patient");
        validate(patientDTO);
        return patientService.updatePatient(patientDTO);
    }

    @DeleteMapping("{id}")
    public void deletePatient(@PathVariable("id") int id) {
        log.info("Deleting patient");
        patientService.deletePatient(id);
    }

    private void validate(PatientDTO patientDTO) {
        log.info("Validating patient");
        if(patientDTO.getAge() <= 0){
            throw new BadRequestException("Age should not be 0 or empty");
        } else if(!StringUtils.hasText(patientDTO.getName())) {
            throw new BadRequestException("Name should not be empty");
        }
    }
}