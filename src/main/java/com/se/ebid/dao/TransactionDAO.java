/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.dao;

import com.se.ebid.entity.Transaction;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface TransactionDAO {
 
    public void save(Transaction transaction);
     
    public List<Transaction> list();
    
    public Transaction findCompletedByTimestamp(java.sql.Timestamp startTime, java.sql.Timestamp endTime);
    
    public List<Transaction> findByBuyerID(long buyerID);
    
    public List<Transaction> findBySellerID(long sellerID);
     
}
