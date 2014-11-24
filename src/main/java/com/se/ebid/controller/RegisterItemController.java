/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.service.ItemService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mtmmoei
 */
@Controller
public class RegisterItemController {

    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping("/registerItem")
    public String viewRegisterItem(Model model) {
        System.out.println("Req : register item form");
        List<CategoryType> categoryList = new ArrayList<>(Arrays.asList(CategoryType.values()));
        List<SellingType> sellingType = new ArrayList<>(Arrays.asList(SellingType.values()));
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("sellingType", sellingType);
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new RegisterItemForm());
            
        }
        model.addAttribute("title", "RegisterItem");
        return "registerItemView";
    }
    
    @RequestMapping(value = "/registerItem/sentForm", method = RequestMethod.POST)

    public String onSubmit(@Valid @ModelAttribute("form") RegisterItemForm form, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

         
        System.out.println("REQ: reg item call");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.form", result);
            redirectAttributes.addFlashAttribute("form", form);
            return "redirect:/registerItem";
        } else {
            long itemID = this.itemService.registerItem(form);
            if (itemID < 0) {
                model.addAttribute("isSuccess", false);
                model.addAttribute("text", "Sorry, The item was unsuccessfully registered");
                return "showView";
            }
            return "redirect:/viewItem/" + itemID;//รอแก้หน้าแสดง
        }

    }
}
