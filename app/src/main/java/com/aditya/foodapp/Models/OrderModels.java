package com.aditya.foodapp.Models;

public class OrderModels {
    int orderimage;
    String ordername,price,ordernumber;

    public OrderModels(int orderimage, String ordername, String price, String ordernumber) {
        this.orderimage = orderimage;
        this.ordername = ordername;
        this.price = price;
        this.ordernumber = ordernumber;
    }

    public OrderModels() {

    }


    public int getOrderimage() {
        return orderimage;
    }

    public void setOrderimage(int orderimage) {
        this.orderimage = orderimage;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }
}
