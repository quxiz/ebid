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
@Table(name="Complaint")
public class Complaint {
    @Id
    @Column(name="complaintID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long complaintID;
    @Column(name="complainterID", nullable = false)
    private long complainterID;
    @Column(name="complaintTitle")
    private String complaintTitle;
    @Column(name="complaintDetail")
    private String complaintDetail;
    @Column(name="complaintTimestamp")
    private java.sql.Timestamp complaintTimestamp;
    @Column(name="solverID")
    private long solverID;
    @Column(name="solveDetail")
    private String solveDetail;
    @Column(name="solveTimestamp")
    private java.sql.Timestamp solveTimestamp;

    public long getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(long complaintID) {
        this.complaintID = complaintID;
    }

    public long getComplainterID() {
        return complainterID;
    }

    public void setComplainterID(long complainterID) {
        this.complainterID = complainterID;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    public String getComplaintDetail() {
        return complaintDetail;
    }

    public void setComplaintDetail(String complaintDetail) {
        this.complaintDetail = complaintDetail;
    }

    public Timestamp getComplaintTimestamp() {
        return complaintTimestamp;
    }

    public void setComplaintTimestamp(Timestamp complaintTimestamp) {
        this.complaintTimestamp = complaintTimestamp;
    }

    public long getSolverID() {
        return solverID;
    }

    public void setSolverID(long solverID) {
        this.solverID = solverID;
    }

    public String getSolveDetail() {
        return solveDetail;
    }

    public void setSolveDetail(String solveDetail) {
        this.solveDetail = solveDetail;
    }

    public Timestamp getSolveTimestamp() {
        return solveTimestamp;
    }

    public void setSolveTimestamp(Timestamp solveTimestamp) {
        this.solveTimestamp = solveTimestamp;
    }

}