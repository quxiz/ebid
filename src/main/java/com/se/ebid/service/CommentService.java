/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.controller.QuestionForm;
import com.se.ebid.controller.AnswerForm;
import com.se.ebid.entity.Comment;
/**
 *
 * @author Nuttapong
 */
public interface CommentService {
    public boolean askQuestion(QuestionForm questionForm);
    public boolean answerQuestion(AnswerForm answerForm);
    public Comment getComment(long commentID);
    
}
