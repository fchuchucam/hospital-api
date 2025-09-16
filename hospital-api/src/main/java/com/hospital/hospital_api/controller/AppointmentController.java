package com.hospital.hospital_api.controller;

import com.hospital.hospital_api.model.Appointment;
import com.hospital.hospital_api.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Appointment> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Appointment getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Appointment create(@RequestParam Long patientId,
                              @RequestParam Long doctorId,
                              @RequestParam String dateTime) {
        return service.create(patientId, doctorId, LocalDateTime.parse(dateTime));
    }

    @PutMapping("/{id}")
    public Appointment update(@PathVariable Long id, @RequestParam String dateTime) {
        return service.update(id, LocalDateTime.parse(dateTime));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
