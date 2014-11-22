/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.service.MemberService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mtmmoei
 */
public class EditPersonalInfoController {

    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

   @RequestMapping ("/editInfo")
    public String viewEditPersonalInfo(Model model) {
        model.addAttribute("title", "แก้ไขข้อมูลส่วนตัว");
//         List<CategoryType> categoryList = new ArrayList<CategoryType>( Arrays.asList(CategoryType.values() ));  
//        model.addAttribute("categoryList", categoryList);
        return "editPersonalInfoView";
    }

    public void onSubmitPersonalInfo(PersonalInfoForm form) {
        //do sth
    }

    public void onSubmitEditPassword(EditPasswordForm form) {
        //do sth
    }
}
