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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
        long memberID = getMemberID();

//        
//        Comment comment = new Comment(questionForm.getItemID());
//        comment.setCommenterID(memberID);
//        comment.setCommentDetail(questionForm.getQuestion());
//        comment.setTimestamp(new Timestamp(System.currentTimeMillis()));
//        this.commentDAO.save(comment);
//        
//        Message message = new Message();
//        message.setSenderID(memberID);
//        message.setReceiverID(questionForm.getSellerID());
//        message.setMessage(ASK_QUESTION_MESSAGE);
//        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
//        message.setSeen(false);
//        this.messageDAO.save(message);

        
        return true;
    }

    @Override
    @Transactional
    public boolean answerQuestion(AnswerForm answerForm) {
    	long memberID = getMemberID();
        
        Comment comment = new Comment();
	comment.setParentID(answerForm.getParentID());
	comment.setCommenterID(memberID);
	comment.setCommentDetail(answerForm.getAnswer());
	comment.setTimestamp(new Timestamp((System.currentTimeMillis())));
	this.commentDAO.save(comment);
	
	Message message = new Message();
	message.setSenderID(memberID);
	message.setReceiverID(answerForm.getAskerID());

//	message.setMessage(ANSWER_QUESTION_MESSAGE);

	message.setTimestamp(new Timestamp(System.currentTimeMillis()));
	message.setSeen(false);
	this.messageDAO.save(message);
	
	return true;
    }
    
    private static long getMemberID(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser = (CustomUser)auth.getPrincipal();
        return customUser.getMemberID();
    }
}
