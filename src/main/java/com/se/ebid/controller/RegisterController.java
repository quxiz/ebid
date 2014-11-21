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
public class RegisterController {

     private MemberService memberService;
    
    @Autowired
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }
    @RequestMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        model.addAttribute("countryList", new CountryList());
        model.addAttribute("title", "สมัครสมาชิก");
         List<CategoryType> categoryList = new ArrayList<CategoryType>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        return "registerView";
    }

    @RequestMapping(value = "/register/submit", method = RequestMethod.POST)
    public String onSubmitRegistration(@ModelAttribute RegistrationForm form) {
        this.memberService.register(form);
        return "redirect:/activateMember";
    }
}
