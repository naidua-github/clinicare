package com.techstomach.justdental.model.service;

public class Calendar {

    private String status;
    private Integer bookingId;
    private String fromDateTime;
    private String toDateTime;
    private Integer doctorId;
    private String doctorName;

    private Integer clinicId;
    private String clinicName;
    private String clinicAddress;
    private String pinDrop;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getBookingId() {
        return bookingId;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    public String getFromDateTime() {
        return fromDateTime;
    }
    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }
    public String getToDateTime() {
        return toDateTime;
    }
    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }
    public Integer getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public Integer getClinicId() {
        return clinicId;
    }
    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }
    public String getClinicName() {
        return clinicName;
    }
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
    public String getClinicAddress() {
        return clinicAddress;
    }
    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }
    public String getPinDrop() {
        return pinDrop;
    }
    public void setPinDrop(String pinDrop) {
        this.pinDrop = pinDrop;
    }
}
