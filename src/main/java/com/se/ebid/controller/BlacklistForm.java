/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

/**
 *
 * @author mtmmoei
 */
public class BlacklistForm {
    private String userId;
    private String blacklistStatus;
    private String detail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
