package com.hospital.hospital_api.controller;

import com.hospital.hospital_api.model.Doctor;
import com.hospital.hospital_api.repository.DoctorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepo;

    public DoctorController(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    // Get all doctors
    @GetMapping
    public List<Doctor> getAll() {
        return doctorRepo.findAll();
    }

    // Get doctor by id
    @GetMapping("/{id}")
    public Doctor getById(@PathVariable Long id) {
        return doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    // Create doctor
    @PostMapping
    public Doctor create(@RequestBody Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    // Update doctor
    @PutMapping("/{id}")
    public Doctor update(@PathVariable Long id, @RequestBody Doctor updated) {
        Doctor existing = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        existing.setName(updated.getName());
        existing.setSpecialty(updated.getSpecialty());
        return doctorRepo.save(existing);
    }

    // Delete doctor
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doctorRepo.deleteById(id);
    }

    // Get doctor's patients
    @GetMapping("/{id}/patients")
    public List<com.hospital.hospital_api.model.Patient> getPatients(@PathVariable Long id) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return doctor.getPatients();
    }
}
