package com.lektiontest.test.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Products {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productcodeid;
    private String productname;
    private String productline;
    private String productscale;
    private String productvendor;
    private String productdescription;
    private int quantityinstock;
    private float buyprice;
    private float msrp;

    public String getProductCodeId() {
        return this.productcodeid;
    }

    public void setProductCodeId(String productcodeid) {
        this.productcodeid = productcodeid;
    }

    public String getProductName() {
        return this.productname;
    }

    public void setProductName(String productName) {
        this.productname = productName;
    }

    public String getProductLine() {
        return this.productline;
    }

    public void setProductLine(String productLine) {
        this.productline = productLine;
    }

    public String getProductScale() {
        return this.productscale;
    }

    public void setProductScale(String productScale) {
        this.productscale = productScale;
    }

    public String getProductVendor() {
        return this.productvendor;
    }

    public void setProductVendor(String productVendor) {
        this.productvendor = productVendor;
    }

    public String getProductDescription() {
        return this.productdescription;
    }

    public void setProductDescription(String productDescription) {
        this.productdescription = productDescription;
    }

    public int getQuantityInStock() {
        return this.quantityinstock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityinstock = quantityInStock;
    }

    public float getBuyPrice() {
        return this.buyprice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyprice = buyPrice;
    }

    public float getMSRP() {
        return this.msrp;
    }

    public void setMSRP(float MSRP) {
        this.msrp = MSRP;
    }

    
    
}
