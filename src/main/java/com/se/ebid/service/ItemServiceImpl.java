/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.service;

import com.se.ebid.controller.BidForm;
import com.se.ebid.controller.BuyForm;
import com.se.ebid.controller.Invoice;
import com.se.ebid.controller.RegisterItemForm;
import com.se.ebid.controller.SearchForm;
import com.se.ebid.controller.SellingType;
import com.se.ebid.dao.ItemDAO;
import com.se.ebid.dao.MemberDAO;
import com.se.ebid.dao.AutoBidDAO;
import com.se.ebid.dao.FeedbackDAO;
import com.se.ebid.dao.MessageDAO;
import com.se.ebid.dao.PhotoDAO;
import com.se.ebid.dao.TransactionDAO;
import com.se.ebid.dao.CommentDAO;
import com.se.ebid.entity.AutoBid;
import com.se.ebid.entity.Comment;
import com.se.ebid.entity.Feedback;
import com.se.ebid.entity.Item;
import com.se.ebid.entity.Member;
import com.se.ebid.entity.Message;
import com.se.ebid.entity.Photo;
import com.se.ebid.entity.Transaction;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;

/**
 *
 * @author Quxiz
 */
@Service
public class ItemServiceImpl implements ItemService{
    
    private ItemDAO itemDAO;
    private MemberDAO memberDAO;
    private AutoBidDAO autoBidDAO;
    private MessageDAO messageDAO;
    private TransactionDAO transactionDAO;
    private FeedbackDAO feedbackDAO;
    private PhotoDAO photoDAO;
    private CommentDAO commentDAO;
    
    @Autowired
    public void setItemDAO(ItemDAO itemDAO){
        this.itemDAO = itemDAO;
    }
    
    @Autowired
    public void setMemberDAO(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
    }
    
    @Autowired
    public void setAutoBidDAO(AutoBidDAO autoBidDAO){
        this.autoBidDAO = autoBidDAO;
    }
    
