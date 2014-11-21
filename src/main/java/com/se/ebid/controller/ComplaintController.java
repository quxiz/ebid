/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.service.ComplaintService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mtmmoei
 */
@Controller
public class ComplaintController {
    
      private ComplaintService complaintService;
      
      @Autowired
      public void setComplaintService(ComplaintService complaintService){
          this.complaintService = complaintService;
      }
       
    @RequestMapping(value = "/complaint", method = RequestMethod.GET)
     public String viewComplaint(Model model) {
        model.addAttribute("title", "Complaint");
         List<CategoryType> categoryList = new ArrayList<>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        ComplaintForm complaintForm = new ComplaintForm();
        model.addAttribute("complaintForm", complaintForm);
        return "homeView";
    }  
     
     @RequestMapping(value = "/complaint", method = RequestMethod.POST)
     public String onSubmit(@ModelAttribute ComplaintForm complaintForm){
         this.complaintService.complaint(complaintForm);
         return "redirect:/";
     }
}
