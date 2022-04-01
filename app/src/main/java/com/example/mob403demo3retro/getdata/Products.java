package com.example.mob403demo3retro.getdata;

public class Products {
    private String pid;
    private String name;
    private String price;

    public Products(String pid, String name, String price) {
        this.pid = pid;
        this.name = name;
        this.price = price;
    }

    public Products() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
