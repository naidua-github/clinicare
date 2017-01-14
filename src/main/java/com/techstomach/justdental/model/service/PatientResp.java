package com.techstomach.justdental.model.service;

import com.google.gson.Gson;
import com.techstomach.justdental.model.schema.Location;

public class PatientResp {

    private Integer patientId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String key;
    private String error;


    private Location location;
    private int middleName;
    private int gender;
    private int dateOfBirth;
    private int placeOfBirth;
    private int maritalStatus;
    private int bloodType;
    private int religion;
    private int occupation;
    private int education;
    private int insuranceNumber;
    private int insuranceExpiration;
    private int emergencyContactName;
    private int emergencyContactRelationship;
    private int emergencyContactNumber;
    private int creationDate;
    private int modifiedDate;
    private int modifiedBy;
    private int isActive;
    private int patientNote;
    private int emailAddress;

    public PatientResp() {
    }
    public PatientResp(Integer patientId, String firstName, String lastName, String phoneNumber, String key) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.key = key;
    }
    public PatientResp(DbRequest dbRequest) {
        this.firstName = dbRequest.getFirstName();
        this.lastName = dbRequest.getLastName();
        this.phoneNumber = dbRequest.getPhoneNumber();
    }

    public String toJson(){
        Gson gson = new Gson();
        String patientJson = gson.toJson(this);

        return patientJson;
    }

    public String getFirstName() {
        return firstName;
    }
    public PatientResp setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public String getLastName() {
        return lastName;
    }
    public PatientResp setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public PatientResp setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public String getKey() {
        return key;
    }
    public PatientResp setKey(String key) {
        this.key = key;
        return this;
    }
    public String getError() {
        return error;
    }
    public PatientResp setError(String error) {
        this.error = error;
        return this;
    }
    public Integer getPatientId() {
        return patientId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public int getMiddleName() {
        return middleName;
    }
    public void setMiddleName(int middleName) {
        this.middleName = middleName;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public int getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public int getPlaceOfBirth() {
        return placeOfBirth;
    }
    public void setPlaceOfBirth(int placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public int getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public int getBloodType() {
        return bloodType;
    }
    public void setBloodType(int bloodType) {
        this.bloodType = bloodType;
    }
    public int getReligion() {
        return religion;
    }
    public void setReligion(int religion) {
        this.religion = religion;
    }
    public int getOccupation() {
        return occupation;
    }
    public void setOccupation(int occupation) {
        this.occupation = occupation;
    }
    public int getEducation() {
        return education;
    }
    public void setEducation(int education) {
        this.education = education;
    }
    public int getInsuranceNumber() {
        return insuranceNumber;
    }
    public void setInsuranceNumber(int insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }
    public int getInsuranceExpiration() {
        return insuranceExpiration;
    }
    public void setInsuranceExpiration(int insuranceExpiration) {
        this.insuranceExpiration = insuranceExpiration;
    }
    public int getEmergencyContactName() {
        return emergencyContactName;
    }
    public void setEmergencyContactName(int emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }
    public int getEmergencyContactRelationship() {
        return emergencyContactRelationship;
    }
    public void setEmergencyContactRelationship(int emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
    }
    public int getEmergencyContactNumber() {
        return emergencyContactNumber;
    }
    public void setEmergencyContactNumber(int emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }
    public int getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }
    public int getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(int modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public int getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public int getIsActive() {
        return isActive;
    }
    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
    public int getPatientNote() {
        return patientNote;
    }
    public void setPatientNote(int patientNote) {
        this.patientNote = patientNote;
    }
    public int getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(int emailAddress) {
        this.emailAddress = emailAddress;
    }
}
