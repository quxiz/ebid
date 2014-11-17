/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.entity;

import java.io.Serializable;
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
@Table(name="Photo")
public class Photo implements Serializable {
    @Id
    @Column(name="photoID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long photoID;
    @Id
    @Column(name="itemID", nullable = false)
    private long itemID;
    @Column(name="photoURL")
    private String photoURL;

    public long getPhotoID() {
        return photoID;
    }

    public void setPhotoID(long photoID) {
        this.photoID = photoID;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
    
}