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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if(!model.containsAttribute("resetPasswordForm")){
            ResetPasswordForm resetPasswordForm = new ResetPasswordForm();
            resetPasswordForm.setEmail(email);
            resetPasswordForm.setSecret(secret);
            model.addAttribute("resetPasswordForm",resetPasswordForm);
        }
        else{
            System.out.println("already set resetPasswordForm");
        }
        return "resetPasswordView";
    }
     @RequestMapping(value="/resetPassword/onSubmit",method = RequestMethod.POST)
     public String onSubmit(@Valid @ModelAttribute ResetPasswordForm resetPasswordForm, BindingResult result, Model model, RedirectAttributes redirectAttributes){
         System.out.println("Req : reset password controller");
         if (result.hasErrors()) {
             System.out.println("X reset password form");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.resetPasswordForm", result);
            redirectAttributes.addFlashAttribute("resetPasswordForm", resetPasswordForm);
            return "redirect:/resetPassword/"+resetPasswordForm.getEmail()+"_"+resetPasswordForm.getSecret();
        }
         if(resetPasswordForm.getNewPassword().equals(resetPasswordForm.getConfirmNewPassword())){
            boolean success = this.memberService.resetPassword(resetPasswordForm);
            if(success){
                model.addAttribute("isSuccess", true);
                model.addAttribute("text", "แก้ไขรหัสผ่านเรียบร้าย");
                model.addAttribute("link", "/signIn");
                model.addAttribute("btnText", "เข้าสู่หน้าเข้าสู่ระบบ");
                return "showView";
            }
            else{
                model.addAttribute("isSuccess", false);
                model.addAttribute("text", "เกิดข้อผิดพลาด");
                model.addAttribute("link", "");
                model.addAttribute("btnText", "");
                return "showView";
            }
         }
         redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.resetPasswordForm", result);
         redirectAttributes.addFlashAttribute("resetPasswordForm", resetPasswordForm);
         return "redirect:/resetPassword/"+resetPasswordForm.getEmail()+"_"+resetPasswordForm.getSecret();
     }
}
