package com.uuproject2.mks.salescycle.Comp.model;

/**
 * Created by mks on 12/21/2017.
 */

public class NewSalesModelLatLon {

    private String  id;
    private double lat;
    private double lon;

    public NewSalesModelLatLon() {
    }

    public NewSalesModelLatLon(String id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public String getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
