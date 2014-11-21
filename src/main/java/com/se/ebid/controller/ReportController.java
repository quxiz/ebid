/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    //comment ให้รันผ่านเฉยๆเพราะกุยังไม่มี service
  /*  private ReportService reportService;
    
    @Autowired
     public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }
     
    @RequestMapping(value = "/report/", method = RequestMethod.GET)
     public String viewReport(Model model) {
        model.addAttribute("title", "Report");
        List<CategoryType> categoryList = new ArrayList<>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        ReportForm reportForm = new ReportForm();
        model.addAttribute("reportForm",reportForm);
        return "reportView";
    }  
     
     @RequestMapping(value = "/report/printReport", method = RequestMethod.POST)
     public String printReport(@ModelAttribute("report") Report report,@ModelAttribute ReportForm reportForm,
            Model model, RedirectAttributes redirectAttributes){
         //do sth
         report = this.reportService.printReport(reportForm.getReportType(),reportForm.getMonth(),reportForm.getYear());
         redirectAttributes.addFlashAttribute("report", report);
         return "redirect:/repoer/showReportView";
     }
     
     @RequestMapping(value = "/reportView/printReport", method = RequestMethod.GET)
     public String showReport(@ModelAttribute("report") Report report, Model model){
         model.addAttribute("report",report);
         return "reportView";
     } */
}
