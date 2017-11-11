package com.uuproject2.mks.salescycle.Comp.salesman;

/**
 * Created by mks on 11/11/2017.
 */

public class FinalSalesCreate {
    private String quantity;
    private String price;

    public FinalSalesCreate(String quantity, String price) {
        this.quantity = quantity;
        this.price = price;
    }
    public FinalSalesCreate(){}

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }
}
