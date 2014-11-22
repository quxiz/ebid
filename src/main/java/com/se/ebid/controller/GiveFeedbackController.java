/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.service.FeedbackService;
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
public class GiveFeedbackController {
    
    private FeedbackService feedbackService;
        
    @Autowired
    public void setFeedbackService(FeedbackService feedbackService){
        this.feedbackService = feedbackService; 
    }
    
    @RequestMapping(value = "/giveFeedback/{transactionID}",method = RequestMethod.GET)
     public String viewGiveFeedback(@PathVariable("transactionID") long transactionID,Model model) {
        model.addAttribute("title", "Give Feedback");
         List<CategoryType> categoryList = new ArrayList<>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        FeedbackForm feedbackForm = new FeedbackForm();
        feedbackForm.setTransactionID(transactionID);
        model.addAttribute("feedbackForm", feedbackForm);
        return "giveFeedbackView";
    }  
     
     @RequestMapping(value = "/giveFeedback/onSubmit", method = RequestMethod.POST)
     public String onSubmit(@ModelAttribute FeedbackForm feedbackForm){
         this.feedbackService.giveFeedback(feedbackForm);
         return "redirect:/"; //ไปหน้าโง่บอกว่าสำเร็จแล้วหรือล้มเหลว
     }
}
