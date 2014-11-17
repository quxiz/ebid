/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.controller;

import com.se.ebid.service.TestQuartzService;
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
public class TestQuartzController {
    
    private TestQuartzService testQuartzService;
    
    @Autowired
    public void setTestQuartzService(TestQuartzService testQuartzService){
        this.testQuartzService = testQuartzService;
    }
    
    @RequestMapping(value = "/testQuartz", method = RequestMethod.GET)
    public String testQuartz(Model model) {
        return "testQuartzView";
    }
    
    @RequestMapping(value = "/testQuartz/add", method = RequestMethod.GET)
    public String addQuartz(Model model) {
        this.testQuartzService.addQuartz();
        return "redirect:/testQuartz";
    }
    
}
