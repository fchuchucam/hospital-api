package com.hospital.hospital_api.controller;

import com.hospital.hospital_api.dto.AppointmentDTO;
import com.hospital.hospital_api.dto.CreateAppointmentRequest;
import com.hospital.hospital_api.model.Appointment;
import com.hospital.hospital_api.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<AppointmentDTO> getAll(){
        return service.getAll().stream().map(ap ->
                new AppointmentDTO(ap.getId(),
                                ap.getPatient().getName(),
                                ap.getDoctor().getName(),
                                ap.getDoctor().getSpecialty(),
                                ap.getDateTime()))
                .collect(Collectors.toList());
    }

    /*public List<Appointment> getAll() {
        return service.getAll();
    }*/

    @GetMapping("/{id}")
    public Appointment getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public AppointmentDTO create(@RequestBody CreateAppointmentRequest req) {
        Appointment ap = service.create(
                req.getPatientId(),
                req.getDoctorId(),
                LocalDateTime.parse(req.getDateTime())
        );
        return new AppointmentDTO(
                ap.getId(),
                ap.getPatient().getName(),
                ap.getDoctor().getName(),
                ap.getDoctor().getSpecialty(),
                ap.getDateTime());
    }

    /*public Appointment create(@RequestParam Long patientId,
                              @RequestParam Long doctorId,
                              @RequestParam String dateTime) {
        return service.create(patientId, doctorId, LocalDateTime.parse(dateTime));
    }*/

    @PutMapping("/{id}")
    public Appointment update(@PathVariable Long id, @RequestParam String dateTime) {
        return service.update(id, LocalDateTime.parse(dateTime));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
