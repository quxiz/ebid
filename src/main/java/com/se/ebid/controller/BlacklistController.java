/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Member;
import com.se.ebid.service.BlacklistService;
import com.se.ebid.service.MemberService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mtmmoei
 */
@Controller
public class BlacklistController {
    
    private BlacklistService blacklistService;
    private MemberService memberService;
    
    @Autowired
    public void setBlacklistService(BlacklistService blacklistService){
        this.blacklistService=blacklistService;
    }
    
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    
    
    @RequestMapping("/blacklist")
     public String viewBlacklist(Model model) {
        model.addAttribute("title", "Blacklist");
        BlacklistForm blacklistForm = new BlacklistForm();
        model.addAttribute("blacklistForm", blacklistForm);
        return "blacklistView";
    }  
     
     @RequestMapping(value = "/blacklist/selectMember", method=RequestMethod.POST)
     public String selectMember(@ModelAttribute ("blacklistForm") BlacklistForm blacklistForm){            
         return "redirect:/blacklist/"+blacklistForm.getUserID();
     }
     
     @RequestMapping(value = "/blacklist/{userID}",method = RequestMethod.GET)
     public String showSelectedMember(@PathVariable ("userID") String userID ,Model model) throws UnsupportedEncodingException{
            Member member = this.memberService.getMemberByUserID(userID);
            BlacklistForm blacklistForm = new BlacklistForm();
            userID = new String(userID.getBytes("iso8859-1"), "UTF-8");
            blacklistForm.setUserID(userID);
            model.addAttribute("blacklistForm", blacklistForm);
            model.addAttribute("member", member);
            return "blacklistMemberView";
     }
     
     @RequestMapping(value = "/blacklist/onSubmit",method = RequestMethod.POST)
     public String onSubmit(@ModelAttribute ("blacklistForm") BlacklistForm blacklistForm,Model model) throws UnsupportedEncodingException
     {
         blacklistForm.setDetail(new String(blacklistForm.getDetail().getBytes("iso8859-1"), "UTF-8"));
         blacklistForm.setBlacklistStatus(new String(blacklistForm.getBlacklistStatus().getBytes("iso8859-1"), "UTF-8"));
        boolean isSuccess = this.blacklistService.blacklist(blacklistForm);
        model.addAttribute("isSuccess", isSuccess);
        if(isSuccess){
        model.addAttribute("text", "You've changed blacklist status of userID "+blacklistForm.getUserID()+" to "
                +blacklistForm.getBlacklistStatus()+". <br> <a href =\"${pageContext.request.contextPath}/blacklist\" type = \"button\" class=\"btn btn-primary\">กลับหน้าจัดการ Blacklist</a> ");
        } else { model.addAttribute("text", "Fail to change blacklist status of userID "+blacklistForm.getUserID()+". <br> <a href =\"${pageContext.request.contextPath}/blacklist\" type = \"button\" class=\"btn btn-primary\">กลับหน้าจัดการ Blacklist</a> ");
        }return "showView";
     }
}
