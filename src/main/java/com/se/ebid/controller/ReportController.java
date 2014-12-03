/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.service.ReportService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mtmmoei
 */
@Controller
public class ReportController {
    private ReportService reportService;
    
    @Autowired
     public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }
     
    @RequestMapping(value = "/report", method = RequestMethod.GET)
     public String viewReport(Model model) {
        model.addAttribute("title", "ออกรายงาน");
        ReportForm reportForm = new ReportForm();
        model.addAttribute("reportForm",reportForm);
        return "reportView";
    }  
     
     @RequestMapping(value = "/report/printReport", method = RequestMethod.POST)
     public String printReport(@ModelAttribute ("reportForm") ReportForm reportForm,
            Model model, RedirectAttributes redirectAttributes){
         System.out.println("print report");
         Report report = this.reportService.printReport(reportForm.getReportType().toString(),reportForm.getMonth(),reportForm.getYear());
         redirectAttributes.addFlashAttribute("report", report);
         return "redirect:/report/showReport";
     }
     
     @RequestMapping(value = "/report/showReport", method = RequestMethod.GET)
     public String showReport(@ModelAttribute("report") Report report, Model model){
         model.addAttribute("report",report);
         model.addAttribute("reportMonth", report.getStartTime().getMonth() + 1);
         model.addAttribute("reportYear", report.getStartTime().getYear() + 1900 + 543);
         model.addAttribute("title","รายงาน");
         
         return "showReportView";
     } 
}
