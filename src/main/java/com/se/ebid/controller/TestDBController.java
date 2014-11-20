/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.controller;

import com.google.common.collect.Lists;
import com.se.ebid.entity.Blacklist;
import com.se.ebid.entity.Comment;
import com.se.ebid.entity.Complaint;
import com.se.ebid.entity.Item;
import com.se.ebid.entity.Member;
import com.se.ebid.entity.Message;
import com.se.ebid.entity.Photo;
import com.se.ebid.service.TestDBService;
import java.util.Arrays;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Quxiz
 */
@Controller
public class TestDBController {
    
    private TestDBService testDBService;
    
    @Autowired
    public void setTestDBService(TestDBService testDBService){
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
     
    @RequestMapping(value= "/testDB/member/saveMember", method = RequestMethod.POST)
    public String saveMember(@ModelAttribute Member m){
        this.testDBService.saveMember(m);
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
    
    @RequestMapping(value= "/testDB/message/saveMessage", method = RequestMethod.POST)
    public String saveMessage(@ModelAttribute Message m){
        this.testDBService.saveMessage(m);
        return "redirect:/testDB/message";
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
    
    @RequestMapping(value= "/testDB/item/saveItem", method = RequestMethod.POST)
    public String saveItem(@ModelAttribute Item m){
        this.testDBService.saveItem(m);
        return "redirect:/testDB/item";
    }
    
    @RequestMapping(value = "/testDB/item/findByItemID/{itemID}", method = RequestMethod.GET)
    public String findItemByItemID(@PathVariable("itemID") long itemID, Model model) {
        model.addAttribute("item", this.testDBService.findItemByItemID(itemID));
        List<Item> listItems = Arrays.asList(this.testDBService.findItemByItemID(itemID));
        model.addAttribute("listItems", listItems);
        return "testDBMemberView";
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
    
    @RequestMapping(value= "/testDB/photo/savePhoto", method = RequestMethod.POST)
    public String saveMessage(@ModelAttribute UploadedFile m){
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
    
    @RequestMapping(value= "/testDB/complaint/saveComplaint", method = RequestMethod.POST)
    public String saveComplaint(@ModelAttribute Complaint m){
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
    
    @RequestMapping(value= "/testDB/blacklist/saveBlacklist", method = RequestMethod.POST)
    public String saveBlacklist(@ModelAttribute Blacklist m){
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
    
    @RequestMapping(value= "/testDB/comment/saveComment", method = RequestMethod.POST)
    public String saveComplaint(@ModelAttribute Comment m){
        this.testDBService.saveComment(m);
        return "redirect:/testDB/comment";
    }
    
    @RequestMapping(value = "/testDB/comment/findByItemID/{itemID}", method = RequestMethod.GET)
    public String findCommentByItemID(@PathVariable("itemID") long itemID, Model model) {
        model.addAttribute("Comment", new Comment());
        model.addAttribute("listComments", this.testDBService.findCommentByItemID(itemID));
        return "testDBCommentView";
    }
    
    
}
