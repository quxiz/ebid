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
public class ComplaintForm {
    @Size(min = 1, message = "Title must not be empty")
    private String title;
    
    @Size(min = 1, message = "Detail must not be empty")
    private String detail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
