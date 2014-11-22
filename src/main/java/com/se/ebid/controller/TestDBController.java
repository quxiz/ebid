/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.google.common.collect.Lists;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.se.ebid.entity.AutoBid;
import com.se.ebid.entity.Blacklist;
import com.se.ebid.entity.Comment;
import com.se.ebid.entity.Complaint;
import com.se.ebid.entity.Feedback;
import com.se.ebid.entity.Item;
import com.se.ebid.entity.Member;
import com.se.ebid.entity.Message;
import com.se.ebid.entity.Photo;
import com.se.ebid.entity.Transaction;
import com.se.ebid.service.TestDBService;
import java.util.Arrays;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Quxiz
 */
@Controller
public class TestDBController {

    private TestDBService testDBService;

    @Autowired
    public void setTestDBService(TestDBService testDBService) {
        this.testDBService = testDBService;
    }

    @RequestMapping(value = "/testDB", method = RequestMethod.GET)
    public String testDB(Model model) {
        return "testDBView";
    }
    /*
     ===============================================================
     ========================== Member =============================
     ===============================================================
     */

    @RequestMapping(value = "/testDB/member", method = RequestMethod.GET)
    public String testDBMember(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("listMembers", this.testDBService.listMembers());
        return "testDBMemberView";
    }

    @RequestMapping(value = "/testDB/member/saveMember", method = RequestMethod.POST)
    public String saveMember(@ModelAttribute Member m) {
        try {
            this.testDBService.saveMember(m);
        } catch (ConstraintViolationException e) {
            System.out.println("catch success");
            System.out.println(e.getCause());
        }
        return "redirect:/testDB/member";
    }

    @RequestMapping(value = "/testDB/member/viewMember/{memberID}", method = RequestMethod.GET)
    public String viewMember(@PathVariable("memberID") long memberID, Model model) {
        model.addAttribute("member", this.testDBService.findMemberByMemberID(memberID));
        model.addAttribute("listMembers", this.testDBService.listMembers());
        return "testDBMemberView";
    }

    @RequestMapping(value = "/testDB/member/findByMemberID/{memberID}", method = RequestMethod.GET)
    public String findMemberByMemberID(@PathVariable("memberID") long memberID, Model model) {
        model.addAttribute("member", this.testDBService.findMemberByMemberID(memberID));
        List<Member> listMembers = Arrays.asList(this.testDBService.findMemberByMemberID(memberID));
        model.addAttribute("listMembers", listMembers);
        return "testDBMemberView";
    }

    @RequestMapping(value = "/testDB/member/findByUserID/{userID}", method = RequestMethod.GET)
    public String findMemberByUserID(@PathVariable("userID") String userID, Model model) {
        model.addAttribute("member", this.testDBService.findMemberByUserID(userID));
        List<Member> listMembers = Arrays.asList(this.testDBService.findMemberByUserID(userID));
        model.addAttribute("listMembers", listMembers);
        return "testDBMemberView";
    }

    @RequestMapping(value = "/testDB/member/findByActivateKey/{activateKey}", method = RequestMethod.GET)
    public String findMemberByActivateKey(@PathVariable("activateKey") String activateKey, Model model) {
        model.addAttribute("member", this.testDBService.findMemberByActivateKey(activateKey));
        List<Member> listMembers = Arrays.asList(this.testDBService.findMemberByActivateKey(activateKey));
        model.addAttribute("listMembers", listMembers);
        return "testDBMemberView";
    }

    @RequestMapping(value = "/testDB/member/findByEmail/{email}", method = RequestMethod.GET)
    public String findMemberByEmail(@PathVariable("email") String email, Model model) {
        model.addAttribute("member", this.testDBService.findMemberByEmail(email));
        List<Member> listMembers = Arrays.asList(this.testDBService.findMemberByEmail(email));
        model.addAttribute("listMembers", listMembers);
        return "testDBMemberView";
    }

