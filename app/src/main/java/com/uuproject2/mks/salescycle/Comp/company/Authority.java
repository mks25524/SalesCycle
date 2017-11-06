package com.uuproject2.mks.salescycle.Comp.company;

/**
 * Created by mks on 11/5/2017.
 */

public class Authority {
    private String id;
    private String name;
    private String catagory;
    private String password;

    public Authority(String id, String name, String catagory, String password) {
        this.id = id;
        this.name = name;
        this.catagory = catagory;
        this.password = password;
    }
    public Authority(){

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCatagory() {
        return catagory;
    }

    public String getPassword() {
        return password;
    }
}
