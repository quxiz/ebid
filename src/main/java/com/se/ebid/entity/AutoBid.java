/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.entity;

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
@Table(name="AutoBid")
public class AutoBid {
    @Id
    @Column(name="itemID", nullable = false)
    private long itemID;
    @Column(name="bidderID", nullable = false)
    private long bidderID;
    @Column(name="maxBid")
    private double maxBid;
    @Column(name="bidIncrement")
    private double bidIncrement;
    @Column(name="timestamp")
    private java.sql.Timestamp timestamp;

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public long getBidderID() {
        return bidderID;
    }

    public void setBidderID(long bidderID) {
        this.bidderID = bidderID;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    
}