    /*
     ===============================================================
     ========================== Message =============================
     ===============================================================
     */
    @RequestMapping(value = "/testDB/message", method = RequestMethod.GET)
    public String testDBMessage(Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("listMessages", this.testDBService.listMessages());
        return "testDBMessageView";
    }

    /*@RequestMapping(value= "/testDB/message/saveMessage", method = RequestMethod.POST)
     public String saveMessage(@ModelAttribute Message m){
     this.testDBService.saveMessage(m);
     return "redirect:/testDB/message";
     }*/
    @RequestMapping(value = "/testDB/message/saveMessage", method = RequestMethod.POST)
    public String saveMessage(@ModelAttribute Message m, Model model, BindingResult mapping1BindingResult, RedirectAttributes redirectAttributes) {
        this.testDBService.saveMessage(m);
        redirectAttributes.addFlashAttribute("message", m);
        return "redirect:/testDB/message/testRedirect";
    }

    @RequestMapping(value = "/testDB/message/testRedirect", method = RequestMethod.GET)
    public String testRedirect(@ModelAttribute Message message, Model model, BindingResult mapping1BindingResult) {
        model.addAttribute("message", message);
        model.addAttribute("listMessages", this.testDBService.listMessages());
        return "testDBMessageView";
    }

    @RequestMapping(value = "/testDB/message/findByReceiverID/{receiverID}", method = RequestMethod.GET)
    public String findMessageByReceiverID(@PathVariable("receiverID") long receiverID, Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("listMessages", this.testDBService.getMessages(receiverID));
        return "testDBMessageView";
    }
    /*
     ===============================================================
     ========================== Item =============================
     ===============================================================
     */

    @RequestMapping(value = "/testDB/item", method = RequestMethod.GET)
    public String testDBItem(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("listItems", this.testDBService.listItems());
        return "testDBItemView";
    }

    @RequestMapping(value = "/testDB/item/saveItem", method = RequestMethod.POST)
    public String saveItem(@ModelAttribute Item m) {
        this.testDBService.saveItem(m);
        return "redirect:/testDB/item";
    }

    @RequestMapping(value = "/testDB/item/findByItemID/{itemID}", method = RequestMethod.GET)
    public String findItemByItemID(@PathVariable("itemID") long itemID, Model model) {
        model.addAttribute("item", this.testDBService.findItemByItemID(itemID));
        List<Item> listItems = Arrays.asList(this.testDBService.findItemByItemID(itemID));
        model.addAttribute("listItems", listItems);
        return "testDBItemView";
    }
    /*
     ===============================================================
     ========================== Photo =============================
     ===============================================================
     */

    @RequestMapping(value = "/testDB/photo", method = RequestMethod.GET)
    public String testDBPhoto(Model model) {
        model.addAttribute("uploadedFile", new UploadedFile());
        model.addAttribute("listPhotos", this.testDBService.listPhotos());
        return "testDBPhotoView";
    }

    @RequestMapping(value = "/testDB/photo/savePhoto", method = RequestMethod.POST)
    public String saveMessage(@ModelAttribute UploadedFile m) {
        this.testDBService.savePhoto(m);
        return "redirect:/testDB/photo";
    }

    @RequestMapping(value = "/testDB/photo/findByItemID/{itemID}", method = RequestMethod.GET)
    public String findPhotoByItemID(@PathVariable("itemID") long itemID, Model model) {
        model.addAttribute("uploadedFile", new UploadedFile());
        model.addAttribute("listItems", this.testDBService.findPhotoByItemID(itemID));
        return "testDBPhotoView";
    }
    /*
     ===============================================================
     ========================== Complaint =============================
     ===============================================================
     */

    @RequestMapping(value = "/testDB/complaint", method = RequestMethod.GET)
    public String testDBComplaint(Model model) {
        model.addAttribute("complaint", new Complaint());
        model.addAttribute("listComplaints", this.testDBService.listComplaints());
        return "testDBComplaintView";
    }

    @RequestMapping(value = "/testDB/complaint/saveComplaint", method = RequestMethod.POST)
    public String saveComplaint(@ModelAttribute Complaint m) {
        this.testDBService.saveComplaint(m);
        return "redirect:/testDB/complaint";
    }

