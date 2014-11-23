/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.controller;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Quxiz
 */
public class SignInForm {
    @Pattern(regexp = "[A-Za-z0-9]+", message = "User ID is invalid")
    @Size(min = 5, max = 20, message = "User ID requires at least 5 characters and at most 20 characters")
    private String userID;
    
    @Size(min = 6, max = 20, message = "Password requires at least 6 characters and at most 20 characters")
    private String password;

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}

