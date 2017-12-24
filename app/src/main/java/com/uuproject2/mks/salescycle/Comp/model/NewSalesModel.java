package com.uuproject2.mks.salescycle.Comp.model;

/**
 * Created by mks on 12/21/2017.
 */

public class NewSalesModel {
    private String id;
    private String date;
    private String orange;
    private String red_Apple;
    private String china_Orange;
    private String dragon_Fruits;
    private String green_Apple;
    private String guava;
    private String nashpati;
    private String red_Grapes;
    private String green_Grapes;
    private int totalBill;

    public NewSalesModel(String id, String date, String orange, String red_Apple, String china_Orange, String dragon_Fruits, String green_Apple, String guava, String nashpati, String red_Grapes, String green_Grapes, int totalBill) {
        this.id = id;
        this.date = date;
        this.orange = orange;
        this.red_Apple = red_Apple;
        this.china_Orange = china_Orange;
        this.dragon_Fruits = dragon_Fruits;
        this.green_Apple = green_Apple;
        this.guava = guava;
        this.nashpati = nashpati;
        this.red_Grapes = red_Grapes;
        this.green_Grapes = green_Grapes;
        this.totalBill = totalBill;
    }

    public NewSalesModel(){};

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getOrange() {
        return orange;
    }

    public String getRed_Apple() {
        return red_Apple;
    }

    public String getChina_Orange() {
        return china_Orange;
    }

    public String getDragon_Fruits() {
        return dragon_Fruits;
    }

    public String getGreen_Apple() {
        return green_Apple;
    }

    public String getGuava() {
        return guava;
    }

    public String getNashpati() {
        return nashpati;
    }

    public String getRed_Grapes() {
        return red_Grapes;
    }

    public String getGreen_Grapes() {
        return green_Grapes;
    }

    public int getTotalBill() {
        return totalBill;
    }
}
