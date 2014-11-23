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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mtmmoei
 */
@Controller
public class ResetPasswordController {
    
     private MemberService memberService;
    
    @Autowired
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }
    
      @RequestMapping(value = "/resetPassword/{email}_{secret}",method = RequestMethod.GET)
     public String viewResetPassword(@PathVariable("secret") String secret,@PathVariable("email") String email,Model model) {
        model.addAttribute("title", "Reset password");
         List<CategoryType> categoryList = new ArrayList<>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        ResetPasswordForm resetPasswordForm = new ResetPasswordForm();
        resetPasswordForm.setEmail(email);
        resetPasswordForm.setSecret(secret);
        model.addAttribute("resetPasswordForm",resetPasswordForm);
        return "resetPasswordView";
    }
     @RequestMapping(value="/resetPassword/onSubmit",method = RequestMethod.POST)
     public String onSubmit(@ModelAttribute ResetPasswordForm resetPasswordForm){
         boolean success = this.memberService.resetPassword(resetPasswordForm);
         if(success)
         return "redirect:/signIn";//หน้าเปลี่ยนรหัสผ่านสำเร็จ
         return "redirect:/signIn";//หน้าเปลี่ยนรหัสผ่านผิดพลาด
     }
}
