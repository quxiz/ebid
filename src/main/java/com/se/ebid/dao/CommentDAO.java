/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.dao;

import com.se.ebid.entity.Comment;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface CommentDAO {
 
    public void save(Comment comment);
     
    public List<Comment> list();
    
    public Comment findByItemID(long itemID);
     
}
