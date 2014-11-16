/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.controller;

import com.se.ebid.entity.Member;
import com.se.ebid.service.TestDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Quxiz
 */
@Controller
public class TestDBController {
    
    private TestDBService testDBService;
    
    @Autowired
    public void setTestDBService(TestDBService testDBService){
        this.testDBService = testDBService;
    }
    
    @RequestMapping(value = "/testDB", method = RequestMethod.GET)
    public String testDB(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("listMembers", this.testDBService.listMembers());
        return "testDBView";
    }
     
    @RequestMapping(value= "/testDB/saveMember", method = RequestMethod.POST)
    //public String addMember(){
    public String saveMember(@ModelAttribute Member m){
        this.testDBService.saveMember(m);
        return "redirect:/testDB";
    }
    
    @RequestMapping(value = "/testDB/viewMember/{memberID}", method = RequestMethod.GET)
    public String viewMember(@PathVariable("memberID") long memberID, Model model) {
        model.addAttribute("member", this.testDBService.findByMemberID(memberID));
        model.addAttribute("listMembers", this.testDBService.listMembers());
        return "testDBView";
    }
    
}
