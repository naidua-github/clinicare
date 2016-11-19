package com.techstomach.justdental.model.service;

public class Patient {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Patient(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Patient(DbRequest dbRequest) {
        this.firstName = dbRequest.getFirstName();
        this.lastName = dbRequest.getLastName();
        this.phoneNumber = dbRequest.getPhoneNumber();
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
