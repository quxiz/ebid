/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.dao;

import com.se.ebid.entity.Feedback;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface FeedbackDAO {
 
    public void save(Feedback feedback);
     
    public List<Feedback> list();
    
    public Feedback findByTransactionID(long transactionID);
    
    public List<Feedback> findByBuyerID(long buyerID);
    
    public List<Feedback> findBySellerID(long sellerID);
     
}
