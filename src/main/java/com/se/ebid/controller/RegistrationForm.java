/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author mtmmoei
 */
public class RegistrationForm {

    @Size(min = 1, max = 45, message = "First name must not be empty or longer than 45 characters")
    private String firstName;

    @Size(min = 1, max = 45, message = "Last name must not be empty or longer than 45 characters")
    private String lastName;

    private String address;

    @Size(min = 1, max = 45, message = "Country must not be empty or longer than 45 characters")
    private String country;

    @Email(message = "Email is invalid")
    @Size(min = 1, max = 45, message = "Email must not be empty and must not be longer than 45 characters")
    private String email;
    
    @Pattern(regexp = "[0-9]+", message = "Phone number is invalid")
    @Size(min = 6, max = 20, message = "Phone number requires at least 5 characters and at most 20 characters")
    private String phoneNo;
    
    @Pattern(regexp = "[A-Za-z0-9]+", message = "User ID is invalid. It must contain only alphanumeric character")
    @Size(min = 5, max = 20, message = "User ID requires at least 5 characters and at most 20 characters")
    private String userID;

    @Size(min = 6, max = 20, message = "Password requires at least 6 characters and at most 20 characters")
    private String password;

    @Size(min = 6, max = 20, message = "Confirm password requires at least 6 characters and at most 20 characters")
    private String confirmPassword;

    @AssertTrue(message = "Password must be as same as confirm password")
    private boolean isValid() {
        return this.password.equals(this.confirmPassword);
    }

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
