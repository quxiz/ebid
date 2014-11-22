/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Member;
import com.se.ebid.service.MemberService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping("/editPersonalInfo")
    public String viewEditPersonalInfo(Model model) {
        model.addAttribute("title", "Edit Info");
        List<CategoryType> categoryList = new ArrayList<>(Arrays.asList(CategoryType.values()));
        model.addAttribute("categoryList", categoryList);
        Member member = this.memberService.getMember();
        PersonalInfoForm personalInfoForm = new PersonalInfoForm();
        EditPasswordForm editPasswordForm = new EditPasswordForm();
        model.addAttribute("member", member);
        model.addAttribute("personalInfoForm",personalInfoForm);
        model.addAttribute("editPasswordForm",editPasswordForm);
        return "editPersonalInfoView";
    }

    @RequestMapping (value ="/editPersonalInfo/onSubmitPersonalInfo", method = RequestMethod.POST)
    public String onSubmitPersonalInfo(@ModelAttribute PersonalInfoForm personalInfoForm) {
        this.memberService.editPersonalInfo(personalInfoForm);
        return "redirect:/";//หน้าแก้ไขข้อมูลตัวเองเรียบร้อย
    }
    
    @RequestMapping (value ="/editPersonalInfo/onSubmitEditPassword", method = RequestMethod.POST)
    public String onSubmitEditPassword(@ModelAttribute EditPasswordForm editPasswordInfoForm) {
        this.memberService.editPassword(editPasswordInfoForm);
        return "redirect:/";//หน้าแก้ไขพาสเรียบร้อย
    }
}
