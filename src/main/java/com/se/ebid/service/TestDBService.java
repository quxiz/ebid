/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.service;

import com.se.ebid.controller.SearchForm;
import com.se.ebid.controller.UploadedFile;
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
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface TestDBService {
    
    public void saveMember(Member m);
    public List<Member> listMembers();
    public Member findMemberByMemberID(long memberID);
    public Member findMemberByUserID(String userID);
    public Member findMemberByActivateKey(String activateKey);
    public Member findMemberByEmail(String email);
    
    public void saveMessage(Message message);
    public List<Message> listMessages();
    public List<Message> getMessages(long receiverID);
    
    public void saveItem(Item item);
    public List<Item> listItems();
    public Item findItemByItemID(long itemID);
    public List<Item> searchItem(SearchForm searchForm);
    
    public void savePhoto(UploadedFile uploadedFile);
    public List<Photo> listPhotos();
    public List<Photo> findPhotoByItemID(long itemID);
    
    public void saveComplaint(Complaint complaint);
    public List<Complaint> listComplaints();
    public Complaint findComplaintByComplaintID(long complaintID);
    public List<Complaint> getComplaint();
    
    public void saveBlacklist(Blacklist blacklist);
    public List<Blacklist> listBlacklists();
    
    public void saveComment(Comment comment);
    public List<Comment> listComments();
    public List<Comment> findCommentByItemID(long itemID);

    public List<Feedback> listFeedbacks();
    public void saveFeedback(Feedback m);
    public Feedback findFeedbackByTransactionID(long transactionID);
    public List<Feedback> findFeedbackBySellerID(long sellerID);
    public List<Feedback> findFeedbackByBuyerID(long buyerID);
    
    public List<Transaction> listTransactions();
    public void saveTransaction(Transaction m);
    public Transaction findTransactionByTransactionID(long transactionID);
    public List<Transaction> findTransactionBySellerID(long sellerID);
    public List<Transaction> findTransactionByBuyerID(long buyerID);
    public List<Transaction> findCompletedByTimestamp();

    public List<AutoBid> listAutoBids();
    public void saveAutoBid(AutoBid m);
    public AutoBid findAutoBidByItemID(long itemID);
}
