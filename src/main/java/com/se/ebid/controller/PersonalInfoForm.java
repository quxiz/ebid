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
 * @author mtmmoei
 */
public class PersonalInfoForm {
    
    @Size(min = 1, max = 45, message = "First name must not be empty or longer than 45 characters")
    private String firstName;
    
    @Size(min = 1, max = 45, message = "Last name must not be empty or longer than 45 characters")
    private String lastName;
    
    private String address;
    
    @Size(min = 1, max = 45, message = "Country must not be empty or longer than 45 characters")
    private String country;
    
    @Pattern(regexp = "[0-9]+", message = "Phone number is invalid")
    private String phoneNo;

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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
}
