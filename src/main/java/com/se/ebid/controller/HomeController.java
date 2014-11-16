/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
     
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
     
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);
         
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
         
        String formattedDate = dateFormat.format(date);
         
        model.addAttribute("serverTime", formattedDate );
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("username", name);
        model.addAttribute("title", "Home Page");
        return "homeView";
    }
     
}