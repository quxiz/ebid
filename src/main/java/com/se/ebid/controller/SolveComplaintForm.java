/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import javax.validation.constraints.Size;

/**
 *
 * @author mtmmoei
 */
public class SolveComplaintForm {
    private long solverID;
    private long complaintID;
    
    @Size(min = 1, message = "Detail must not be empty")
    private String detail;

    public long getSolverID() {
        return solverID;
    }

    public void setSolverID(long solverID) {
        this.solverID = solverID;
    }

    public long getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(long complaintID) {
        this.complaintID = complaintID;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
