/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.service.ComplaintService;
import java.io.UnsupportedEncodingException;
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
       
    @RequestMapping(value = "/complaint")
     public String viewComplaint(Model model) {
        model.addAttribute("title", "Complaint");
        ComplaintForm complaintForm = new ComplaintForm();
        model.addAttribute("complaintForm", complaintForm);
        return "complaintView";
    }  
     
     @RequestMapping(value = "/complaint/submit", method = RequestMethod.POST)
     public String onSubmitComplaint(@ModelAttribute ("complaintForm") ComplaintForm complaintForm,Model model) throws UnsupportedEncodingException{
         complaintForm.setDetail(new String(complaintForm.getDetail().getBytes("iso8859-1"), "UTF-8"));
         complaintForm.setTitle(new String(complaintForm.getTitle().getBytes("iso8859-1"), "UTF-8"));
         boolean isSuccess = this.complaintService.complaint(complaintForm);
         model.addAttribute("isSuccess", isSuccess);
         if(isSuccess){
        model.addAttribute("text", "You've sent your complaint to administrators. ");
         model.addAttribute("link", "");
            model.addAttribute("btnText", "");
        } else { model.addAttribute("text", "Fail to send your complaint. ");
         model.addAttribute("link", "/complaint");
            model.addAttribute("btnText", "กลับหน้าร้องเรียน");
         }return "showView";
     }
}
