/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author mtmmoei
 */
public class ReceivingInfoForm {
    @Email(message = "Paypal account is invalid")
    @Size(min = 1, max = 45, message = "Paypal account must not be empty or longer than 45 characters")
    private String payPalAccount;

    public String getPayPalAccount() {
        return payPalAccount;
    }

    public void setPayPalAccount(String payPalAccount) {
        this.payPalAccount = payPalAccount;
    }
    
}
