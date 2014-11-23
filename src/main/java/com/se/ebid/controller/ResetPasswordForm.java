/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author mtmmoei
 */
public class ResetPasswordForm {
    @Email(message = "Email is invalid")
    @Size(min = 1, max = 45, message = "Email must not be empty and must not be longer than 45 characters")
    private String email;
    
    // secret = key for reset password
    private String secret;
    
    @Size(min = 6, max = 20, message = "New password requires at least 6 characters and at most 20 characters")
    private String newPassword;
    
    @Size(min = 6, max = 20, message = "Confirm new password requires at least 6 characters and at most 20 characters")
    private String confirmNewPassword;

    @AssertTrue(message = "New password must be as same as confirm new password")
    private boolean isValid() {
        return this.newPassword.equals(this.confirmNewPassword);
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSecret(){
        return this.secret;
    }
    
    public void setSecret(String secret){
        this.secret = secret;
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

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
    
}
