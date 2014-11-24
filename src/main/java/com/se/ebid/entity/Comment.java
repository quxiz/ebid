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
@Table(name="Comment")
public class Comment {
    @Id
    @Column(name="commentID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long commentID;
    @Column(name="parentID")
    private Long parentID = null;
    @Column(name="itemID", nullable = false)
    private long itemID;
    @Column(name="commenterID", nullable = false)
    private long commenterID;
    @Column(name="commenterName")
    private String commenterName;
    @Column(name="commentDetail")
    private String commentDetail;
    @Column(name="timestamp")
    private java.sql.Timestamp timestamp;

    public long getCommentID() {
        return commentID;
    }

    public void setCommentID(long commentID) {
        this.commentID = commentID;
    }

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }
    
    public void setParentID(long parentID) {
        this.parentID = parentID;
    }


    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public long getCommenterID() {
        return commenterID;
    }

    public void setCommenterID(long commenterID) {
        this.commenterID = commenterID;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    
}