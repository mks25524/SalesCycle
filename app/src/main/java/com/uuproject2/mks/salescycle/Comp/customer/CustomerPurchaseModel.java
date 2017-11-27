package com.uuproject2.mks.salescycle.Comp.customer;

/**
 * Created by mks on 11/28/2017.
 */

public class CustomerPurchaseModel {
    private String transactionId;
    private String totalBil;

    public CustomerPurchaseModel(String transactionId, String totalBil) {
        this.transactionId = transactionId;
        this.totalBil = totalBil;
    }
    public CustomerPurchaseModel(){}

    public String getTransactionId() {
        return transactionId;
    }

    public String getTotalBil() {
        return totalBil;
    }
}