    @RequestMapping(value = "/testDB/complaint/findByComplaintID/{complaintID}", method = RequestMethod.GET)
    public String findComplaintByComplaintID(@PathVariable("complaintID") long complaintID, Model model) {
        model.addAttribute("complaint", new Complaint());
        List<Complaint> listComplaints = Arrays.asList(this.testDBService.findComplaintByComplaintID(complaintID));
        model.addAttribute("listComplaints", listComplaints);
        return "testDBComplaintView";
    }

    @RequestMapping(value = "/testDB/complaint/getComplaint", method = RequestMethod.GET)
    public String getComplaint(Model model) {
        model.addAttribute("complaint", new Complaint());
        model.addAttribute("listComplaints", this.testDBService.getComplaint());
        return "testDBComplaintView";
    }
    /*
     ===============================================================
     ========================== Blacklist =============================
     ===============================================================
     */

    @RequestMapping(value = "/testDB/blacklist", method = RequestMethod.GET)
    public String testDBBlacklist(Model model) {
        model.addAttribute("blacklist", new Blacklist());
        model.addAttribute("listBlacklists", this.testDBService.listBlacklists());
        return "testDBBlacklistView";
    }

    @RequestMapping(value = "/testDB/blacklist/saveBlacklist", method = RequestMethod.POST)
    public String saveBlacklist(@ModelAttribute Blacklist m) {
        this.testDBService.saveBlacklist(m);
        return "redirect:/testDB/blacklist";
    }

    /*
     ===============================================================
     ========================== Comment =============================
     ===============================================================
     */
    @RequestMapping(value = "/testDB/comment", method = RequestMethod.GET)
    public String testDBComment(Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("listComments", this.testDBService.listComments());
        return "testDBCommentView";
    }

    @RequestMapping(value = "/testDB/comment/saveComment", method = RequestMethod.POST)
    public String saveComment(@ModelAttribute Comment m) {
        this.testDBService.saveComment(m);
        return "redirect:/testDB/comment";
    }

    @RequestMapping(value = "/testDB/comment/findByItemID/{itemID}", method = RequestMethod.GET)
    public String findCommentByItemID(@PathVariable("itemID") long itemID, Model model) {
        model.addAttribute("Comment", new Comment());
        model.addAttribute("listComments", this.testDBService.findCommentByItemID(itemID));
        return "testDBCommentView";
    }

    /*
     ===============================================================
     ========================== Feedback =============================
     ===============================================================
     */
    @RequestMapping(value = "/testDB/feedback", method = RequestMethod.GET)
    public String testDBFeedback(Model model) {
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("listFeedbacks", this.testDBService.listFeedbacks());
        return "testDBFeedbackView";
    }

    @RequestMapping(value = "/testDB/feedback/saveFeedback", method = RequestMethod.POST)
    public String saveFeedback(@ModelAttribute Feedback m) {
        this.testDBService.saveFeedback(m);
        return "redirect:/testDB/feedback";
    }

    @RequestMapping(value = "/testDB/feedback/findByTransactionID/{transactionID}", method = RequestMethod.GET)
    public String findFeedbackByFeedbackID(@PathVariable("transactionID") long transactionID, Model model) {
        model.addAttribute("feedback", this.testDBService.findFeedbackByTransactionID(transactionID));
        List<Feedback> listFeedbacks = Arrays.asList(this.testDBService.findFeedbackByTransactionID(transactionID));
        model.addAttribute("listFeedbacks", listFeedbacks);
        return "testDBFeedbackView";
    }

    @RequestMapping(value = "/testDB/feedback/findBySellerID/{sellerID}", method = RequestMethod.GET)
    public String findFeedbackBySellerID(@PathVariable("sellerID") long sellerID, Model model) {
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("listFeedbacks", this.testDBService.findFeedbackBySellerID(sellerID));
        return "testDBFeedbackView";
    }

