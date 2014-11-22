/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.service.CommentService;
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
public class AnswerQuestionController {
    
    private CommentService commentService;
    
    @Autowired
    public void setCommentService(CommentService commentService){
        this.commentService = commentService;
    }
    
    @RequestMapping(value = "/answerQuestion/{parentID}", method = RequestMethod.GET)
     public String viewAnswerQuestion(@PathVariable ("parentID") long parentID, Model model) {
        model.addAttribute("title", "ตอบคำถาม");
        AnswerForm answerForm = new AnswerForm();
        answerForm.setParentID(parentID);
        model.addAttribute("answerForm",answerForm);
        return "answerQuestionView";
    }  
     
     @RequestMapping(value = "/answerQuestion/onSubmit",method = RequestMethod.POST)
     public String onSubmit(@ModelAttribute AnswerForm answerForm){
         this.commentService.answerQuestion(answerForm);
         return "redirect:/viewMessage";// ไปหน้าไหนไม่รุดี หน้าตอบคำถามเสร็จสิ้น?
     }
}
