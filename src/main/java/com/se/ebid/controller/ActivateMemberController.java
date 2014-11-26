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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mtmmoei
 */
@Controller
public class ActivateMemberController {
    
    private MemberService memberService;
    @Autowired
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }
    
    @RequestMapping(value = "/activateMember/{activateKey}", method = RequestMethod.GET)
     public String viewActivateMember(@PathVariable ("activateKey") String activateKey,Model model) {
        model.addAttribute("title", "Activate Account");
        
        boolean isSuccess = this.memberService.activateMember(activateKey);
        model.addAttribute("isSuccess", isSuccess);
        if(isSuccess){
            model.addAttribute("text", "Activate สำเร็จ");
        }else {model.addAttribute("text", "เกิดปัญหาบางอย่าง");}
        return "showView";
    }  
}
