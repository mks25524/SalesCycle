package com.uuproject2.mks.salescycle.Comp.model;

/**
 * Created by mks on 11/24/2017.
 */

public class SMSData {

    // Number from witch the sms was send
    private String number;
    // SMS text body
    private String body;
    String date;
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }




}