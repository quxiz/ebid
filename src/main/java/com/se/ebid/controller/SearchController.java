/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mtmmoei
 */
@Controller
public class SearchController {
    
    @RequestMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        //new itemService = ItemService();
        //itemService.search(sf);
        model.addAttribute("keyword", keyword);
        return "searchResultView";
    }
    /*
    @RequestMapping(value= "/testDB/saveMember", method = RequestMethod.POST)
    //public String addMember(){
    public String saveMember(@ModelAttribute Member m){
        this.testDBService.saveMember(m);
        return "redirect:/testDB";
    }*/
    
}
