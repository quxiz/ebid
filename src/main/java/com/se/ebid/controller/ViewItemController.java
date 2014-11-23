/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Item;
import com.se.ebid.entity.Photo;
import com.se.ebid.service.CommentService;
import com.se.ebid.service.ItemService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mtmmoei
 */
@Controller
public class ViewItemController {

    private ItemService itemService; //waiting for declaring itemService
    private CommentService commentService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
     public void setCommentService(CommentService commentService) {
     this.commentService = commentService;
     }
     
    @RequestMapping(value = "/viewItem/{itemID}",method = RequestMethod.GET)
    public String viewItem(@PathVariable("itemID") long itemID, Model model) {
        QuestionForm questionForm = new QuestionForm();
        
        Item item = this.itemService.getItem(itemID);
        
        BuyForm buyForm = new BuyForm();
        BidForm bidForm = new BidForm();
        questionForm.setItemID(itemID);
        buyForm.setItemID(itemID);       
        model.addAttribute("buyForm", buyForm);
        model.addAttribute("bidForm",bidForm);
        model.addAttribute("questionForm", questionForm);
        model.addAttribute("item",item);
        model.addAttribute("listPhotos",this.itemService.getPhoto(itemID));
        model.addAttribute("listComments",this.itemService.getComment(itemID));
        model.addAttribute("title", this.itemService.getItem(itemID).getTitle());
        return "viewItemView";

    }

    @RequestMapping(value = "/viewItem/{itemID}/onSubmitQuestionForm", method = RequestMethod.POST)
    public String onSubmitQuestionForm(@PathVariable ("itemID") long itemID,@ModelAttribute("questionForm") QuestionForm questionForm) {
             this.commentService.askQuestion(questionForm);
        return "redirect:/viewItem/{itemID}"; //url item
    }

    @RequestMapping(value = "/viewItem/{itemID}/onSubmitBidForm", method = RequestMethod.POST)
    public String onSubmitBidForm(@PathVariable ("itemID") long itemID,@ModelAttribute ("bidForm") BidForm bidForm) {
        this.itemService.bid(bidForm);
        return "redirect:/viewItem/"+itemID;
    }

    @RequestMapping(value = {"/viewItem/{itemID}/onSubmitBuyForm"}, method = RequestMethod.POST)
    public String onSubmitBuyForm(@PathVariable ("itemID") long itemID,@ModelAttribute("buyForm") BuyForm buyForm,Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("buyForm", buyForm);
        return "redirect:/buyItem/"+itemID;

    }

    @RequestMapping(value = "/buyItem/{itemID}", method = RequestMethod.GET)
    public String buyItem(@ModelAttribute("buyForm") BuyForm buyForm,@PathVariable("itemID") long itemID, Model model) {
        Invoice invoice = this.itemService.buy(buyForm);
        if(invoice ==null) return "redirect:/error/Blacklisted userID can't buy any items.";
        model.addAttribute("invoice", invoice);
        model.addAttribute("item", this.itemService.getItem(itemID));
        model.addAttribute("listPhotos", this.itemService.getPhoto(itemID));
        return "buyItemView";
    }

    @RequestMapping(value = "/buyItem/{itemID}/confirmBuy", method = RequestMethod.POST)
    public String confirmBuy(@PathVariable("itemID") long itemID ,@ModelAttribute("buyForm") BuyForm buyForm) {
        long transactionID = this.itemService.confirmBuy(buyForm);
        if(transactionID<0) return "/viewItem/"+itemID;
        return "redirect:/checkOut/"+transactionID;
    }
}
