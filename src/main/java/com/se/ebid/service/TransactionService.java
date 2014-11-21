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
    public Transaction getTransaction(long transactionID);
    public Transaction setShippingService(long transactionID, String shippingService);
    public boolean checkOutTransaction(long transactionID);
    public boolean sendSellerEmail(Member member, Transaction transaction);
    public boolean sendBuyerEmail(Member member, Transaction transaction);
    public List<Transaction> getBuyTransaction();
    public List<Transaction> getSellTransaction();
}
