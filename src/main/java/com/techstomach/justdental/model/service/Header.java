package com.techstomach.justdental.model.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Header {
    private int responseCode;
    private String responseStatus;
    private String timestamp;
    private String server;

    public Header(int responseCode, String responseStatus) {
        this.responseCode = responseCode;
        this.responseStatus = responseStatus;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
        this.timestamp = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        try
        {
            this.server = InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException e)
        {
            this.server = "unknown";
        }
    }

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }
    public int getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    public String getResponseStatus() {
        return responseStatus;
    }
    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
