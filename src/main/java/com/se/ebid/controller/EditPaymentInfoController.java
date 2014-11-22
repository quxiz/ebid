/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Member;
import com.se.ebid.service.MemberService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mtmmoei
 */
@Controller
public class EditPaymentInfoController {
    
    private MemberService memberService;
    public void setMemberService(MemberService memberservice){
        this.memberService=memberService;
    }
     @RequestMapping("/editPaymentInfo")
     public String viewEditPaymentInfo(Model model) {
        model.addAttribute("title", "Edit Payment Info");
         List<CategoryType> categoryList = new ArrayList<>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        Member member = this.memberService.getMember();
        PaymentInfoForm paymentInfoForm = new PaymentInfoForm();
        model.addAttribute("member", member);
        model.addAttribute("paymentInfoForm", paymentInfoForm);
        return "editPaymentInfoView";
    }  
     
     @RequestMapping(value = "/editPaymentInfo/onSubmit",method = RequestMethod.POST)
     public String onSubmit(@ModelAttribute PaymentInfoForm paymentInfoForm){
         this.memberService.editPaymentInfo(paymentInfoForm);
         return "redirect:/";//หน้าแก้ไขสำเร็จ
     }
}
