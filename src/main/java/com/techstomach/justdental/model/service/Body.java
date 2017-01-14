package com.techstomach.justdental.model.service;


public class Body {

    private CalendarResp calendar;
    private String patient;
    private String user;
    private String clinic;
    private String role;
    private Error error;

    public Body setError(Error error) {
        this.error = error;
        return this;
    }
    public Body setPatient(String patient) {
        this.patient = patient;
        return this;
    }
    public Body setPatient(PatientResp patient) {
        this.patient = patient.toJson();
        return this;
    }
    public Body setUser(UserResp user) {
        this.user = user.toJson();
        return this;
    }
    public Body setUser(String user) {
        this.user = user;
        return this;
    }
    public Body setClinic(String clinic) {
        this.clinic = clinic;
        return this;
    }
    public Body setClinic(ClinicResp clinicResp) {
        this.clinic = clinicResp.toJson();
        return this;
    }
    public Body setRole(RoleResp roleResp) {
        this.role = roleResp.toJson();
        return this;
    }
    public Body setRole(String role) {
        this.role = role;
        return this;
    }


    public Error getError() {
        return error;
    }
    public String getPatient() {
        return patient;
    }
    public CalendarResp getCalendar() {
        return calendar;
    }
    public String getUser() {
        return user;
    }
    public String getClinic() {
        return clinic;
    }
    public String getRole() {
        return role;
    }

    public void setCalendar(CalendarResp calendar) {
        this.calendar = calendar;
    }

}
