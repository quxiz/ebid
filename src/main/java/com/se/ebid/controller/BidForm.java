/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

/**
 *
 * @author mtmmoei
 */
public class BidForm {
    private long itemID;
    
    @DecimalMax(value = "1000000.00", message = "Maxbid must be lower than or equal to 1000000")
    @DecimalMin(value = "0.01", message = "Maxbid must be greater than or equal to 0.01")
    @Size(min = 1, message = "Maxbid must not be empty")
    @Digits(integer = 20, fraction = 2, message = "Maxbid is invalid")
    private double maxBid;
    
    @DecimalMin(value = "0.01", message = "Bid increment must be greater than or equal to 0.01")
    @Size(min = 1, message = "Bid increment must not be empty")
    @Digits(integer = 20, fraction = 2, message = "Bid increment is invalid")
    private double bidIncrement;
    
    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itenID) {
        this.itemID = itenID;
    }

    public double getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(double maxBid) {
        this.maxBid = maxBid;
    }

    public double getBidIncrement() {
        return bidIncrement;
    }

    public void setBidIncrement(double bidIncrement) {
        this.bidIncrement = bidIncrement;
    }
    
}
