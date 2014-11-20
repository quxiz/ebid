/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.entity.Member;
import com.se.ebid.entity.Transaction;

/**
 *
 * @author Nuttapong
 */
public interface TransactionService {
    public Transaction getTransaction(long transactionID);
    public Transaction setDelivery(long transactionID, String delivery);
    public boolean checkOutTransaction(long transactionID);
    public boolean sendSellerEmail(Member member);
    public boolean sendBuyerEmail(Member member);
}
