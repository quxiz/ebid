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
        model.addAttribute("registrationForm", new RegistrationForm());
        model.addAttribute("countryList", new CountryList());
        model.addAttribute("title", "สมัครสมาชิก");
        List<CategoryType> categoryList = new ArrayList<CategoryType>(Arrays.asList(CategoryType.values()));
        model.addAttribute("categoryList", categoryList);
        return "registerView";
    }
    /*
     <<<<<<< HEAD
     <<<<<<< HEAD

     @RequestMapping("/register")
     public String viewRegister(Model model) {
     model.addAttribute("registrationForm", new RegistrationForm());
     model.addAttribute("countryList", new CountryList());
     model.addAttribute("title", "สมัครสมาชิก");
     List<CategoryType> categoryList = new ArrayList<CategoryType>(Arrays.asList(CategoryType.values()));
     model.addAttribute("categoryList", categoryList);
     return "registerView";
     =======
     @RequestMapping(value="/register{fail}",method= RequestMethod.GET)
     public String viewRegister(@PathVariable("fail") String fail,Model model) {
     model.addAttribute("registrationForm", new RegistrationForm());
     model.addAttribute("title", "สมัครสมาชิก");
     =======
     @RequestMapping(value="/register{fail}",method= RequestMethod.GET)
     public String viewRegister(@PathVariable("fail") String fail,Model model) {
     model.addAttribute("registrationForm", new RegistrationForm());
     model.addAttribute("countryList", new CountryList());
     model.addAttribute("title", "สมัครสมาชิก");
     >>>>>>> 4934d2486afea9fe6bb8cf84132ef1136d7119fe
     //         List<CategoryType> categoryList = new ArrayList<CategoryType>( Arrays.asList(CategoryType.values() ));  
     //        model.addAttribute("categoryList", categoryList);
     return "registerView"; 
     >>>>>>> 4934d2486afea9fe6bb8cf84132ef1136d7119fe
     }
     */

    @RequestMapping(value = "/register/submit", method = RequestMethod.POST)
    public String onSubmitRegistration(@Valid
            @ModelAttribute("form") RegistrationForm form, BindingResult result, Model model
    ) {
        System.out.println("first name = " + form.getFirstName());
        System.out.println(result.hasErrors());
        if (result.hasErrors()) {
            return "registerView";
            //return "redirect:/register";

        } else {
            return "homeView";
            //boolean successStatus = this.memberService.register(form);

            //if (successStatus) {
            //    return "redirect:/registerSuccess";
            //} else {
            //    return "redirect:/register";
            //}
        }
    }

}