    @RequestMapping(value = "/testDB/feedback/findByBuyerID/{buyerID}", method = RequestMethod.GET)
    public String findFeedbackByBuyerID(@PathVariable("buyerID") long buyerID, Model model) {
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("listFeedbacks", this.testDBService.findFeedbackByBuyerID(buyerID));
        return "testDBFeedbackView";
    }

    /*
     ===============================================================
     ========================== Transaction =============================
     ===============================================================
     */
    @RequestMapping(value = "/testDB/transaction", method = RequestMethod.GET)
    public String testDBTransaction(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("listTransactions", this.testDBService.listTransactions());
        return "testDBTransactionView";
    }

    @RequestMapping(value = "/testDB/transaction/saveTransaction", method = RequestMethod.POST)
    public String saveTransaction(@ModelAttribute Transaction m) {
        this.testDBService.saveTransaction(m);
        return "redirect:/testDB/transaction";
    }

    @RequestMapping(value = "/testDB/transaction/viewTransaction/{transactionID}", method = RequestMethod.GET)
    public String viewTransaction(@PathVariable("transactionID") long transactionID, Model model) {
        model.addAttribute("transaction", this.testDBService.findTransactionByTransactionID(transactionID));
        List<Transaction> listTransactions = Arrays.asList(this.testDBService.findTransactionByTransactionID(transactionID));
        model.addAttribute("listTransactions", listTransactions);
        return "testDBTransactionView";
    }

    @RequestMapping(value = "/testDB/transaction/findByTransactionID/{transactionID}", method = RequestMethod.GET)
    public String findTransactionByTransactionID(@PathVariable("transactionID") long transactionID, Model model) {
        model.addAttribute("transaction", this.testDBService.findTransactionByTransactionID(transactionID));
        List<Transaction> listTransactions = Arrays.asList(this.testDBService.findTransactionByTransactionID(transactionID));
        model.addAttribute("listTransactions", listTransactions);
        return "testDBTransactionView";
    }

    @RequestMapping(value = "/testDB/transaction/findBySellerID/{sellerID}", method = RequestMethod.GET)
    public String findTransactionBySellerID(@PathVariable("sellerID") long sellerID, Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("listTransactions", this.testDBService.findTransactionBySellerID(sellerID));
        return "testDBTransactionView";
    }

    @RequestMapping(value = "/testDB/transaction/findByBuyerID/{buyerID}", method = RequestMethod.GET)
    public String findTransactionByBuyerID(@PathVariable("buyerID") long buyerID, Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("listTransactions", this.testDBService.findTransactionByBuyerID(buyerID));
        return "testDBTransactionView";
    }

    @RequestMapping(value = "/testDB/transaction/findCompletedByTimestamp", method = RequestMethod.GET)
    public String findCompletedByTimestamp(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("listTransactions", this.testDBService.findCompletedByTimestamp());
        return "testDBTransactionView";
    }

    /*
     ===============================================================
     ========================== AutoBid =============================
     ===============================================================
     */
    @RequestMapping(value = "/testDB/autoBid", method = RequestMethod.GET)
    public String testDBAutoBid(Model model) {
        model.addAttribute("autoBid", new AutoBid());
        model.addAttribute("listAutoBids", this.testDBService.listAutoBids());
        return "testDBAutoBidView";
    }

    @RequestMapping(value = "/testDB/autoBid/saveAutoBid", method = RequestMethod.POST)
    public String saveAutoBid(@ModelAttribute AutoBid m) {
        this.testDBService.saveAutoBid(m);
        return "redirect:/testDB/autoBid";
    }

    @RequestMapping(value = "/testDB/autoBid/findByItemID/{itemID}", method = RequestMethod.GET)
    public String findAutoBidByItemID(@PathVariable("itemID") long itemID, Model model) {
        model.addAttribute("autoBid", this.testDBService.findAutoBidByItemID(itemID));
        List<AutoBid> listAutoBids = Arrays.asList(this.testDBService.findAutoBidByItemID(itemID));
        model.addAttribute("listAutoBids", listAutoBids);
        return "testDBAutoBidView";
    }

}
