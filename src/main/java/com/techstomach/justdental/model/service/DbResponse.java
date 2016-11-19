package com.techstomach.justdental.model.service;

import com.google.gson.Gson;

public class DbResponse {

    private Header header;
    private Body body;

    public DbResponse() {

    }

    @Override
    public String toString() {
        try
        {
            Gson gson = new Gson();
            return gson.toJson(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public Header getHeader() {
        return header;
    }
    public void setHeader(Header header) {
        this.header = header;
    }
    public Body getBody() {
        return body;
    }
    public void setBody(Body body) {
        this.body = body;
    }
}
