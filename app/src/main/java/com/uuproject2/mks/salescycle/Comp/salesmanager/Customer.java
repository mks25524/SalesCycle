package com.uuproject2.mks.salescycle.Comp.salesmanager;

/**
 * Created by mks on 11/10/2017.
 */

public class Customer {
   // private String id;
    private String customerName;
    private String customerShopName;
    private String customerMobile;
    private String customerAddress;
    private String customerPassword;

    public Customer( String customerName, String customerShopName, String customerMobile, String customerAddress, String customerPassword) {
      ///  this.id = id;
        this.customerName = customerName;
        this.customerShopName = customerShopName;
        this.customerMobile = customerMobile;
        this.customerAddress = customerAddress;
        this.customerPassword = customerPassword;
    }
    public Customer(){}

//    public String getId() {
//        return id;
//    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerShopName() {
        return customerShopName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerShopName(String customerShopName) {
        this.customerShopName = customerShopName;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
}
