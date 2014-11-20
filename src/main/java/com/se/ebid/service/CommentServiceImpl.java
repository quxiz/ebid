/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.controller.AnswerForm;
import com.se.ebid.controller.QuestionForm;
import com.se.ebid.entity.Comment;
import com.se.ebid.entity.Message;
import com.se.ebid.dao.MessageDAO;
import com.se.ebid.dao.CommentDAO;
import java.sql.Timestamp;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nuttapong
 */
public class CommentServiceImpl implements CommentService{

    private CommentDAO commentDAO;
    private MessageDAO messageDAO;
    
    @Autowired
    public void setCommentDAO(CommentDAO commentDAO){
        this.commentDAO = commentDAO;
    }
    
    @Autowired
    public void setMessageDAO(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    
    @Override
    @Transactional
    public boolean askQuestion(QuestionForm questionForm) {
        long memberID = Common.getMemberID();
        String commenterName = Common.getUserID();
        
        Comment comment = new Comment();
        comment.setItemID(questionForm.getItemID());
        comment.setCommenterID(memberID);
        comment.setCommenterName(commenterName);
        comment.setCommentDetail(questionForm.getQuestion());
        comment.setTimestamp(new Timestamp(System.currentTimeMillis()));
        this.commentDAO.save(comment);
        
        Message message = new Message();
        message.setSenderID(memberID);
        message.setReceiverID(questionForm.getSellerID());
        message.setMessage("ถาม");
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        message.setSeen(false);
        this.messageDAO.save(message);
        
        return true;
    }

    @Override
    @Transactional
    public boolean answerQuestion(AnswerForm answerForm) {
    	long memberID = Common.getMemberID();
        
        Comment comment = new Comment();
	comment.setParentID(answerForm.getParentID());
	comment.setCommenterID(memberID);
	comment.setCommentDetail(answerForm.getAnswer());
	comment.setTimestamp(new Timestamp((System.currentTimeMillis())));
	this.commentDAO.save(comment);
	
	Message message = new Message();
	message.setSenderID(memberID);
	message.setReceiverID(answerForm.getAskerID());
	message.setMessage("ตอบ");
	message.setTimestamp(new Timestamp(System.currentTimeMillis()));
	message.setSeen(false);
	this.messageDAO.save(message);
	
	return true;
    }
    
    private static String setAskQuestionMessage(){
        return null;
    }
    
}
