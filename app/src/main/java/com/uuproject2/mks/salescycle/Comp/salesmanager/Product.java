package com.uuproject2.mks.salescycle.Comp.salesmanager;

/**
 * Created by mks on 11/8/2017.
 */

public class Product {
    private String productName;
    private String productQuantity;
    private String productPrice;
    private String url;
    private String id;

    public Product(String id,String productName, String productQuantity, String productPrice, String url) {
        this.id=id;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.url = url;
    }
    public Product(){

    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getUrl() {
        return url;
    }
}
