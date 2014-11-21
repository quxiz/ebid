/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.controller.Report;
import java.sql.Timestamp;

/**
 *
 * @author Nuttapong
 */
public interface ReportService {
    public Report printReport(String reportType, int month, int year); 
}
