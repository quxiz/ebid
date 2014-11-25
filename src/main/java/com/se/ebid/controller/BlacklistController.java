/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Member;
import com.se.ebid.service.BlacklistService;
import com.se.ebid.service.MemberService;
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
     public String selectMember(@ModelAttribute BlacklistForm blacklistForm){            
         return "redirect:/blacklist/"+blacklistForm.getUserID();
     }
     
     @RequestMapping(value = "/blacklist/{userID}",method = RequestMethod.GET)
     public String showSelectedMember(@PathVariable ("userID") String userID ,Model model){
            Member member = this.memberService.getMemberByUserID(userID);
            BlacklistForm blacklistForm = new BlacklistForm();
            blacklistForm.setUserID(userID);
            model.addAttribute("blacklistForm", blacklistForm);
            model.addAttribute("member", member);
            return "blacklistMemberView";
     }
     
     @RequestMapping(value = "/blacklist/onSubmit",method = RequestMethod.POST)
     public String onSubmit(@ModelAttribute BlacklistForm blacklistForm)
     {
        this.blacklistService.blacklist(blacklistForm);
        return "redirect:/blacklist";
     }
}
