/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.dao.MessageDAO;
import com.se.ebid.entity.Message;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nuttapong
 */
public class MessageServiceImpl implements MessageService{

    private MessageDAO messageDAO;
    
    @Autowired
    public void setMessageDAO(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    
    @Override
    public List<Message> getMessage() {
        return this.messageDAO.findByReceiverID(Common.getMemberID());
    }
}
