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
public class EditPersonalInfoController {

    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/editPersonalInfo")
    public String viewEditPersonalInfo(Model model) {
        model.addAttribute("title", "แก้ไขข้อมูลส่วนตัว");
        List<CategoryType> categoryList = new ArrayList<>(Arrays.asList(CategoryType.values()));
        model.addAttribute("categoryList", categoryList);
        Member member = this.memberService.getMember();
        PersonalInfoForm personalInfoForm = new PersonalInfoForm();
        EditPasswordForm editPasswordForm = new EditPasswordForm();
        ReceivingInfoForm receivingInfoForm = new ReceivingInfoForm();
        PaymentInfoForm paymentInfoForm = new PaymentInfoForm();
        personalInfoForm.setFirstName(member.getFirstName());
        personalInfoForm.setLastName(member.getLastName());
        personalInfoForm.setAddress(member.getAddress());
        personalInfoForm.setCountry(member.getCountry());
        personalInfoForm.setPhoneNo(member.getPhoneNo());
        receivingInfoForm.setPayPalAccount(member.getReceivingAccount());
        paymentInfoForm.setPayPalAccount(member.getPaymentAccount());
        model.addAttribute("member", member);
        model.addAttribute("personalInfoForm", personalInfoForm);
        model.addAttribute("editPasswordForm", editPasswordForm);
        model.addAttribute("receivingInfoForm", receivingInfoForm);
        model.addAttribute("paymentInfoForm", paymentInfoForm);
        model.addAttribute("countryList", new CountryList());
        return "editPersonalInfoView";
    }

    @RequestMapping(value = "/editPersonalInfo/onSubmitPersonalInfo", method = RequestMethod.POST)
    public String onSubmitPersonalInfo(@ModelAttribute PersonalInfoForm personalInfoForm) {
        this.memberService.editPersonalInfo(personalInfoForm);
        return "redirect:/";//หน้าแก้ไขข้อมูลตัวเองเรียบร้อย
    }

    @RequestMapping(value = "/editPersonalInfo/onSubmitEditPassword", method = RequestMethod.POST)
    public String onSubmitEditPassword(@ModelAttribute EditPasswordForm editPasswordInfoForm) {
        this.memberService.editPassword(editPasswordInfoForm);
        return "redirect:/";//หน้าแก้ไขพาสเรียบร้อย

    }

    @RequestMapping(value = "/editPersonalInfo/editReceivingInfo/onSubmit", method = RequestMethod.POST)
    public String onSubmitRecievingInfo(@ModelAttribute ReceivingInfoForm receivingInfoForm) {
        this.memberService.editReceivingInfo(receivingInfoForm);
        return "redirect:/";//เปลี่ยนข้อมูลการรับเงินสำเร็จ
    }

    @RequestMapping(value = "/editPersonalInfo/editPaymentInfo/onSubmit", method = RequestMethod.POST)
    public String onSubmitEditPaymentInfo(@ModelAttribute PaymentInfoForm paymentInfoForm) {
        if (this.memberService.editPaymentInfo(paymentInfoForm)) {
            return "rediect:/success/Your Payment Infomation has changed";
        } else {
            return "redirect:/error/There is a problem with your new Payment Information and can't change";
        }

    }
}
