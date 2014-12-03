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
import java.io.UnsupportedEncodingException;
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
        Item item = this.itemService.getItemByTransactionID(transactionID);
        TransactionForm transactionForm = new TransactionForm();
        transactionForm.setTransactionID(transactionID);
        transactionForm.setAddress(member.getAddress());
        model.addAttribute("transactionForm", transactionForm);
        model.addAttribute("member", member);
        model.addAttribute("transaction", transaction);
        model.addAttribute("item", item);
        if (item.getShippingService() != null && item.getShippingCost() != null) {
            String[] shippingServices = item.getShippingService().split(" ");
            String[] shippingCosts = item.getShippingCost().split(" ");
            model.addAttribute("shippingServices", shippingServices);
            model.addAttribute("shippingCosts", shippingCosts);
        }
        return "checkOutView";
    }

    @RequestMapping(value = "/checkOut/submit", method = RequestMethod.POST)
    public String onSubmitCheckout(@ModelAttribute("transactionForm") TransactionForm transactionForm, Model model) throws UnsupportedEncodingException {
        Transaction transaction = this.transactionService.getTransaction(transactionForm.getTransactionID());
        System.out.println(transactionForm.getTransactionID());
        System.out.println(transactionForm.getAddress());
        System.out.println(transactionForm.getShippingService());
        System.out.println(new String(transactionForm.getShippingService().getBytes("iso8859-1"), "UTF-8"));
        
//        if (!transaction.getShippingAddress().equals(transactionForm.getAddress())) {
//            transaction.setShippingAddress(transactionForm.getAddress());
//        }
//        if (!transaction.getShippingService().equals(transactionForm.getShippingService())) {
//            transaction.setShippingService(transactionForm.getShippingService());
//        }
        
        //transaction.setShippingAddress(new String(transactionForm.getAddress().getBytes("iso8859-1"), "UTF-8"));
        //transaction.setShippingService(new String(transactionForm.getShippingService().getBytes("iso8859-1"), "UTF-8"));
        this.transactionService.setShippingService(transactionForm.getTransactionID(), transactionForm.getShippingService(), transactionForm.getAddress());
        return "redirect:/payment/" + transactionForm.getTransactionID();
    }

    @RequestMapping(value = "/payment/{transactionID}", method = RequestMethod.GET)
    public String finishCheckout(@PathVariable("transactionID") long transactionID, Model model) {
        model.addAttribute("transactionID", transactionID);
        return "paymentView";
    }

    @RequestMapping(value = "/checkoutTransaction/{transactionID}", method = RequestMethod.GET)
    public String CheckoutTransaction(@PathVariable("transactionID") long transactionID, Model model) {
        int success = this.transactionService.checkOutTransaction(transactionID);
        model.addAttribute("link", "");
        model.addAttribute("btnText", "");
        if (success>0) {
            model.addAttribute("isSuccess", true);
            model.addAttribute("text", "การชำระเงินเสร็จสิ้น");

        } else {
            if(success == TransactionService.ERR_ALREADY_COMPLETE){
                model.addAttribute("isSuccess", false);
                model.addAttribute("text", "รายการสินค้านี้ได้รับการชำระเงินก่อนหน้านี้แล้ว หากมีข้อผิดพลาด กรุณาแจ้งผู้ดูแล");
            }
            else if(success == TransactionService.ERR_NO_TRANSACTION){
                model.addAttribute("isSuccess", false);
                model.addAttribute("text", "ไม่พบรายการสินค้า หากมีข้อผิดพลาด กรุณาแจ้งผู้ดูแล");
            }
            else if(success == TransactionService.ERR_NOT_ENOUGH_QTY){
                model.addAttribute("isSuccess", false);
                model.addAttribute("text", "จำนวนสินค้าปัจจุบันไม่เพียงพอ กรุณาติดต่อระบบชำระเงิน(PayPal)เพื่อแจ้งปัญหา และขอรับเงินคืน");
            }
            else{
                model.addAttribute("isSuccess", false);
                model.addAttribute("text", "มีข้อผิดพลาดในการชำระเงิน หากมีข้อผิดพลาด กรุณาแจ้งผู้แล");
            }
            
        }
        return "showView";
    }

    @RequestMapping("/checkOut/error")
    public String CheckoutTransaction(Model model) {
        model.addAttribute("isSuccess", false);
        model.addAttribute("text", "เงินในบัญชีของคุณไม่พอในการจ่าย");
        model.addAttribute("link", "");
        model.addAttribute("btnText", "");
        return "showView";
    }

}
