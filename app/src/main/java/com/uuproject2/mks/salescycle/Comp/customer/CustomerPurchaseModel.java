package com.uuproject2.mks.salescycle.Comp.customer;

/**
 * Created by mks on 11/28/2017.
 */

public class CustomerPurchaseModel {
    private String transactionId;
    private String totalBil;
    private String paymentStatus;
    private String id;
    private String date;

    public CustomerPurchaseModel(String transactionId, String totalBil, String paymentStatus, String id, String date) {
        this.transactionId = transactionId;
        this.totalBil = totalBil;
        this.paymentStatus = paymentStatus;
        this.id = id;
        this.date = date;
    }

    public CustomerPurchaseModel(){}

    public String getTransactionId() {
        return transactionId;
    }

    public String getTotalBil() {
        return totalBil;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
}
