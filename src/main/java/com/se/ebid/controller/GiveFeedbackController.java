/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Feedback;
import com.se.ebid.entity.Item;
import com.se.ebid.service.CustomUser;
import com.se.ebid.service.FeedbackService;
import com.se.ebid.service.ItemService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class GiveFeedbackController {

    private FeedbackService feedbackService;
    private ItemService itemService;

    @Autowired
    public void setFeedbackService(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/giveFeedback/{transactionID}", method = RequestMethod.GET)
    public String viewGiveFeedback(@PathVariable("transactionID") long transactionID, Model model) {
        model.addAttribute("title", "ให้ Feedback");
        FeedbackForm feedbackForm = new FeedbackForm();
        feedbackForm.setTransactionID(transactionID);
        Feedback feedback = this.feedbackService.getFeedback(transactionID);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        long memberID = customUser.getMemberID();
        if (memberID == feedback.getSellerID() && memberID == feedback.getBuyerID()) {
            model.addAttribute("isSuccess", false);

            model.addAttribute("text", "ซื้อสินค้าของคุณเอง!!");
            model.addAttribute("link", "");
            model.addAttribute("btnText", "");

            return "showView";
        } else if (memberID == feedback.getSellerID()) {
            if (feedback.isSellerFeedbacked()) {
                model.addAttribute("isSuccess", false);

                model.addAttribute("text", "ให้ feedback แล้ว");
                model.addAttribute("link", "");
                model.addAttribute("btnText", "");
                return "showView";
            }
            model.addAttribute("isSeller", true);
            model.addAttribute("isBuyer", false);
            model.addAttribute("buyerName", feedback.getBuyerName());
            System.out.println("buyerName" + feedback.getBuyerName());
        } else if (memberID == feedback.getBuyerID()) {
            if (feedback.isBuyerFeedbacked()) {
                model.addAttribute("isSuccess", false);
                model.addAttribute("text", "ให้ feedback แล้ว");
                model.addAttribute("link", "");
                model.addAttribute("btnText", "");
                return "showView";
            }
            model.addAttribute("isSeller", false);
            model.addAttribute("isBuyer", true);
            model.addAttribute("sellerName", feedback.getSellerName());
            System.out.println("sellerName" + feedback.getSellerName());
        } else {
            System.out.println("memberID != sellerID and buyerID");
            model.addAttribute("isSuccess", false);

            model.addAttribute("text", "เกิดข้อผิดพลาด");
            model.addAttribute("link", "");
            model.addAttribute("btnText", "");
            return "showView";
        }
        Item item = this.itemService.getItem(feedback.getItemID());
        model.addAttribute("item", item);
        model.addAttribute("feedbackForm", feedbackForm);
        return "giveFeedbackView";
    }

    @RequestMapping(value = "/giveFeedback/submit", method = RequestMethod.POST)
    public String onSubmitFeedback(@ModelAttribute("feedbackForm") FeedbackForm feedbackForm, Model model) throws UnsupportedEncodingException {
        feedbackForm.setComment(new String(feedbackForm.getComment().getBytes("iso8859-1"), "UTF-8"));
        boolean success = this.feedbackService.giveFeedback(feedbackForm);
        if (success) {
            model.addAttribute("isSuccess", true);

            model.addAttribute("text", "ส่ง feedback เรียบร้อย");
            model.addAttribute("link", "");
            model.addAttribute("btnText", "");
            return "showView";
        } else {
            model.addAttribute("isSuccess", false);
            model.addAttribute("text", "กรุณาส่งอีกครั้ง");
            model.addAttribute("link", "/giveFeedback/" + feedbackForm.getTransactionID());
            model.addAttribute("btnText", "กลับไปหน้าให้ feedback");
            return "showView";
        }
    }
}
