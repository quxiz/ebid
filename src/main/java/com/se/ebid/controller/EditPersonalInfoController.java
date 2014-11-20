/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mtmmoei
 */
public class EditPersonalInfoController {
      @RequestMapping("/editInfo")
     public String viewEditPersonalInfo(Model model) {
        model.addAttribute("title", "Edit Info");
         List<CategoryType> categoryList = new ArrayList<CategoryType>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        return "editPersonalInfoView";
    }
     public void onSubmitPersonalInfo(PersonalInfoForm form){
         //do sth
     }
     public void onSubmitEditPassword(EditPasswordForm form){
         //do sth
     }
}
