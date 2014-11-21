/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Quxiz
 */
@Controller
public class SignInController {

//    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
//    public String viewSignIn(ModelMap model) {
//        SignInForm signInForm = new SignInForm();
//        model.addAttribute("signInForm", signInForm);
//        model.addAttribute("title", "SignIn");
//        return "signInView";
//    }
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String viewSignIn(Model model) {
        model.addAttribute("title", "เข้าสู่ระบบ");
        
        List<CategoryType> categoryList = new ArrayList<>(Arrays.asList(CategoryType.values()));
        model.addAttribute("categoryList", categoryList);
        
        SignInForm signInForm = new SignInForm();
        model.addAttribute("signInForm", signInForm);
        
        return "signInView";
    }

    public void onSubmit(SignInForm form) {
        //do something
    }
}
