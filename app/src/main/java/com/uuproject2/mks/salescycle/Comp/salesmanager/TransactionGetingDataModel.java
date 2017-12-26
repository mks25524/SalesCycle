package com.uuproject2.mks.salescycle.Comp.salesmanager;

/**
 * Created by mks on 11/29/2017.
 */

public class TransactionGetingDataModel {
    private String id;
    private String date;
    private String paymentStatus;
    private String totalBil;
    private String transactionId;

    public TransactionGetingDataModel(String id, String date, String paymentStatus, String totalBil, String transactionId) {
        this.id = id;
        this.date = date;
        this.paymentStatus = paymentStatus;
        this.totalBil = totalBil;
        this.transactionId = transactionId;
    }


    public TransactionGetingDataModel(){};

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getTotalBil() {
        return totalBil;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
