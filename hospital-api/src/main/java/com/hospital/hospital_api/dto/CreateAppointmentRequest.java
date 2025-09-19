package com.hospital.hospital_api.dto;

public class CreateAppointmentRequest {
    private Long patientId;
    private Long doctorId;
    private String dateTime; // ISO 8601

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
}