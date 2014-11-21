/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Item;
import com.se.ebid.entity.Photo;
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
        BuyForm buyform = new BuyForm();
        qform.setItemID(itemID);
        buyform.setItemID(itemID);
        model.addAttribute("buyform", buyform);
        model.addAttribute("qform", qform);
        model.addAttribute("item", this.itemService.getItem(itemID));
        // model.addAttribute("listPhotos",this.itemService.getPhoto(itemID));
        // model.addAttribute("listComments",this.itemService.getComment(itemID));

        List<CategoryType> categoryList = new ArrayList<CategoryType>(Arrays.asList(CategoryType.values()));
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("title", this.itemService.getItem(itemID).getTitle());
        return "viewItemView";

    }

    @RequestMapping(value = "/viewItem/onSubmitQuestionForm", method = RequestMethod.POST)
    public String onSubmitQuestionForm(@ModelAttribute QuestionForm qform) {
        //     this.commentService.askQuestion(qform);
        return "redirect:/viewItem"; //url
    }

    public void onSubmitBidForm(BidForm form) {
        //do sth
    }

    @RequestMapping(value = {"/viewItem/onSubmitBuyForm"}, method = RequestMethod.POST)
    public String onSubmitBuyForm(@ModelAttribute("buyform") BuyForm buyform,
            Model model, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("buyform", buyform);

        return "redirect:/buyItem/" + buyform.getItemID();

    }

    @RequestMapping(value = "/buyItem/{itemID}", method = RequestMethod.GET)
    public String buyItem(@ModelAttribute("buyform") BuyForm buyform, @PathVariable("itemID") long itemID, Model model) {
        Invoice invoice = this.itemService.buy(buyform);
        model.addAttribute("buyform", buyform);
        model.addAttribute("invoice", invoice);
        model.addAttribute("item", this.itemService.getItem(buyform.getItemID()));
        // model.addAttribute("listPhotos", this.itemService.getItem(buyform.getItemID()).getPhoto());
        return "buyItemView";
    }
    /*
    
     //not sure
     @RequestMapping(value = "/buyItem/{itemID}/{quantity}", method = RequestMethod.GET)
     public String buyItem(@PathVariable("itemID") long itemID,@PathVariable("quantity") long quantity, Model model) {
     BuyForm buyform=new BuyForm();
     buyform.setItemID(itemID);
     buyform.setQuantity(quantity);
     Invoice invoice = this.itemService.buy(buyform);
     model.addAttribute("invoice", invoice);
     model.addAttribute("buyform", buyform);
     model.addAttribute("item", this.itemService.getItem(itemID));
     //   model.addAttribute("listPhotos",this.itemService.getPhoto(itemID));
     return "buyItemView"; //jsp
     }*/

    @RequestMapping(value = "/buyItem/confirmBuy", method = RequestMethod.POST)
    public String confirmBuy(@ModelAttribute BuyForm buyform) {

        return "";
    }
}
