/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

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
        return "editPersonalInfoView";
    }
     public void onSubmitPersonalInfo(PersonalInfoForm form){
         //do sth
     }
     public void onSubmitEditPassword(EditPasswordForm form){
         //do sth
     }
}
