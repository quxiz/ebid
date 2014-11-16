/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.controller;

import com.se.ebid.entity.Member;
import com.se.ebid.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Quxiz
 */
@Controller
public class MemberController {
    
    private MemberService memberService;
    
    @Autowired
    public void setPersonService(MemberService memberService){
        this.memberService = memberService;
    }
    
    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String listMembers(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("listMembers", this.memberService.listMembers());
        return "memberView";
    }
     
    //For add and update person both
    @RequestMapping(value= "/members/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("member") Member m){
         
        if(m.getMemberID() == 0){
            //new person, add it
            this.memberService.saveMember(m);
        }else{
            //existing person, call update
            this.memberService.saveMember(m);
        }
         
        return "redirect:/members";
         
    }
}
