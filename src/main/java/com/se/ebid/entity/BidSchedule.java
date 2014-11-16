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
@Table(name="BidSchedule")
public class BidSchedule {
    @Id
    @Column(name="itemID", nullable = false)
    private long itemID;
    @Column(name="endTime")
    private java.sql.Timestamp endTime;
    @Column(name="complated")
    private boolean complated;

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public boolean isComplated() {
        return complated;
    }

    public void setComplated(boolean complated) {
        this.complated = complated;
    }
    
    
    
}