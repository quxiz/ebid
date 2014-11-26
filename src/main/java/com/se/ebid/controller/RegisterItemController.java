/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Member;
import com.se.ebid.service.ItemService;
import com.se.ebid.service.MemberService;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
    private MemberService memberService;
    
    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
    
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @InitBinder
public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy H:mm",Locale.ENGLISH);
    dateFormat.setLenient(false);

    // true passed to CustomDateEditor constructor means convert empty String to null
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
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

    public String onSubmit(@Valid @ModelAttribute("form") RegisterItemForm form, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {

        if(form.getEndTime()!=null){
            System.out.println(form.getEndTime());
        }
         
        System.out.println("REQ: reg item call");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.form", result);
            redirectAttributes.addFlashAttribute("form", form);
            return "redirect:/registerItem";
        } else {
 
            form.setDetail(new String(form.getDetail().getBytes("iso8859-1"), "UTF-8"));
            form.setPackageDetail(new String(form.getPackageDetail().getBytes("iso8859-1"), "UTF-8"));
            form.setReturnPolicy(new String(form.getReturnPolicy().getBytes("iso8859-1"), "UTF-8"));
            form.setShippingService(new String(form.getShippingService().getBytes("iso8859-1"), "UTF-8"));
            form.setSpecifics(new String(form.getSpecifics().getBytes("iso8859-1"), "UTF-8"));
            form.setTitle(new String(form.getTitle().getBytes("iso8859-1"), "UTF-8"));
            Member member = this.memberService.getMember();
            if (!member.isActivated()) {
            model.addAttribute("isSuccess", "false");
            model.addAttribute("text", "กรุณาตรวจสอบอีเมลและ activate บัญชีของคุณ!");
            return "showView";
        } else if (member.isBlacklisted()) {
            model.addAttribute("isSuccess", "false");
            model.addAttribute("text", "คุณติดบัญชีดำ");
            return "showView";
        }  
        else if(member.getReceivingAccount()==null){
                return "redirect:/editPersonalInfo4";
            }
            long itemID = this.itemService.registerItem(form);
            if (itemID < 0) {
                model.addAttribute("isSuccess", false);
                model.addAttribute("text", "ลงทะเบียนสินค้าไม่สำเร็จ <br> <a href =\"${pageContext.request.contextPath}/registerItem\" type = \"button\" class=\"btn btn-primary\">กลับหน้าลงทะเบียนสินค้า</a>");
                return "showView";
            }
            return "redirect:/viewItem/" + itemID;//รอแก้หน้าแสดง
        }

    }
}
