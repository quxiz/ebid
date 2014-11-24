/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Comment;
import com.se.ebid.service.CommentService;
import com.se.ebid.service.ItemService;
import com.se.ebid.service.MessageService;
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
    private ItemService itemService;
    @Autowired
    public void setCommentService(CommentService commentService){
        this.commentService = commentService;
    }
    
    @Autowired
    public void setItemService(ItemService itemService){
        this.itemService = itemService;
    }
    
    
    @RequestMapping(value = "/answerQuestion/{itemID}_{parentID}", method = RequestMethod.GET)
     public String viewAnswerQuestion(@PathVariable ("itemID") long itemID,@PathVariable ("parentID") long parentID, Model model) {
        model.addAttribute("title", "ตอบคำถาม");
        AnswerForm answerForm = new AnswerForm();
        answerForm.setParentID(parentID);
        answerForm.setItemID(itemID);        
        Comment comment = this.commentService.getComment(parentID);
        answerForm.setAskerID(comment.getCommenterID());
        String title = this.itemService.getItem(itemID).getTitle();
        model.addAttribute("answerForm",answerForm);
        model.addAttribute("itemTitle",title);
        model.addAttribute("comment",comment);
        return "answerQuestionView";
    }  
     
     @RequestMapping(value = "/answerQuestion/onSubmit",method = RequestMethod.POST)
     public String onSubmit(@ModelAttribute ("answerForm") AnswerForm answerForm,Model model){
            if(this.commentService.answerQuestion(answerForm))return "redirect:/viewItem/"+answerForm.getItemID();
            else {
                model.addAttribute("isSuccess",false);
                model.addAttribute("text","Error found");
                return "showView";
            }
     }
}
