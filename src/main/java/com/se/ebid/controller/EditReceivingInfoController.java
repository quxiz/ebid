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
import org.springframework.beans.factory.annotation.Autowired;
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
public class EditReceivingInfoController {
    
    private MemberService memberService;
    
    @Autowired
    public void setMemberService(MemberService memberService){
        this.memberService=memberService;
    }
    
    @RequestMapping("/editReceivingInfo")
     public String viewEditReceivingInfo(Model model) {
        model.addAttribute("title", "Edit Recieving Info");
         List<CategoryType> categoryList = new ArrayList<>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        Member member = this.memberService.getMember();
        ReceivingInfoForm receivingInfoForm = new ReceivingInfoForm();
        model.addAttribute("member", member);
        model.addAttribute("receivingInfoForm", receivingInfoForm);
        return "editRecievingInfoView";
    }  
     @RequestMapping(value = "/editReceivingInfo/onSubmit", method = RequestMethod.POST)
     public String onSubmit(@ModelAttribute ReceivingInfoForm receivingInfoForm){
         this.memberService.editReceivingInfo(receivingInfoForm);
         return "redirect:/";//เปลี่ยนข้อมูลการรับเงินสำเร็จ
     }
}
