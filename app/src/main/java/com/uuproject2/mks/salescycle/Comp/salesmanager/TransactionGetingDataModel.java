package com.uuproject2.mks.salescycle.Comp.salesmanager;

/**
 * Created by mks on 11/29/2017.
 */

public class TransactionGetingDataModel {
    private String id;
    private String name;

    public TransactionGetingDataModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TransactionGetingDataModel(){};

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
