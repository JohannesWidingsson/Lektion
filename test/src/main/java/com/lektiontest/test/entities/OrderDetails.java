package com.lektiontest.test.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="orderdetails")
public class OrderDetails implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int orderdetailsid;
    private int ordernumber;
    private String productcodeid;

    private int quantityordered;
    private float priceeach;
    private int orderlinenumber;

    public int getOrderNumber() {
        return this.ordernumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.ordernumber = orderNumber;
    }

    public String getProductCode() {
        return this.productcodeid;
    }

    public void setProductCode(String productCode) {
        this.productcodeid = productCode;
    }

    public int getQuantityOrdered() {
        return this.quantityordered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityordered = quantityOrdered;
    }

    public float getPriceEach() {
        return this.priceeach;
    }

    public void setPriceEach(float priceEach) {
        this.priceeach = priceEach;
    }

    public int getOrderLineNumber() {
        return this.orderlinenumber;
    }

    public void setOrderLineNumber(int orderLineNumber) {
        this.orderlinenumber = orderLineNumber;
    }
    
    public int getOrderdetailsid() {
        return this.orderdetailsid;
    }

    public void setOrderdetailsid(int orderdetailsid) {
        this.orderdetailsid = orderdetailsid;
    }


}