/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

/**
 *
 * @author mtmmoei
 */
public class EditPasswordForm {
    @Size(min = 6, max = 20, message = "New password requires at least 6 characters and at most 20 characters")
    private String newPassword;
    
    @Size(min = 6, max = 20, message = "Confirm new password requires at least 6 characters and at most 20 characters")
    private String confirmNewPassword;
    
    private String oldPassword;

    @AssertTrue(message = "New password must be as same as Confirm new password")
    private boolean isValid() {
        return this.newPassword.equals(this.confirmNewPassword);
    }
    
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String conformNewPassword) {
        this.confirmNewPassword = conformNewPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    
}
