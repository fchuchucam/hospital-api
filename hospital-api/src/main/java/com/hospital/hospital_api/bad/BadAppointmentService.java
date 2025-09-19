package com.hospital.hospital_api.bad;

import com.hospital.hospital_api.model.Appointment;
import com.hospital.hospital_api.model.Doctor;
import com.hospital.hospital_api.model.Patient;
import com.hospital.hospital_api.repository.AppointmentRepository;
import com.hospital.hospital_api.repository.DoctorRepository;
import com.hospital.hospital_api.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ❌ BAD EXAMPLE:
 * - Mixes controller + service logic
 * - Talks directly to repositories
 * - No DTOs, raw entities exposed
 * - No validation
 * - Hard to test
 */
@RestController
@RequestMapping("/bad-appointments")
public class BadAppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public BadAppointmentService(AppointmentRepository appointmentRepo,
                                 DoctorRepository doctorRepo,
                                 PatientRepository patientRepo) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    @GetMapping
    public List<Appointment> all() {
        return appointmentRepo.findAll();
    }

    @PostMapping
    public Appointment create(@RequestParam Long patientId,
                              @RequestParam Long doctorId,
                              @RequestParam String dateTime) {
        Patient patient = patientRepo.findById(patientId).orElse(null);
        Doctor doctor = doctorRepo.findById(doctorId).orElse(null);

        Appointment ap = new Appointment();
        ap.setPatient(patient);
        ap.setDoctor(doctor);
        ap.setDateTime(LocalDateTime.parse(dateTime));

        return appointmentRepo.save(ap);
    }
}
