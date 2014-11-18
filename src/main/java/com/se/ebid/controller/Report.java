/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import java.sql.Timestamp;

/**
 *
 * @author mtmmoei
 */
public class Report {
    private Timestamp startTime;
    private Timestamp endTime;
    private double buyingAmount;
    private double chargeAmount;
    private double sellingAmount;
    private long transactionAmount;
    ReportType reportType;

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public double getBuyingAmount() {
        return buyingAmount;
    }

    public void setBuyingAmount(double buyingAmount) {
        this.buyingAmount = buyingAmount;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public double getSellingAmount() {
        return sellingAmount;
    }

    public void setSellingAmount(double sellingAmount) {
        this.sellingAmount = sellingAmount;
    }

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }
    
}
