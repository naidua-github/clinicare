package com.techstomach.justdental.model.service;

import com.google.gson.Gson;

public class RoleResp {
    private Integer roleId;
    private String name;
    private String description;

    public RoleResp() {
    }
    public RoleResp(DbRequest dbRequest) {
        this.name = dbRequest.getName();
        this.description = dbRequest.getDescription();
        this.roleId = dbRequest.getRoleId();
    }

    public String toJson(){
        Gson gson = new Gson();
        String patientJson = gson.toJson(this);

        return patientJson;
    }


    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
