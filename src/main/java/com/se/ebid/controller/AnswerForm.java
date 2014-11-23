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
public class AnswerForm {
    
    private long itemID;
    private long parentID;
    private long askerID;
    
    @Size(min = 1, message = "Answer must not be empty")
    private String answer;

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public long getParentID() {
        return parentID;
    }

    public void setParentID(long parentID) {
        this.parentID = parentID;
    }

    public long getAskerID() {
        return askerID;
    }

    public void setAskerID(long askerID) {
        this.askerID = askerID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
