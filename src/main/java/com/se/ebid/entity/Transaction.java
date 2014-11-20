/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.entity;

import com.se.ebid.controller.SellingType;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Quxiz
 */
@Entity
@Table(name="Transaction")
public class Transaction {
    @Id
    @Column(name="transactionID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long transactionID;
    @Column(name="sellerID", nullable = false)
    private long sellerID;
    @Column(name="buyerID", nullable = false)
    private long buyerID;
    @Column(name="itemID", nullable = false)
    private long itemID;
    @Column(name="quantity")
    private long quantity;
    @Column(name="price")
    private double price;
    @Column(name="detail")
    private String detail;
    @Column(name="sellingType")
    private SellingType sellingType;
    @Column(name="delivery")
    private String delivery;
    @Column(name="timestamp")
    private java.sql.Timestamp timestamp;
    @Column(name="complated")
    private boolean complated;

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public long getSellerID() {
        return sellerID;
    }

    public void setSellerID(long sellerID) {
        this.sellerID = sellerID;
    }

    public long getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(long buyerID) {
        this.buyerID = buyerID;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public SellingType getSellingType() {
        return sellingType;
    }

    public void setSellingType(SellingType sellingType) {
        this.sellingType = sellingType;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isComplated() {
        return complated;
    }

    public void setComplated(boolean complated) {
        this.complated = complated;
    }
    
    
    
}