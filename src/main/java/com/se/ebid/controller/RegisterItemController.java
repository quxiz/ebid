/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        List<CategoryType> categoryList = new ArrayList<CategoryType>( Arrays.asList(CategoryType.values() ));    
        List<SellingType> sellingType = new ArrayList<SellingType>( Arrays.asList(SellingType.values() ));    
        
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("sellingType", sellingType);
        model.addAttribute("form", new RegisterItemForm());
        model.addAttribute("title", "Register");
        
        return "registerItemView";
    }  
     
     @RequestMapping(value= "/registerItem/sentForm", method = RequestMethod.POST)
     public String onSubmit(@ModelAttribute RegisterItemForm form){
         //this.itemService.registerItem(form);
         return "redirect:/viewItem";//รอแก้หน้าแสดง
     }
}
