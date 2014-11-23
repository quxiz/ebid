/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.se.ebid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Kawin
 */

@Controller
public class ReponseController {
    
    @RequestMapping(value = "/success/{text}",method = RequestMethod.GET)
    public String showSuccess(@PathVariable ("text") String text,Model model){
        model.addAttribute("text", text);
        model.addAttribute("isSuccess", true);
        return "showView"; //มีปุ่มกลับหน้าหลักเพียงปุ่มเดียว
    }
         
    @RequestMapping(value = "/error/{text}",method = RequestMethod.GET)
    public String showError(@PathVariable ("text") String text,Model model){
        model.addAttribute("text", text);
        model.addAttribute("isSuccess", false);
        return "showView"; //มีปุ่มกลับหน้าหลักเพียงปุ่มเดียว
    }
}
