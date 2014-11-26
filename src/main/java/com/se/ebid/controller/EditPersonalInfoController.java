/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Member;
import com.se.ebid.service.MemberService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = "/editPersonalInfo{errorNum}", method = RequestMethod.GET)
    public String viewEditPersonalInfo(Model model,@PathVariable ("errorNum") String errorNum) {
        
        model.addAttribute("title", "แก้ไขข้อมูลส่วนตัว");
        Member member = this.memberService.getMember();
        model.addAttribute("errorNUM",errorNum);
        
        if (!model.containsAttribute("personalInfoForm")) {
            PersonalInfoForm personalInfoForm = new PersonalInfoForm();
            personalInfoForm.setFirstName(member.getFirstName());
            personalInfoForm.setLastName(member.getLastName());
            personalInfoForm.setAddress(member.getAddress());
            personalInfoForm.setCountry(member.getCountry());
            personalInfoForm.setPhoneNo(member.getPhoneNo());
            model.addAttribute("personalInfoForm", personalInfoForm);
        }

        if (!model.containsAttribute("editPasswordForm")) {
            EditPasswordForm editPasswordForm = new EditPasswordForm();
            model.addAttribute("editPasswordForm", editPasswordForm);
        }

        if (!model.containsAttribute("receivingInfoForm")) {
            ReceivingInfoForm receivingInfoForm = new ReceivingInfoForm();
            receivingInfoForm.setPayPalAccount(member.getReceivingAccount());
            model.addAttribute("receivingInfoForm", receivingInfoForm);
        }

        if (!model.containsAttribute("paymentInfoForm")) {
            PaymentInfoForm paymentInfoForm = new PaymentInfoForm();
            paymentInfoForm.setPayPalAccount(member.getPaymentAccount());
            model.addAttribute("paymentInfoForm", paymentInfoForm);
        }
        model.addAttribute("member", member);
        model.addAttribute("countryList", new CountryList());
        return "editPersonalInfoView";
    }

    @RequestMapping(value = "/editPersonalInfo/onSubmitPersonalInfo", method = RequestMethod.POST)
    public String onSubmitPersonalInfo(@Valid @ModelAttribute("personalInfoForm") PersonalInfoForm personalInfoForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.personalInfoForm", result);
            redirectAttributes.addFlashAttribute("personalInfoForm", personalInfoForm);
            return "redirect:/editPersonalInfo1";
        } else {
            
            personalInfoForm.setAddress(new String(personalInfoForm.getAddress().getBytes("iso8859-1"), "UTF-8"));
            personalInfoForm.setFirstName(new String(personalInfoForm.getFirstName().getBytes("iso8859-1"), "UTF-8"));
            personalInfoForm.setLastName(new String(personalInfoForm.getLastName().getBytes("iso8859-1"), "UTF-8"));
            boolean isSuccess = this.memberService.editPersonalInfo(personalInfoForm);
            model.addAttribute("isSuccess", isSuccess);
            if (isSuccess) {
                model.addAttribute("text", "แก้ไขข้อมูลส่วนตัวเรียบร้อย");
                return "showView";
            } else {
                model.addAttribute("text", "แก้ไขข้อมูลส่วนตัวล้มเหลว <br> <a href =\"${pageContext.request.contextPath}/editPersonalInfo\" type = \"button\" class=\"btn btn-primary\">กลับหน้าแก้ไขข้อมูลส่วนตัว</a> ");
                return "showView";
            }
        }
    }

    @RequestMapping(value = "/editPersonalInfo/onSubmitEditPassword", method = RequestMethod.POST)
    public String onSubmitEditPassword(@Valid @ModelAttribute("editPasswordForm") EditPasswordForm editPasswordForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editPasswordForm", result);
            redirectAttributes.addFlashAttribute("editPasswordForm", editPasswordForm);
            return "redirect:/editPersonalInfo2";
        } else {
            boolean isSuccess = this.memberService.editPassword(editPasswordForm);
            model.addAttribute("isSuccess", isSuccess);
            if (isSuccess) {
                model.addAttribute("text", "เปลี่ยนรหัสผ่านเรียบร้อย");

            } else {
                if (!editPasswordForm.getNewPassword().equals(editPasswordForm.getConfirmNewPassword())) {
                    model.addAttribute("text", "รหัสผ่านใหม่ไม่ตรงกับยืนยันรหัสผ่าน");
                } else {
                    model.addAttribute("text", "เกิดปัญหาในการเปลี่ยนรหัสผ่าน");
                }
            }
            return "showView";
        }
    }

    
    @RequestMapping(value = "/editPersonalInfo/editPaymentInfo/onSubmit", method = RequestMethod.POST)
    public String onSubmitEditPaymentInfo(@Valid @ModelAttribute("paymentInfoForm") PaymentInfoForm paymentInfoForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.paymentInfoForm", result);
            redirectAttributes.addFlashAttribute("paymentInfoForm", paymentInfoForm);
            return "redirect:/editPersonalInfo3";
        } else {
            
            boolean isSuccess = this.memberService.editPaymentInfo(paymentInfoForm);
            model.addAttribute("isSuccess", isSuccess);
            if (isSuccess) {
                model.addAttribute("text", "เปลี่ยนบัญชี Paypal เรียบร้อย");
            } else {
                model.addAttribute("text", "เกิดปัญหาในการเปลี่ยนบัญชี Paypal");
            }
            return "showView";
        }
    }
    
    
    @RequestMapping(value = "/editPersonalInfo/editReceivingInfo/onSubmit", method = RequestMethod.POST)
    public String onSubmitRecievingInfo(@Valid @ModelAttribute("receivingInfoForm") ReceivingInfoForm receivingInfoForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.receivingInfoForm", result);
            redirectAttributes.addFlashAttribute("receivingInfoForm", receivingInfoForm);
            return "redirect:/editPersonalInfo4";
        } else {
            boolean isSuccess = this.memberService.editReceivingInfo(receivingInfoForm);
            model.addAttribute("isSuccess", isSuccess);
            if (isSuccess) {
                model.addAttribute("text", "เปลี่ยนบัญชีรับเงินเรียบร้อย");
            } else {
                model.addAttribute("text", "เกิดปัญหาในการเปลี่ยนบัญชีรับเงิน");
            }
            return "showView";
        }
    }

}
