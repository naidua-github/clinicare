package com.techstomach.justdental.model.service;


public class Body {

    private Calendar calendar;
    private Patient patient;
    private Error error;

    public Error getError() {
        return error;
    }
    public Body setError(Error error) {
        this.error = error;
        return this;
    }
    public Patient getPatient() {
        return patient;
    }
    public Body setPatient(Patient patient) {
        this.patient = patient;
        return this;
    }
    public Calendar getCalendar() {
        return calendar;
    }
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
