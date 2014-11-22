/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Message;
import com.se.ebid.service.MessageService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mtmmoei
 */
@Controller
public class ViewMessageController {
    
    private MessageService messageService;
    
    @Autowired
    public void setMessageService(MessageService messageService){
     this.messageService = messageService;
    }
    
    @RequestMapping(value = "/viewMessage", method = RequestMethod.GET)
     public String viewMessage(Model model) {
        model.addAttribute("title", "ข้อความ");
        List<CategoryType> categoryList = new ArrayList<>( Arrays.asList(CategoryType.values() ));  
        model.addAttribute("categoryList", categoryList);
        List<Message> listMessages = this.messageService.getMessage();
        model.addAttribute("listMessages",listMessages); 
        return "viewMessageView";
    }  
 
}
