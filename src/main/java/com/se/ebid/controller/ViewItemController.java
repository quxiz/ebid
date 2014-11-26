/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Item;
import com.se.ebid.entity.Member;
import com.se.ebid.entity.Photo;
import com.se.ebid.service.CommentService;
import com.se.ebid.service.CustomUser;
import com.se.ebid.service.ItemService;
import com.se.ebid.service.MemberService;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private MemberService memberService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    private void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/viewItem/{itemID}", method = RequestMethod.GET)
    public String viewItem(@PathVariable("itemID") long itemID, Model model) {
        QuestionForm questionForm = new QuestionForm();
        Item item = this.itemService.getItem(itemID);
        BuyForm buyForm = new BuyForm();
        BidForm bidForm = new BidForm();
        questionForm.setItemID(itemID);
        buyForm.setItemID(itemID);
        model.addAttribute("buyForm", buyForm);
        model.addAttribute("bidForm", bidForm);
        model.addAttribute("questionForm", questionForm);
        model.addAttribute("item", item);
        model.addAttribute("listPhotos", this.itemService.getPhoto(itemID));
        model.addAttribute("listComments", this.itemService.getComment(itemID));
        if (item.getShippingService() != null && item.getShippingCost() != null) {
            String[] shippingServices = item.getShippingService().split(" ");
            String[] shippingCosts = item.getShippingCost().split(" ");
            model.addAttribute("shippingServices", shippingServices);
            model.addAttribute("shippingCosts", shippingCosts);
        }
        if (item != null) {
            model.addAttribute("title", item.getTitle());
        }
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        if (item.getEndTime() != null) {
            model.addAttribute("showBidForm", item.getEndTime().after(timestamp));
        }
        if (item.getSellingType() == SellingType.BID) {
            model.addAttribute("maxbidID", this.itemService.getMaxBidderID(itemID));
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                CustomUser customUser = (CustomUser) auth.getPrincipal();
                model.addAttribute("yourID", customUser.getMemberID());
            }
        }
        return "viewItemView";

    }

    @RequestMapping(value = "/viewItem/{itemID}/onSubmitQuestionForm", method = RequestMethod.POST)
    public String onSubmitQuestionForm(@PathVariable("itemID") long itemID, @ModelAttribute("questionForm") QuestionForm questionForm, Model model) throws UnsupportedEncodingException {
        questionForm.setSellerID(this.itemService.getItem(itemID).getSellerID());
        questionForm.setQuestion(new String(questionForm.getQuestion().getBytes("iso8859-1"), "UTF-8"));
        if (this.commentService.askQuestion(questionForm)) {
            return "redirect:/viewItem/" + itemID;
        } else {
            model.addAttribute("isSuccess", false);
            model.addAttribute("text", "There is a problem when sending your question. ");
            model.addAttribute("link", "/viewItem/" + itemID);
            model.addAttribute("btnText", "กลับหน้าดูข้อมูลสินค้า");
            return "showView";
        }
    }

    @RequestMapping(value = "/viewItem/{itemID}/onSubmitBidForm", method = RequestMethod.POST)
    public String onSubmitBidForm(@PathVariable("itemID") long itemID, @ModelAttribute("bidForm") BidForm bidForm, Model model) {
        Member member = this.memberService.getMember();
        if (!member.isActivated()) {
<<<<<<< HEAD
            model.addAttribute("isSuccess", false);
            model.addAttribute("text", "Please, check your email and activate your account!");
            model.addAttribute("link", "");
            model.addAttribute("btnText", "");
            return "showView";
        } else if (member.isBlacklisted()) {
            model.addAttribute("isSuccess", false);
            model.addAttribute("text", "You are blacklisted");
            model.addAttribute("link", "");
            model.addAttribute("btnText", "");
=======
            model.addAttribute("isSuccess", "false");
            model.addAttribute("text", "กรุณาตรวจสอบอีเมลและ activate บัญชี!");
            return "showView";
        } else if (member.isBlacklisted()) {
            model.addAttribute("isSuccess", "false");
            model.addAttribute("text", "คุณติดบัญชีดำ");
>>>>>>> 6f7d1d9d9c7dd6662ae6740312b96bb6750fad43
            return "showView";
        } else if (member.getPaymentAccount() == null) {
            return "redirect:/editPersonalInfo3";
        }
<<<<<<< HEAD
        if (this.itemService.bid(bidForm) != 1) {
            model.addAttribute("isSuccess", false);
            model.addAttribute("text", "There is a problem, you can't bid. ");
            model.addAttribute("link", "/viewItem/" + itemID);
            model.addAttribute("btnText", "กลับหน้าดูข้อมูลสินค้า");
            return "showView";
        } else {
=======
        if(this.itemService.bid(bidForm)!=1){
            model.addAttribute("isSuccess",false);
            model.addAttribute("text","เกิดข้อพลาด ไม่สามารถประมูลได้ <br> <a href =\"${pageContext.request.contextPath}/viewItem/\""+itemID+" type = \"button\" class=\"btn btn-primary\">กลับหน้าดูข้อมูลสินค้า</a> ");
        return "showView";
        }
        else {
>>>>>>> 6f7d1d9d9c7dd6662ae6740312b96bb6750fad43
            return "redirect:/viewItem/" + itemID;
        }
    }

    @RequestMapping(value = {"/viewItem/{itemID}/onSubmitBuyForm"}, method = RequestMethod.POST)
    public String onSubmitBuyForm(@PathVariable("itemID") long itemID, @ModelAttribute("buyForm") BuyForm buyForm, Model model, RedirectAttributes redirectAttributes) {
        Member member = this.memberService.getMember();
<<<<<<< HEAD
        model.addAttribute("link", "");
        model.addAttribute("btnText", "");
        if (buyForm.getQuantity() <= 0) {
            model.addAttribute("isSuccess", false);
            model.addAttribute("text", "Invalid quantity");
            return "showView";
        } else if (!member.isActivated()) {
            model.addAttribute("isSuccess", false);
            model.addAttribute("text", "Please, activate your account!");
            return "showView";
        } else if (member.isBlacklisted()) {
            model.addAttribute("isSuccess", false);
            model.addAttribute("text", "You are blacklisted");
=======
        if (buyForm.getQuantity() <= 0 && buyForm.getQuantity()>this.itemService.getItem(buyForm.getItemID()).getQuantity()) {
            model.addAttribute("isSuccess", "false");
            model.addAttribute("text", "จำนวนสินค้าไม่ถูกต้อง");
            return "showView";
        } else if (!member.isActivated()) {
            model.addAttribute("isSuccess", "false");
            model.addAttribute("text", "กรุณา activate บัญชี");
            return "showView";
        } else if (member.isBlacklisted()) {
            model.addAttribute("isSuccess", "false");
            model.addAttribute("text", "คุณติดบัญชีดำ");
>>>>>>> 6f7d1d9d9c7dd6662ae6740312b96bb6750fad43
            return "showView";
        } else if (member.getPaymentAccount() == null) {
            return "redirect:/editPersonalInfo3";
        }
        redirectAttributes.addFlashAttribute("buyForm", buyForm);
        return "redirect:/buyItem/" + itemID;

    }

    @RequestMapping(value = "/buyItem/{itemID}", method = RequestMethod.GET)
    public String buyItem(@ModelAttribute("buyForm") BuyForm buyForm, @PathVariable("itemID") long itemID, Model model) {
        Invoice invoice = this.itemService.buy(buyForm);
        model.addAttribute("link", "/viewItem/"+itemID);
        model.addAttribute("btnText", "กลับหน้าดูข้อมูลสินค้า");
        if (invoice.getItemID() == ItemService.ERR_BLACKLIST) {
<<<<<<< HEAD
            model.addAttribute("isSuccess", false);
            model.addAttribute("text", "Your userID is in the blacklist");
            return "showView";
        }
        if (invoice.getItemID() == ItemService.ERR_NOT_ENOUGH_QTY) {
            model.addAttribute("isSuccess", false);
            model.addAttribute("text", "Quantity exceed");
            return "showView";
        }
        if (invoice.getItemID() < 0) {
            model.addAttribute("isSuccess", false);
            model.addAttribute("text", "Error found");
=======
            model.addAttribute("isSuccess", "false");
            model.addAttribute("text", "คุณติดบัญชีดำ");
            return "showView";
        }
        if (invoice.getItemID() == ItemService.ERR_NOT_ENOUGH_QTY) {
            model.addAttribute("isSuccess", "false");
            model.addAttribute("text", "จำนวนสินค้าเกิน");
            return "showView";
        }
        if (invoice.getItemID() < 0) {
            model.addAttribute("isSuccess", "false");
            model.addAttribute("text", "เกิดข้อผิดพลาด");
>>>>>>> 6f7d1d9d9c7dd6662ae6740312b96bb6750fad43
            return "showView";
        }
        model.addAttribute("buyForm", buyForm);
        model.addAttribute("invoice", invoice);
        model.addAttribute("item", this.itemService.getItem(itemID));
        model.addAttribute("listPhotos", this.itemService.getPhoto(itemID));
        return "buyItemView";
    }

    @RequestMapping(value = "/buyItem/{itemID}/confirmBuy", method = RequestMethod.POST)
    public String confirmBuy(@PathVariable("itemID") long itemID, @ModelAttribute("buyForm") BuyForm buyForm) {
        long transactionID = this.itemService.confirmBuy(buyForm);
        if (transactionID < 0) {
            return "/viewItem/" + itemID;
        }
        return "redirect:/checkOut/" + transactionID;
    }
}
