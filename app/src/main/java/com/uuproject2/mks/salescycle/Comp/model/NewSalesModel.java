package com.uuproject2.mks.salescycle.Comp.model;

/**
 * Created by mks on 12/21/2017.
 */

public class NewSalesModel {
    private String id;
    private String date;
    private String Orange;
    private String Red_Apple;
    private String China_Orange;
    private String Dragon_Fruits;
    private String Green_Apple;
    private String Guava;
    private String Nashpati;
    private String Red_Grapes;
    private String Green_Grapes;
    private int totalBill;

    public NewSalesModel(String id, String date, String orange, String red_Apple, String china_Orange, String dragonFruits, String green_Apple, String guava, String nashpati, String red_Grapes, String green_Grapes, int totalBill) {
        this.id = id;
        this.date = date;
        Orange = orange;
        Red_Apple = red_Apple;
        China_Orange = china_Orange;
        Dragon_Fruits = dragonFruits;
        Green_Apple = green_Apple;
        Guava = guava;
        Nashpati = nashpati;
        Red_Grapes = red_Grapes;
        Green_Grapes = green_Grapes;
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
        return Orange;
    }

    public String getRed_Apple() {
        return Red_Apple;
    }

    public String getChina_Orange() {
        return China_Orange;
    }

    public String getDragon_Fruits() {
        return Dragon_Fruits;
    }

    public String getGreen_Apple() {
        return Green_Apple;
    }

    public String getGuava() {
        return Guava;
    }

    public String getNashpati() {
        return Nashpati;
    }

    public String getRed_Grapes() {
        return Red_Grapes;
    }

    public String getGreen_Grapes() {
        return Green_Grapes;
    }

    public int getTotalBill() {
        return totalBill;
    }
}
