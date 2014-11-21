/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Nuttapong
 */
public class Common {
    
    static final int ADMIN_ID = -1;
    
    static final long getMemberID(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser = (CustomUser)auth.getPrincipal();
        return customUser.getMemberID();
    }
    
    static final String getUserID(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser = (CustomUser)auth.getPrincipal();
        return customUser.getUserID();
    }
}
