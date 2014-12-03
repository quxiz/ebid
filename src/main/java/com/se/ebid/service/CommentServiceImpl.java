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
import org.springframework.stereotype.Service;

/**
 *
 * @author Nuttapong
 */
@Service
public class CommentServiceImpl implements CommentService {

    private CommentDAO commentDAO;
    private MessageDAO messageDAO;

    @Autowired
    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
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
        message.setMessage("There is a question for you.<br/>"
                + "<a href=\"" + Common.BASE_URL + Common.ANSWER_QUESTION_URL + questionForm.getItemID() + "_" + comment.getCommentID() + "\">" 
                + "Click to answer your question" + "</a>");
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        message.setSeen(false);
        message.setSenderName(Common.getUserID());
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
        System.out.println("item id : "+answerForm.getItemID());
        comment.setItemID(answerForm.getItemID());
        comment.setCommentDetail(answerForm.getAnswer());
        comment.setTimestamp(new Timestamp((System.currentTimeMillis())));
        comment.setCommenterName(Common.getUserID());
        this.commentDAO.save(comment);

        Message message = new Message();
        message.setSenderID(memberID);
        message.setReceiverID(answerForm.getAskerID());
        message.setMessage("Your question has been answered.<br/>"
                + "<a href=\"" + Common.BASE_URL + Common.VIEW_ITEM_URL + comment.getItemID() + "\">" 
                + "Click to view" + "</a>");
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        message.setSeen(false);
        message.setSenderName(Common.getUserID());

        this.messageDAO.save(message);

        return true;
    }

    @Override
    public Comment getComment(long commentID) {
        return this.commentDAO.getComment(commentID);
    }

}
