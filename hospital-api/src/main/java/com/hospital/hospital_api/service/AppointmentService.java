package com.hospital.hospital_api.service;

import com.hospital.hospital_api.model.Appointment;
import com.hospital.hospital_api.model.Doctor;
import com.hospital.hospital_api.model.Patient;
import com.hospital.hospital_api.repository.AppointmentRepository;
import com.hospital.hospital_api.repository.DoctorRepository;
import com.hospital.hospital_api.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    public AppointmentService(AppointmentRepository appointmentRepo,
                              PatientRepository patientRepo,
                              DoctorRepository doctorRepo) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    public List<Appointment> getAll() {
        return appointmentRepo.findAll();
    }

    public Appointment getById(Long id) {
        return appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public Appointment create(Long patientId, Long doctorId, LocalDateTime dateTime) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Rule: doctor cannot have two appointments at same time
        appointmentRepo.findByDoctorAndDateTime(doctor, dateTime)
                .ifPresent(a -> {
                    throw new RuntimeException("Doctor already has an appointment at this time");
                });

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDateTime(dateTime);

        return appointmentRepo.save(appointment);
    }

    public Appointment update(Long id, LocalDateTime newDateTime) {
        Appointment existing = getById(id);

        // Validate doctor availability again
        appointmentRepo.findByDoctorAndDateTime(existing.getDoctor(), newDateTime)
                .ifPresent(a -> {
                    if (!a.getId().equals(id)) {
                        throw new RuntimeException("Doctor already has an appointment at this time");
                    }
                });

        existing.setDateTime(newDateTime);
        return appointmentRepo.save(existing);
    }

    public void delete(Long id) {
        appointmentRepo.deleteById(id);
    }
}

