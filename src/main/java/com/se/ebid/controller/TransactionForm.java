/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.controller;

import javax.validation.constraints.Size;

/**
 *
 * @author Kawin
 */
class TransactionForm {
    private long transactionID;
    
    @Size(min = 1, message = "Address must not be empty")
    private String address;

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
