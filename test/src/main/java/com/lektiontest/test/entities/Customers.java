package com.lektiontest.test.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

@Entity
public class Customers {
 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customernumber;
    
    private String customername;
    private String contactlastname;
    private String contactfirstname;
    private String phone;
    private String addressline1;
    private String addressline2;
    private String city;
    private String state;
    private String postalcode;
    private String country;
    //private int salesrepemployeenumber;
    private BigDecimal creditlimit;

    public int getCustomerNumber() {
        return this.customernumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customernumber = customerNumber;
    }

    public String getCustomerName() {
        return this.customername;
    }

    public void setCustomerName(String customerName) {
        this.customername = customerName;
    }

    public String getContactLastName() {
        return this.contactlastname;
    }

    public void setContactLastName(String contactLastName) {
        this.contactlastname = contactLastName;
    }

    public String getContactFirstName() {
        return this.contactfirstname;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactfirstname = contactFirstName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return this.addressline1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressline1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressline2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressline2 = addressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return this.postalcode;
    }

    public void setPostalCode(String postalCode) {
        this.postalcode = postalCode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
/*
    public int getSalesRepEmployeeNumber() {
        return this.salesrepemployeenumber;
    }

    public void setSalesRepEmployeeNumber(int salesRepEmployeeNumber) {
        this.salesrepemployeenumber = salesRepEmployeeNumber;
    } */

    public BigDecimal getCreditLimit() {
        return this.creditlimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditlimit = creditLimit;
    }


}