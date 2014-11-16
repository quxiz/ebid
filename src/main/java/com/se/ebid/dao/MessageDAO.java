/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.dao;

import com.se.ebid.entity.Message;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface MessageDAO {
 
    public void save(Message message);
     
    public List<Message> list();
    
    public Message findByReceiverID(long receiverID);
     
}
