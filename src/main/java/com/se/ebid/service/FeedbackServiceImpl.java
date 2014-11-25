/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.controller.FeedbackForm;
import com.se.ebid.dao.FeedbackDAO;
import com.se.ebid.entity.Feedback;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Nuttapong
 */

@Service
public class FeedbackServiceImpl implements FeedbackService{

    private FeedbackDAO feedbackDAO;
    
    @Autowired
    public void setFeedbackDAO(FeedbackDAO feedbackDAO){
        this.feedbackDAO = feedbackDAO;
    }
    
    @Override
    @Transactional
    public boolean giveFeedback(FeedbackForm feedbackForm) {
        Feedback feedback = this.feedbackDAO.findByTransactionID(feedbackForm.getTransactionID());
        if(feedback == null) {
            System.out.println("feedback == null");
            return false;
        }
        
        long memberID = Common.getMemberID();
        if(memberID == feedback.getSellerID()){
            feedback.setSellerComment(feedbackForm.getComment());
            feedback.setSellerRating(feedbackForm.getRating());
            feedback.setSellerFeedbacked(true);
            this.feedbackDAO.save(feedback);
            return true;
        }
        if(memberID == feedback.getBuyerID()){
            feedback.setBuyerComment(feedbackForm.getComment());
            feedback.setBuyerRating(feedbackForm.getRating());
            feedback.setBuyerFeedbacked(true);
            this.feedbackDAO.save(feedback);
            return true;
        }
        System.out.println("memberID != id in feedback");
        return false;
    }
    
    @Override
    @Transactional
    public Feedback getFeedback(long transactionID) {
        return this.feedbackDAO.findByTransactionID(transactionID);
    }
    
    @Override
    @Transactional
    public List<Feedback> getSellerFeedback(long sellerID) {
        return this.feedbackDAO.findBySellerID(sellerID);
    }
}
