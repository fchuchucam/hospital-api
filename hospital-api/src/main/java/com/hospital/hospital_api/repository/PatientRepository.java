package com.hospital.hospital_api.repository;


import com.hospital.hospital_api.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}