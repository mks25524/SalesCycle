package com.uuproject2.mks.salescycle.Comp.salesman;

/**
 * Created by mks on 11/10/2017.
 */

public class InitialSalesCreate {
    private String date;
    private String id;
    private String salesManId;

    public InitialSalesCreate(String date, String id,String salesManId) {
        this.date = date;
        this.id = id;
        this.salesManId=salesManId;
    }
    public InitialSalesCreate(){}

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getSalesManId() {
        return salesManId;
    }
}
