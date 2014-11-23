/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Complaint;
import com.se.ebid.service.ComplaintService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mtmmoei
 */
@Controller
public class SolveComplaintController {
    
    private ComplaintService complaintService;
    
    @Autowired
    public void setComplaintService(ComplaintService complaintService){
        this.complaintService=complaintService;
    }
    
    @RequestMapping("/solveComplaint")
     public String viewSolveComplaint(Model model) {
        model.addAttribute("title", "ตอบข้อร้องเรียน");
        List<Complaint> listComplaints = this.complaintService.getComplaint();
        model.addAttribute("listComplaints", listComplaints);
        SolveComplaintForm solveComplaintForm = new SolveComplaintForm();
        model.addAttribute("solveComplaintForm",solveComplaintForm);
        return "solveComplaintView";
    }  
     
     @RequestMapping(value = "/solveComplaint/onSubmit", method = RequestMethod.POST)
     public String onSubmit(@ModelAttribute ("solveComplaintForm") SolveComplaintForm solveComplaintForm){
        boolean success = this.complaintService.solveComplaint(solveComplaintForm);
        if(success)return "redirect:/solveComplaint";
        else return "redirect:/solveComplaint";//notfound     
     }
}
