package com.hospital.hospital_api.dto;

import java.time.LocalDateTime;

public class AppointmentDTO {

    private Long id;
    private String patientName;
    private LocalDateTime dateTime;
    private String doctorName;
    private String doctorSpecialty;

    public AppointmentDTO(Long id, String patientName, String doctorName,
                          String doctorSpecialty, LocalDateTime dateTime){
        this.id=id;
        this.patientName=patientName;
        this.doctorName=doctorName;
        this.doctorSpecialty=doctorSpecialty;
        this.dateTime=dateTime;
    }

    public Long getId(){
        return id;
    }

    public String getPatientName() {
        return patientName;
    }
    public String getDoctorName() {
        return doctorName;
    }


    public String getDoctorSpecialty() {
        return doctorSpecialty;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
