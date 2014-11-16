/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Quxiz
 */
@Entity
@Table(name="Member")
public class Member {
    @Id
    @Column(name="memberID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long memberID;
    @Column(name="userID",unique = true, nullable = false)
    private String userID;
    @Column(name="password")
    private String password;
    @Column(name="firstName", columnDefinition="VARCHAR(45)")
    private String firstName;
    @Column(name="lastName", columnDefinition="VARCHAR(45)")
    private String lastName;
    @Column(name="address")
    private String address;
    @Column(name="country", columnDefinition="VARCHAR(45)")
    private String country;
    @Column(name="email", columnDefinition="VARCHAR(45)")
    private String email;
    @Column(name="phoneNo", columnDefinition="VARCHAR(45)")
    private String phoneNo;
    @Column(name="activated")
    private boolean activated;
    @Column(name="timestamp")
    private java.sql.Timestamp timestamp;
    @Column(name="paymentAccount", columnDefinition="VARCHAR(45)")
    private String paymentAccount;
    @Column(name="receivingAccount", columnDefinition="VARCHAR(45)")
    private String receivingAccount;
    @Column(name="blacklisted")
    private boolean blacklisted;
    @Column(name="activateKey")
    private String activateKey;

    public long getMemberID() {
        return memberID;
    }

    public void setMemberID(long memberID) {
        this.memberID = memberID;
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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public String getReceivingAccount() {
        return receivingAccount;
    }

    public void setReceivingAccount(String receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public boolean isBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    public String getActivateKey() {
        return activateKey;
    }

    public void setActivateKey(String activateKey) {
        this.activateKey = activateKey;
    }

}
