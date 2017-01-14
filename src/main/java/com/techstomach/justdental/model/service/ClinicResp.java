package com.techstomach.justdental.model.service;

import com.google.gson.Gson;

public class ClinicResp {

    private Integer clinicId;
    private String clinicName;
    private String taxNumber;
    private String primaryContactName;
    private String primaryContactNumber;
    private int isActive;
    private String street1;
    private String street2;
    private String locality;
    private String city;
    private String state;
    private String country;
    private String gmap;
    private String pincode;

    public ClinicResp() {
    }
    public ClinicResp(DbRequest dbRequest) {
        this.clinicName = dbRequest.getClinicName();
        this.city = dbRequest.getCity();
        this.state = dbRequest.getState();
    }
    public String toJson(){
        Gson gson = new Gson();
        String patientJson = gson.toJson(this);

        return patientJson;
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
    public String getTaxNumber() {
        return taxNumber;
    }
    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }
    public String getPrimaryContactName() {
        return primaryContactName;
    }
    public void setPrimaryContactName(String primaryContactName) {
        this.primaryContactName = primaryContactName;
    }
    public String getPrimaryContactNumber() {
        return primaryContactNumber;
    }
    public void setPrimaryContactNumber(String primaryContactNumber) {
        this.primaryContactNumber = primaryContactNumber;
    }
    public int getIsActive() {
        return isActive;
    }
    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
    public String getStreet1() {
        return street1;
    }
    public void setStreet1(String street1) {
        this.street1 = street1;
    }
    public String getStreet2() {
        return street2;
    }
    public void setStreet2(String street2) {
        this.street2 = street2;
    }
    public String getLocality() {
        return locality;
    }
    public void setLocality(String locality) {
        this.locality = locality;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getGmap() {
        return gmap;
    }
    public void setGmap(String gmap) {
        this.gmap = gmap;
    }
    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
