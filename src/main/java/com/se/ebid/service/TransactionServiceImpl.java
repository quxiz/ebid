/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.entity.Member;
import com.se.ebid.entity.Message;
import com.se.ebid.entity.Transaction;
import com.se.ebid.dao.TransactionDAO;
import com.se.ebid.dao.MemberDAO;
import com.se.ebid.dao.MessageDAO;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
/**
 *
 * @author Nuttapong
 */


public class TransactionServiceImpl implements TransactionService {

    private TransactionDAO transactionDAO;
    private MemberDAO memberDAO;
    private MessageDAO messageDAO;
    
    @Autowired
    public void setTransactionDAO(TransactionDAO transactionDAO){
        this.transactionDAO = transactionDAO;
    }
    
    @Autowired
    public void setMemberDAO(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
    }
    
    @Autowired
    public void setMessageDAO(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    
    @Override
    public Transaction getTransaction(long transactionID) {
        return this.transactionDAO.findByTransactionID(transactionID);
    }

    @Override
    @Transactional
    public Transaction setShippingService(long transactionID, String shippingService) {
        Transaction transaction = this.transactionDAO.findByTransactionID(transactionID);
        if(transaction == null) return null;
        transaction.setShippingService(shippingService);
        this.transactionDAO.save(transaction);
        return transaction;
    }

    @Override
    @Transactional
    public boolean checkOutTransaction(long transactionID) {
        Transaction transaction = this.transactionDAO.findByTransactionID(transactionID);
        if(transaction == null) return false;
        transaction.setComplated(true);
        this.transactionDAO.save(transaction);
        
        long sellerID = transaction.getSellerID();
        long buyerID = transaction.getBuyerID();
        
        Member buyer = this.memberDAO.findByMemberID(buyerID);
        if(buyer == null){
            Message messageAdmin = new Message();
            messageAdmin.setSenderID(Common.ADMIN_ID);
            messageAdmin.setReceiverID(Common.ADMIN_ID);
            messageAdmin.setMessage("ผู้ซื้อหาย");
            messageAdmin.setTimestamp(new Timestamp(System.currentTimeMillis()));
            messageAdmin.setSeen(false);
            this.messageDAO.save(messageAdmin);
        }
        else{
            sendBuyerEmail(buyer);
            Message messageBuyer = new Message();
            messageBuyer.setSenderID(Common.ADMIN_ID);
            messageBuyer.setReceiverID(buyerID);
            messageBuyer.setMessage("ข้อความผู้ซื้อ");
            messageBuyer.setTimestamp(new Timestamp(System.currentTimeMillis()));
            messageBuyer.setSeen(false);
            this.messageDAO.save(messageBuyer);
        }
        
        Member seller = this.memberDAO.findByMemberID(sellerID);
        if(seller == null){
            Message messageAdmin = new Message();
            messageAdmin.setSenderID(Common.ADMIN_ID);
            messageAdmin.setReceiverID(Common.ADMIN_ID);
            messageAdmin.setMessage("ผู้ขายหาย");
            messageAdmin.setTimestamp(new Timestamp(System.currentTimeMillis()));
            messageAdmin.setSeen(false);
            this.messageDAO.save(messageAdmin);
        }
        else{
            Message messageSeller = new Message();
            messageSeller.setSenderID(Common.ADMIN_ID);
            messageSeller.setReceiverID(sellerID);
            messageSeller.setMessage("ข้อความผู้ขาย");
            messageSeller.setTimestamp(new Timestamp(System.currentTimeMillis()));
            messageSeller.setSeen(false);
            this.messageDAO.save(messageSeller);
        }
        return true;
    }

    @Override
    public boolean sendSellerEmail(Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean sendBuyerEmail(Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
