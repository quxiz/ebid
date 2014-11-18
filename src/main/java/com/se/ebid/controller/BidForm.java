/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

/**
 *
 * @author mtmmoei
 */
public class BidForm {
    private long itenID;
    private double maxBid;
    private double bidIncrement;

    public long getItenID() {
        return itenID;
    }

    public void setItenID(long itenID) {
        this.itenID = itenID;
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
