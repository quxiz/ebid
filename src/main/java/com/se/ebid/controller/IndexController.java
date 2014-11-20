/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.dao.MemberDAO;
import com.se.ebid.dao.MemberDAOImpl;
import com.se.ebid.entity.Member;
import com.se.ebid.service.CustomUser;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
 *
 * @author mtmmoei
 */
@Controller
public class IndexController {
     private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
     
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);
         
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
         
        String formattedDate = dateFormat.format(date);
         
        model.addAttribute("serverTime", formattedDate );
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("username", name);
        
        //user CustomUser
        CustomUser customUser = (CustomUser)auth.getPrincipal();
        String userID = customUser.getUserID();
        model.addAttribute("userID", userID);
        long memberID = customUser.getMemberID();
        model.addAttribute("memberID", memberID);
        model.addAttribute("title", "Index Page");
        
         List<CategoryType> categoryList = new ArrayList<CategoryType>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        return "indexView_bright";
    }
}
