package com.hospital.hospital_api.repository;

import com.hospital.hospital_api.model.Appointment;
import com.hospital.hospital_api.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByDoctorAndDateTime(Doctor doctor, LocalDateTime dateTime);
}