/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.service.TransactionService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mtmmoei
 */
@Controller
public class ViewTransactionController {
     
    private TransactionService transactionService;
    
    @Autowired()
    public void setTransactionService(TransactionService transactionService){
        this.transactionService=transactionService;
    }
    
    /* @RequestMapping(value = "/viewTransaction", method = RequestMethod.GET)
     public String viewTransaction(Model model) {
        model.addAttribute("title", "ประวัติการซื้อขายสินค้า");
         List<CategoryType> categoryList = new ArrayList<CategoryType>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        
        model.addAttribute();
        return "viewTransactionView";
    }*/
     
     @RequestMapping("/viewBuyTransaction")
     public String selectBuyTransaction(Model model){
         
     model.addAttribute("title", "ประวัติการซื้อสินค้า");
         List<CategoryType> categoryList = new ArrayList<>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        //model.addAttribute("listTransactions",this.transactionService.getBuyTransaction());
        return "viewTransactionView";
     
     }
     
     @RequestMapping("/viewSellTransaction")
     public String selectSellTransaction(Model model){
         model.addAttribute("title", "ประวัติการขายสินค้า");
         List<CategoryType> categoryList = new ArrayList<>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        //model.addAttribute("listTransactions",this.transactionService.getSellTransaction());
        return "viewTransactionView";
     }
}
