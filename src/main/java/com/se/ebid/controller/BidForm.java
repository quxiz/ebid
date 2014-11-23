/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

/**
 *
 * @author mtmmoei
 */
public class BidForm {
    private long itemID;
    
    @DecimalMin(value = "0.01", message = "Maxbid must be greater than or equal to 0.01")
    @Size(min = 1, message = "Maxbid must not be empty")
    @Digits(integer = 20, fraction = 2, message = "Maxbid is invalid")
    private BigDecimal maxBid;
    
    @DecimalMin(value = "0.01", message = "Bid increment must be greater than or equal to 0.01")
    @Size(min = 1, message = "Bid increment must not be empty")
    @Digits(integer = 20, fraction = 2, message = "Bid increment is invalid")
    private BigDecimal bidIncrement;
    
    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itenID) {
        this.itemID = itenID;
    }

    public double getMaxBid() {
        return maxBid.doubleValue();
    }

    public void setMaxBid(double maxBid) {
        this.maxBid = BigDecimal.valueOf(maxBid);
    }

    public double getBidIncrement() {
        return bidIncrement.doubleValue();
    }

    public void setBidIncrement(double bidIncrement) {
        this.bidIncrement = BigDecimal.valueOf(bidIncrement);
    }
    
}
