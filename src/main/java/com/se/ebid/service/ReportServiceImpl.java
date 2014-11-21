/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.controller.Report;
import com.se.ebid.controller.ReportType;
import com.se.ebid.controller.SellingType;
import com.se.ebid.dao.TransactionDAO;
import com.se.ebid.entity.Transaction;
import java.sql.Timestamp;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nuttapong
 */

@Service
public class ReportServiceImpl implements ReportService{

    private TransactionDAO transactionDAO;
    
    @Autowired
    public void setTransactionDAO(TransactionDAO transactionDAO){
        this.transactionDAO = transactionDAO;
    }
    
    @Override
    @Transactional
    public Report printReport(String reportType, int month, int year) {
        Timestamp startTime = monthToStartTime(month,year);
        Timestamp endTime = monthToEndTime(month,year);
        double buyingAmount = 0;
        double chargeAmount = 0;
        long transactionAmount = 0;
        
        List<Transaction> transactionList = this.transactionDAO.findCompletedByTimestamp(startTime, endTime);
        for(Transaction aTransaction : transactionList){
            if( (reportType.equals(SellingType.BID.getName()) && aTransaction.getSellingType() == SellingType.BID) || (reportType.equals(SellingType.BUY.getName()) && aTransaction.getSellingType() == SellingType.BUY) || reportType.equals("ALL")){
                double price = aTransaction.getPrice();
                buyingAmount += price;
                chargeAmount += priceToChargeAmount(price);
                transactionAmount++;
            }
        }
        
        Report report = new Report();
        report.setStartTime(startTime);
        report.setEndTime(endTime);
        report.setBuyingAmount(buyingAmount);
        report.setChargeAmount(chargeAmount);
        report.setSellingAmount(buyingAmount - chargeAmount);
        report.setTransactionAmount(transactionAmount);
        switch(reportType){
            case "BID":
                report.setReportType(ReportType.BID);
                break;
            case "BUY":
                report.setReportType(ReportType.BUY);
                break;
            case "ALL":
                report.setReportType(ReportType.ALL);
                break;
            default:
                return null;
        }
        
        return report;
    }

    private Timestamp monthToStartTime(int month, int year) {
        return new Timestamp(year-1990,month-1,0,0,0,0,0);
    }

    private Timestamp monthToEndTime(int month, int year) {
        if(month==12){
            month = 0;
            year += 1;
        }
        Timestamp t = new Timestamp(year,month,0,0,0,0,0);
        return new Timestamp(t.getTime()-1);
    }

    private double priceToChargeAmount(double price) {
        return price*0.07;
    }
    
}
