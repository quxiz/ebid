/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import javax.validation.constraints.Size;

/**
 *
 * @author mtmmoei
 */
public class BlacklistForm {
    private String userID;
    
    @Size(min = 1, message = "Blacklist status must not be empty")
    private String blacklistStatus;
    
    @Size(min = 1, message = "Detail must not be empty")
    private String detail;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBlacklistStatus() {
        return blacklistStatus;
    }

    public void setBlacklistStatus(String blacklistStatus) {
        this.blacklistStatus = blacklistStatus;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
}
