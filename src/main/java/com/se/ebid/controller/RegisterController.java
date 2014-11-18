/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mtmmoei
 */
@Controller
public class RegisterController {
    @RequestMapping("/register")
     public String viewRegister(Model model) {
        model.addAttribute("countryList", new CountryList());
        model.addAttribute("title", "Register");
        return "registerView";
    }
    
     public void onSubmit(RegistrationForm form){
         //do something
     }
}
