/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import static com.se.ebid.controller.SellingType.BID;
import static com.se.ebid.controller.SellingType.BUY;
import com.se.ebid.dao.ItemDAO;
import com.se.ebid.entity.Member;
import com.se.ebid.entity.Message;
import com.se.ebid.entity.Transaction;
import com.se.ebid.dao.TransactionDAO;
import com.se.ebid.dao.MemberDAO;
import com.se.ebid.dao.MessageDAO;
import com.se.ebid.entity.Item;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nuttapong
 */

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionDAO transactionDAO;
    private MemberDAO memberDAO;
    private MessageDAO messageDAO;
    private ItemDAO itemDAO;

    @Autowired
    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Autowired
    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
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
        if (transaction == null) {
            return null;
        }
        transaction.setShippingService(shippingService);
        this.transactionDAO.save(transaction);
        return transaction;
    }

    @Override
    @Transactional
    public boolean checkOutTransaction(long transactionID) {
        Transaction transaction = this.transactionDAO.findByTransactionID(transactionID);
        if (transaction == null) {
            return false;
        }
        transaction.setCompleted(true);
        this.transactionDAO.save(transaction);

        long sellerID = transaction.getSellerID();
        long buyerID = transaction.getBuyerID();

        Member buyer = this.memberDAO.findByMemberID(buyerID);
        if (buyer == null) {
            Message messageAdmin = new Message();
            messageAdmin.setSenderID(Common.ADMIN_ID);
            messageAdmin.setReceiverID(Common.ADMIN_ID);
            messageAdmin.setMessage("Buyer is unknown<br/>"
            + "BuyerID: " + buyerID + "<br/>"
            + "ItemID: " + transaction.getItemID());
            messageAdmin.setTimestamp(new Timestamp(System.currentTimeMillis()));
            messageAdmin.setSeen(false);
            this.messageDAO.save(messageAdmin);
        } else {
            sendBuyerEmail(buyer, transaction);
            Message messageBuyer = new Message();
            messageBuyer.setSenderID(Common.ADMIN_ID);
            messageBuyer.setReceiverID(buyerID);
            Item item = this.itemDAO.findByItemID(transaction.getItemID());
            if(item.getSellingType() == BUY){
                messageBuyer.setMessage("Transaction is completed!<br/>"
                + "To enter the feedback for your seller, click on the link below (or copy and paste the URL into your browser): <br/>"
                + "<a href=\"" + Common.BASE_URL + Common.GIVE_FEEDBACK_URL + transaction.getTransactionID() + "\">" 
                + Common.BASE_URL + Common.GIVE_FEEDBACK_URL + transaction.getTransactionID() + "</a>");
            }
            if(item.getSellingType() == BID){
                messageBuyer.setMessage("The transaction is completed!"
                    +"The transaction ID " + transaction.getTransactionID() + " is completed.");
            }
            messageBuyer.setTimestamp(new Timestamp(System.currentTimeMillis()));
            messageBuyer.setSeen(false);
            this.messageDAO.save(messageBuyer);
        }

        Member seller = this.memberDAO.findByMemberID(sellerID);
        if (seller == null) {
            Message messageAdmin = new Message();
            messageAdmin.setSenderID(Common.ADMIN_ID);
            messageAdmin.setReceiverID(Common.ADMIN_ID);
            messageAdmin.setMessage("Seller is unknown<br/>"
            + "SellerID: " + sellerID + "<br/>"
            + "ItemID: " + transaction.getItemID());
            messageAdmin.setTimestamp(new Timestamp(System.currentTimeMillis()));
            messageAdmin.setSeen(false);
            this.messageDAO.save(messageAdmin);
        } else {
            sendSellerEmail(seller, transaction);
            Message messageSeller = new Message();
            messageSeller.setSenderID(Common.ADMIN_ID);
            messageSeller.setReceiverID(sellerID);
            messageSeller.setMessage("The transaction is completed!"
                + "To enter the feedback for your seller, click on the link below (or copy and paste the URL into your browser): \n"
                + "<a href=\"" + Common.BASE_URL + Common.GIVE_FEEDBACK_URL + transaction.getTransactionID() + "\">" 
                + Common.BASE_URL + Common.GIVE_FEEDBACK_URL + transaction.getTransactionID() + "</a>");
            messageSeller.setTimestamp(new Timestamp(System.currentTimeMillis()));
            messageSeller.setSeen(false);
            this.messageDAO.save(messageSeller);
        }
        return true;
    }

    private boolean sendSellerEmail(Member member, Transaction transaction) {
        long itemID = transaction.getItemID();
        Item item = this.itemDAO.findByItemID(itemID);
        if(item.getSellingType() == BUY){
            return Common.sendMail(member.getEmail(), "[ebid] The transaction is completed!",
                "To enter the feedback for your seller, click on the link below (or copy and paste the URL into your browser): \n"
                + Common.BASE_URL + Common.GIVE_FEEDBACK_URL + transaction.getTransactionID());
        }
        if(item.getSellingType() == BID){
            return Common.sendMail(member.getEmail(), "[ebid] The transaction is completed!",
                "The transaction ID "
                + transaction.getTransactionID()
                + " is completed.");
        }
        return false;
    }

    private boolean sendBuyerEmail(Member member, Transaction transaction) {
        return Common.sendMail(member.getEmail(), "[ebid] The transaction is completed!",
                "To enter the feedback for your seller, click on the link below (or copy and paste the URL into your browser): \n"
                + Common.BASE_URL + Common.GIVE_FEEDBACK_URL + transaction.getTransactionID());
    }

    @Override
    public List<Transaction> getBuyTransaction() {
        return this.transactionDAO.findByBuyerID(Common.getMemberID());
    }

    @Override
    public List<Transaction> getSellTransaction() {
        return this.transactionDAO.findBySellerID(Common.getMemberID());
    }

}
