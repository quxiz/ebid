/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Item;
import com.se.ebid.entity.Member;
import com.se.ebid.entity.Transaction;
import com.se.ebid.service.ItemService;
import com.se.ebid.service.MemberService;
import com.se.ebid.service.TransactionService;
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
public class CheckOutController {

    private TransactionService transactionService;
    private ItemService itemService;
    private MemberService memberService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/checkOut/{transactionID}", method = RequestMethod.GET)
    public String viewCheckout(@PathVariable("transactionID") long transactionID, Model model) {
        model.addAttribute("title", "ชำระค่าสินค้า");
        Transaction transaction = this.transactionService.getTransaction(transactionID);
        Member member = this.memberService.getMember();
        Item item = this.itemService.getItem(transactionID);
        TransactionForm transactionForm = new TransactionForm();
        transactionForm.setTransactionID(transactionID);
        transactionForm.setAddress(member.getAddress());
        model.addAttribute("transactionForm", transactionForm);
        model.addAttribute("member", member);
        model.addAttribute("transaction", transaction);
        model.addAttribute("item", item);
        return "checkOutView";
    }

    @RequestMapping(value = "/checkOut/submit", method = RequestMethod.POST)
    public String onSubmitCheckout(@ModelAttribute TransactionForm transactionForm) {
        Transaction transaction = this.transactionService.getTransaction(transactionForm.getTransactionID());
    //    transaction.setShippingAddress(transactionForm.getAddress());
        return "redirect:/payment/"+transactionForm.getTransactionID();
    }
    
    @RequestMapping(value = "/payment/{transactionID}", method = RequestMethod.GET)
    public String finishCheckout(@PathVariable("transactionID") long transactionID,Model model) {
        model.addAttribute("transactionID", transactionID);    
        return "paymentView";//ไปยังหน้าpaypal
    }
    
    @RequestMapping(value = "/checkOut/checkoutTransaction/{transactionID}", method = RequestMethod.GET)
    public String finishCheckout(@PathVariable("transactionID") long transactionID) {
        this.transactionService.checkOutTransaction(transactionID);        
        return "redirect:/";//ชำระค่าสินค้าเรียบร้อยแล้ว
    }
    
}
