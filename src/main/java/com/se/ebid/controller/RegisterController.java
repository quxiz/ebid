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
    public String onSubmitRegistration(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm, BindingResult result, Model model,RedirectAttributes redirectAttributes) {
        //System.out.println(result.hasErrors());
        if (result.hasErrors()) {
            //return "registerView";
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationForm", result);
            redirectAttributes.addFlashAttribute("registrationForm", registrationForm);
            return "redirect:/register";

        } else {
            
            try{
                boolean successStatus = this.memberService.register(registrationForm);
            }catch(ConstraintViolationException e){
                System.out.println("รหัสผ่านไม่ตรงกัน หรือ email หรือ userID มีอยู่ในระบบอยู่แล้ว");
                System.out.println(e.getCause());
                return "redirect:/error/password mismatched or usernamID or email has already used";
            }
            return "redirect:/success/Please check your email and activate your ID";
            //

            //if (successStatus) {
            //    return "redirect:/registerSuccess";
            //} else {
            //    return "redirect:/register";
            //}
        }
    }

}
