/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.service.MemberService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mtmmoei
 */
@Controller
public class RegisterController {

    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/register")
    public String viewRegister(Model model) {
        if (!model.containsAttribute("registrationForm")) {
            model.addAttribute("registrationForm", new RegistrationForm());
        }
        model.addAttribute("countryList", new CountryList());
        model.addAttribute("title", "สมัครสมาชิก");
        return "registerView";
    }

    @RequestMapping(value = "/register/submit", method = RequestMethod.POST)
    public String onSubmitRegistration(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationForm", result);
            redirectAttributes.addFlashAttribute("registrationForm", registrationForm);
            return "redirect:/register";
<<<<<<< HEAD
        } else {

            if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
                model.addAttribute("text", "Password mismatch");
                model.addAttribute("link", "");
                model.addAttribute("btnText", "");
            }
=======
        }else {
            
            if(!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) model.addAttribute("text","รหัสผ่านไม่ตรงกับยืนยันรหัสผ่าน"); 
>>>>>>> 6f7d1d9d9c7dd6662ae6740312b96bb6750fad43
            registrationForm.setAddress(new String(registrationForm.getAddress().getBytes("iso8859-1"), "UTF-8"));
            registrationForm.setFirstName(new String(registrationForm.getFirstName().getBytes("iso8859-1"), "UTF-8"));
            registrationForm.setLastName(new String(registrationForm.getLastName().getBytes("iso8859-1"), "UTF-8"));
            registrationForm.setUserID(new String(registrationForm.getUserID().getBytes("iso8859-1"), "UTF-8"));
            int ret = this.memberService.register(registrationForm);
<<<<<<< HEAD
            switch (ret) {
                case MemberService.ERR_DUP_EMAIL: {
                    model.addAttribute("isSuccess", false);
                    model.addAttribute("text", "Email has already used");
                    model.addAttribute("link", "");
                    model.addAttribute("btnText", "");
                }
                case MemberService.ERR_DUP_USER: {
                    model.addAttribute("isSuccess", false);
                    model.addAttribute("text", "User ID has already used");
                    model.addAttribute("link", "");
                    model.addAttribute("btnText", "");
                }
            }
            if (ret >= 0) {
                model.addAttribute("isSuccess", true);
                model.addAttribute("text", "Please check your email and activate your ID");
                model.addAttribute("link", "");
                model.addAttribute("btnText", "");
            } else {
                model.addAttribute("isSuccess", false);
                model.addAttribute("text", "Error found");
                model.addAttribute("link", "");
                model.addAttribute("btnText", "");
            }
=======
            switch(ret){
                case MemberService.ERR_DUP_EMAIL : {model.addAttribute("isSuccess",false); model.addAttribute("text","อีเมลนี้ถูกใช้ไปแล้ว");}
                case MemberService.ERR_DUP_USER : {model.addAttribute("isSuccess",false); model.addAttribute("text","ชื่อผู้ใช้นี้ถูกใช้ไปแล้ว");}
            }
            if(ret >= 0) {model.addAttribute("isSuccess",true); model.addAttribute("text","กรุณาตรวจสอบอีเมลและ activate บัญชี");  }
            model.addAttribute("title","ผิดพลาด");
>>>>>>> 6f7d1d9d9c7dd6662ae6740312b96bb6750fad43
            return "showView";
        }
    }
}
