/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.velocity.tools.generic.LoopTool.Equals;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author mtmmoei
 */


public class RegistrationForm {
    @Size(min = 1,message = "First name is a required field")
    private String firstName;
    
    @Size(min = 1,message = "last name is a required field")
    private String lastName;
    
    @Size(min = 5, message = "address error")
    private String address;
    
    @Size(min = 1,message = "country is a required field")
    private String country;
    
    @Email
    @Size(min = 1,message = "last name is a required field")
    private String email;
    
    @Size(max=20,min=6)
    @Pattern(regexp="[0-9]+")
    private String phoneNo;
    
    @Pattern(regexp="[A-Za-z0-9]+",message = "invalid userID pattern" )
    @Size(min=5, message = "userID requires at least 5 characters")
    private String userID;
    
    @Size(min=6,message= "password requires at least 6 characters")
    private String password;
    
    @Size(min=6,message= "password requires at least 6 characters")
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
}
