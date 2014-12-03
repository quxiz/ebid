/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

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
public class ForgotPasswordController {

      private MemberService memberService;
    
    @Autowired
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }
    

      @RequestMapping("/forgotPassword")

     public String viewForgotPassword(Model model) {
        model.addAttribute("title", "Forgot Password");
         List<CategoryType> categoryList = new ArrayList<>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("forgotPasswordForm", new ForgotPasswordForm());
        return "forgotPasswordView";
    }
  
     @RequestMapping(value = "/forgotPassword/submit", method = RequestMethod.POST)
     public String onSubmitForgotPassword(ForgotPasswordForm form , Model model){
         
         if (this.memberService.forgotPassword(form)) {
                model.addAttribute("isSuccess", true);
                model.addAttribute("text", "จัดส่งอีเมลเพื่อแก้ไขรหัสผ่านเรียบร้อย กรุณาตรวจสอบอีเมล");
                model.addAttribute("link", "");
                model.addAttribute("btnText", "");
            } else {
                model.addAttribute("isSuccess", false);
                model.addAttribute("text", "ไม่พบอีเมลนี้ในระบบ");
                model.addAttribute("link", "/forgotPassword");
                model.addAttribute("btnText", "กลับสู่หน้าลืมรหัสผ่าน");
            }
            return "showView";
     }
}
