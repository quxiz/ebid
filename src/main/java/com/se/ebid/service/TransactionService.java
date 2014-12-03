/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.entity.Member;
import com.se.ebid.entity.Transaction;
import java.util.List;

/**
 *
 * @author Nuttapong
 */
public interface TransactionService {
    
    public final int ERR_NO_TRANSACTION = -2;
    public final int ERR_ALREADY_COMPLETE = -3;
    public final int ERR_NOT_ENOUGH_QTY = -4;
    
    public Transaction getTransaction(long transactionID);
    public Transaction setShippingService(long transactionID, String shippingService, String shippingAddress);
    public int checkOutTransaction(long transactionID);
    public List<Transaction> getBuyTransaction();
    public List<Transaction> getSellTransaction();
}
