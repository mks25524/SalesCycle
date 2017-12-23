package com.uuproject2.mks.salescycle.Comp.model;

/**
 * Created by mks on 12/23/2017.
 */

public class SalesHistoryModel {
    private String id;
    private String date;
    private String chilna_orange;
    private String dragon_fruits;
    private String greenApple;
    private String greenGrapes;
    private String guava;
    private String nashpati;
    private String orange;
    private String redApple;
    private String redGrapes;
    private String totalBill;

    public SalesHistoryModel(String id, String date, String chilna_orange, String dragon_fruits, String greenApple, String greenGrapes, String guava, String nashpati, String orange, String redApple, String redGrapes, String totalBill) {
        this.id = id;
        this.date = date;
        this.chilna_orange = chilna_orange;
        this.dragon_fruits = dragon_fruits;
        this.greenApple = greenApple;
        this.greenGrapes = greenGrapes;
        this.guava = guava;
        this.nashpati = nashpati;
        this.orange = orange;
        this.redApple = redApple;
        this.redGrapes = redGrapes;
        this.totalBill = totalBill;
    }
    public SalesHistoryModel(){};

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getChilna_orange() {
        return chilna_orange;
    }

    public String getDragon_fruits() {
        return dragon_fruits;
    }

    public String getGreenApple() {
        return greenApple;
    }

    public String getGreenGrapes() {
        return greenGrapes;
    }

    public String getGuava() {
        return guava;
    }

    public String getNashpati() {
        return nashpati;
    }

    public String getOrange() {
        return orange;
    }

    public String getRedApple() {
        return redApple;
    }

    public String getRedGrapes() {
        return redGrapes;
    }

    public String getTotalBill() {
        return totalBill;
    }
}
