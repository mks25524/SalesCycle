package com.uuproject2.mks.salescycle.Comp.model;

/**
 * Created by mks on 12/30/2017.
 */

public class CoordinatesModel {
    private String id;
    private String lat;
    private String lon;

    public CoordinatesModel(String id, String lat, String lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public CoordinatesModel() {
    }

    public String getId() {
        return id;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}
