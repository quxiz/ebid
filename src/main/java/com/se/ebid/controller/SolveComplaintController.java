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
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mtmmoei
 */
@Controller
public class SolveComplaintController {
    @RequestMapping("/solveComplaint")
     public String viewSolveComplaint(Model model) {
        model.addAttribute("title", "ตอบข้อร้องเรียน");
         List<CategoryType> categoryList = new ArrayList<CategoryType>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        return "solveComplaintView";
    }  
     public void onSubmit(SolveComplaintForm form){
         //do sth
     }
     public void selectComplaint(long complaintID){
         
     }
}
