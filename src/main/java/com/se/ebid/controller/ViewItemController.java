/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mtmmoei
 */
@Controller
public class ViewItemController {
    @RequestMapping("/viewItem")
     public String viewEditPaymentInfo(Model model) {
        model.addAttribute("title", "View Item");
        return "viewItemView";
    }  
    
     public void askQuestion(){
         //di sth
     }
     public void onSubmitQuestionForm(QuestionForm form){
         //do sth
     }
     public void onSubmitBidForm(BidForm form){
         //do sth
     }
     public void onSubmitBuyForm(BuyForm form){
         //do sth
     }
     public void confirmBuy(){
         //do sth
     }
}

