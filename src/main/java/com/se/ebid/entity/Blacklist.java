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
@Table(name="Blacklist")
public class Blacklist {
    @Id
    @Column(name="blacklistID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long blacklistID;
    @Column(name="memberID", nullable = false)
    private long memberID;
    @Column(name="detail")
    private String detail;
    @Column(name="status")
    private BlacklistStatus status;
    @Column(name="timestamp")
    private java.sql.Timestamp timestamp;

    public long getBlacklistID() {
        return blacklistID;
    }

    public void setBlacklistID(long blacklistID) {
        this.blacklistID = blacklistID;
    }

    public long getMemberID() {
        return memberID;
    }

    public void setMemberID(long memberID) {
        this.memberID = memberID;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BlacklistStatus getStatus() {
        return status;
    }

    public void setStatus(BlacklistStatus status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    
}