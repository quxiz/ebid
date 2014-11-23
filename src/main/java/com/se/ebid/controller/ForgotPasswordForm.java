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
public class ForgotPasswordForm {
    @Email(message = "Email is invalid")
    @Size(min = 1, max = 45, message = "Email must not be empty and must not be longer than 45 characters")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
