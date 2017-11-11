package com.uuproject2.mks.salescycle.Comp.salesman;

/**
 * Created by mks on 11/10/2017.
 */

public class InitialSalesCreate {
    private String date;
    private String id;

    public InitialSalesCreate(String date, String id) {
        this.date = date;
        this.id = id;
    }
    public InitialSalesCreate(){}

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }
}
