package com.example.prm_app_shopping.model;

import java.util.Date;

public class orders {
    private String id;
    private String coustomerID;
    private int orderStatus;
    private Date orderDate;
    private Date requiredDate;
    private Date shipDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoustomerID() {
        return coustomerID;
    }

    public void setCoustomerID(String coustomerID) {
        this.coustomerID = coustomerID;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public orders(String id, String coustomer, int orderStatus, Date orderDate, Date requiredDate, Date shipDate) {
        this.id = id;
        this.coustomerID = coustomer;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shipDate = shipDate;
    }

    public orders() {
    }
}
