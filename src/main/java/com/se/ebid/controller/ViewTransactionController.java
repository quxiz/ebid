/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Item;
import com.se.ebid.entity.Transaction;
import com.se.ebid.service.ItemService;
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
     
    private ItemService itemService;
    
    @Autowired()
    public void setItemService(ItemService itemService){
        this.itemService=itemService;
    }
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
     
     @RequestMapping("/viewTransaction")
     public String selectTransaction(Model model){
         
     model.addAttribute("title", "ประวัติการซื้อขายสินค้า");
         List<Transaction> listSellTransactions = this.transactionService.getSellTransaction();
         List<Transaction> listBuyTransactions = this.transactionService.getBuyTransaction();
        model.addAttribute("listBuyTransactions",listBuyTransactions);
        model.addAttribute("listSellTransactions",listSellTransactions);
        List<Item> listSoldItems = null;
        List<Item> listBoughtItems = null;

//        for (Transaction listSellTransaction : listSellTransactions) {
//            listSoldItems.add(this.itemService.getItem(listSellTransaction.getItemID()));
//        }
//        for (Transaction listBuyTransaction : listBuyTransactions) {
//            listBoughtItems.add(this.itemService.getItem(listBuyTransaction.getItemID()));
//        }
        model.addAttribute("listSoldItems",listSoldItems);
        model.addAttribute("listBoughtItems",listBoughtItems);
        return "viewTransactionView";
     
     }
     
//     @RequestMapping("/viewSellTransaction")
//     public String selectSellTransaction(Model model){
//         model.addAttribute("title", "ประวัติการขายสินค้า");
//         List<CategoryType> categoryList = new ArrayList<>( Arrays.asList(CategoryType.values() ));  
////        model.addAttribute("categoryList", categoryList);
//        model.addAttribute("listTransactions",this.transactionService.getSellTransaction());
//        return "viewTransactionView";
//     }
}
