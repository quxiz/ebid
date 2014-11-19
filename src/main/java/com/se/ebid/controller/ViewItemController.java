/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Item;
import com.se.ebid.entity.Photo;
import com.se.ebid.service.ItemService;
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
public class ViewItemController {

    private ItemService itemService; //waiting for declaring itemService
    //private CommentService commentService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    /*@Autowired
     public void setCommentService(CommentService commentService) {
     this.commentService = commentService;
     }*/
    @RequestMapping(value = "/viewItem/{itemID}", method = RequestMethod.GET)
    public String viewItem(@PathVariable("itemID") long itemID, Model model) {
        QuestionForm qform = new QuestionForm();
        qform.setItemID(itemID);
        model.addAttribute("qform", qform);
        model.addAttribute("item", this.itemService.getItem(itemID));
        // model.addAttribute("listPhotos",this.itemService.getPhoto(itemID));
        // model.addAttribute("listComments",this.itemService.getComment(itemID));
        return "viewItemView";
    }

    @RequestMapping(value = "/viewItem/onSubmitQuestionForm", method = RequestMethod.POST)
    public String onSubmitQuestionForm(@ModelAttribute QuestionForm qform) {
        //     this.commentService.askQuestion(qform);
        //     return "redirect:/viewItem";
        return "redirect:/viewItemView";
    }

    public void onSubmitBidForm(BidForm form) {
        //do sth
    }

    public void onSubmitBuyForm(BuyForm form) {
        //do sth
    }

    public void confirmBuy() {
        //do sth
    }
}