    @Autowired
    public void setMessageDAO(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    
    @Autowired
    public void setTransactionDAO(TransactionDAO transactionDAO){
        this.transactionDAO = transactionDAO;
    }
    
    @Autowired
    public void setFeedbackDAO(FeedbackDAO feedbackDAO){
        this.feedbackDAO = feedbackDAO;
    }
    
    @Autowired
    public void setPhotoDAO(PhotoDAO photoDAO){
        this.photoDAO = photoDAO;
    }
    
    @Autowired
    public void setCommentDAO(CommentDAO commentDAO){
        this.commentDAO = commentDAO;
    }
    
    @Autowired
    ServletContext servletContext;

    @Override
    public List<Item> search(SearchForm searchForm) {
        return this.itemDAO.search(searchForm);
    }

    @Override
    @Transactional
    public Item getItem(long itemID) {
        return this.itemDAO.findByItemID(itemID);
    }
    
    @Override
    public List<Photo> getPhoto(long itemID) {
        return this.photoDAO.findByItemID(itemID);
    }

    @Override
    public List<Comment> getComment(long itemID) {
        return this.commentDAO.findByItemID(itemID);
    }

    @Override
    @Transactional
    public boolean bid(BidForm bidForm) {
        long memberID = Common.getMemberID();
        Member member = this.memberDAO.findByMemberID(memberID);
        if(member == null) return false;
        AutoBid autoBid = this.autoBidDAO.findByItemID(bidForm.getItemID());
        if(autoBid == null) return false;
        Item item = this.itemDAO.findByItemID(bidForm.getItemID());
        if(item == null) return false;
        
        double newMaxBid = bidForm.getMaxBid();
        if(newMaxBid < item.getPrice()) return false;
        double oldMaxBid = autoBid.getMaxBid();
        double newBidIncrement = bidForm.getBidIncrement();
        double oldBidIncrement = autoBid.getBidIncrement();
        
        if(newMaxBid > oldMaxBid){
            double price = oldMaxBid + newBidIncrement;
            if(price > newMaxBid) price = newMaxBid;
            item.setPrice(price);
            this.itemDAO.save(item);
            
            long outBidderID = autoBid.getBidderID();
            autoBid.setBidderID(memberID);
            autoBid.setMaxBid(newMaxBid);
            autoBid.setBidIncrement(newBidIncrement);
            autoBid.setTimestamp(new Timestamp(System.currentTimeMillis()));
            this.autoBidDAO.save(autoBid);
            
            Member outBidder = this.memberDAO.findByMemberID(outBidderID);
            if(outBidder != null){
                sendOutbidEmail(outBidder, item);
                Message message = new Message();
                message.setSenderID(Common.ADMIN_ID);
                message.setReceiverID(outBidderID);
                message.setMessage("outbid");
                message.setTimestamp(new Timestamp(System.currentTimeMillis()));
                message.setSeen(false);
                this.messageDAO.save(message);          
            }
        }
        else{
            double price = newMaxBid + oldBidIncrement;
            if(price > oldMaxBid) price = oldMaxBid;
            item.setPrice(price);
            this.itemDAO.save(item);
        }
        return true;
    }

    @Override
    public boolean sendOutbidEmail(Member member, Item item) {
        return Common.sendMail(member.getEmail(), "[ebid] Outbid notification",
        "You were outbitted at " + item.getTitle() + "\n" +
        "Current price: " + item.getPrice() + "\n"+
        "\n"+
        "Beat it now!!!\n" +
        Common.BASE_URL + Common.VIEW_ITEM_URL + item.getItemID()
        );
    }

    @Override
    @Transactional
    public Invoice buy(BuyForm buyForm) {
        Member member = this.memberDAO.findByMemberID(Common.getMemberID());
        if(member == null) return null;
        if(member.isBlacklisted()) return null;
        
        long itemID = buyForm.getItemID();
        Item item = this.itemDAO.findByItemID(itemID);
        if(item == null) return null;
        if(item.getQuantity() < buyForm.getQuantity()) return null;
        
        Invoice invoice = new Invoice();
        invoice.setItemID(itemID);
        invoice.setQuantity(buyForm.getQuantity());
        invoice.setTotal(buyForm.getQuantity() * item.getPrice());
        
        return invoice;
    }

    @Override
    @Transactional
    public boolean confirmBuy(BuyForm buyForm) {
        long itemID = buyForm.getItemID();
        Item item = this.itemDAO.findByItemID(itemID);
        long sellerID = item.getSellerID();
        long buyerID = Common.getMemberID();
        if(buyForm.getQuantity() > item.getQuantity()) return false;
        
        Transaction transaction = new Transaction();
        transaction.setSellerID(sellerID);
        transaction.setBuyerID(buyerID);
        transaction.setItemID(itemID);
        transaction.setQuantity(buyForm.getQuantity());
        transaction.setPrice(item.getPrice() * buyForm.getQuantity());
        transaction.setDetail(item.getDetail());
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        this.transactionDAO.save(transaction);
        
        Feedback feedback = new Feedback();
        feedback.setTransactionID(transaction.getTransactionID());
        feedback.setSellerID(sellerID);
        feedback.setBuyerID(buyerID);
        feedback.setItemID(itemID);
        this.feedbackDAO.save(feedback);
        
        return true;
    }

    @Override
    @Transactional
    //unfinish
    public boolean registerItem(RegisterItemForm registerItemForm) {
        Item item = new Item();
        long memberID = Common.getMemberID();
        item.setSellerID(memberID);
        item.setTitle(registerItemForm.getTitle());
        item.setSpecifics(registerItemForm.getSpecific());
        item.setDetail(registerItemForm.getDetail());
        item.setCategory(registerItemForm.getCategory());
        item.setSellingType(registerItemForm.getSellingType());
        item.setPrice(registerItemForm.getPrice());
        item.setQuantity(registerItemForm.getQuantity());
        item.setStartTime(registerItemForm.getStartTime());
        item.setEndTime(registerItemForm.getEndTime());

       // item.setPaymentMethod(registerItemForm.getPaymentMethod());

        item.setShippingService(registerItemForm.getShippingService());
        item.setShippingCost(registerItemForm.getShippingCost());
        item.setPackageDetail(registerItemForm.getPackageDetail());
        item.setReturnPolicy(registerItemForm.getReturnPolicy());
        item.setTimestamp(new Timestamp(System.currentTimeMillis()));

        this.itemDAO.save(item);
        MultipartFile[] photoList = registerItemForm.getPhotos();
        long itemID = item.getItemID();
        for (MultipartFile aPhoto : photoList) {
            Photo photo = new Photo();
            photo.setItemID(itemID);
            photo.setPhotoURL(null);
            long photoID = this.photoDAO.save(photo);
            String photoURL = servletContext.getRealPath("resources/uploadedImg/") + photoID + aPhoto.getContentType();
            try {
                aPhoto.transferTo(new File(photoURL));
            }catch (IOException ex) {
                Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IllegalStateException ex) {
                Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            photo.setPhotoURL(photoURL);
            this.photoDAO.save(photo);
        }
        
        if(registerItemForm.getSellingType() == SellingType.BID){
            AutoBid autoBid = new AutoBid();
            autoBid.setItemID(itemID);
            autoBid.setBidderID(-1);
            autoBid.setMaxBid(item.getPrice());
            autoBid.setBidIncrement(0);
            autoBid.setTimestamp(new Timestamp(System.currentTimeMillis()));
            this.autoBidDAO.save(autoBid);
            
            // unfinish bidSchedule
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
