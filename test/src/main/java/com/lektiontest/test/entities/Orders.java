package com.lektiontest.test.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
public class Orders {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ordernumber;
    private Date orderdate;
    private Date requireddate;
    private Date shippeddate;
    private String status;



    
    @NotNull

    @Length(min=1, max=20, message="Message must be between 1 and 20 charecters")
    private String comments;
    private int customernumber;

    public int getOrderNumber() {
        return this.ordernumber;
    }

    public void setOrderNumber(int ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Date getOrderDate() {
        return this.orderdate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderdate = orderDate;
    }

    public Date getRequiredDate() {
        return this.requireddate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requireddate = requiredDate;
    }

    public Date getShippedDate() {
        return this.shippeddate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippeddate = shippedDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getCustomerNumber() {
        return this.customernumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customernumber = customerNumber;
    }

}