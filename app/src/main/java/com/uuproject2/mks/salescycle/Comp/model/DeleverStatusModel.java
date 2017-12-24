package com.uuproject2.mks.salescycle.Comp.model;

/**
 * Created by mks on 12/24/2017.
 */

public class DeleverStatusModel {
    private String customerAddress;
    private String customerMobile;
    private String customerName;
    private String customerShopName;

    public DeleverStatusModel(String customerAddress, String customerMobile, String customerName, String customerShopName) {
        this.customerAddress = customerAddress;
        this.customerMobile = customerMobile;
        this.customerName = customerName;
        this.customerShopName = customerShopName;
    }
    public DeleverStatusModel(){};

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerShopName() {
        return customerShopName;
    }
}
