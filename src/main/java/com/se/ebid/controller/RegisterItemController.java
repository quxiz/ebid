/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

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
public class RegisterItemController {
    //private ItemService itemService;
    
    @RequestMapping("/registerItem")
     public String viewRegisterItem(Model model) {
        model.addAttribute("form", new RegisterItemForm());
        
        return "registerItemView";
    }  
     
     @RequestMapping(value= "/registerItem/sentForm", method = RequestMethod.POST)
     public String onSubmit(@ModelAttribute RegisterItemForm form){
         //this.itemService.registerItem(form);
         return "redirect:/registerItem";//รอแก้หน้าแสดง
     }
}
