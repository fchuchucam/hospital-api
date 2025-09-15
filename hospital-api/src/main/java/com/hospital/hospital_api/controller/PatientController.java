package com.hospital.hospital_api.controller;

import com.hospital.hospital_api.model.Patient;
import com.hospital.hospital_api.repository.PatientRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientRepository repo;

    // constructor injection (Spring will autowire repo)
    public PatientController(PatientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Patient> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        return repo.save(patient);
    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient updated) {
        Patient existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setDiagnosis(updated.getDiagnosis());
        return repo.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
