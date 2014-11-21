/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.controller.FeedbackForm;

/**
 *
 * @author Nuttapong
 */
public interface FeedbackService {
    public boolean giveFeedback(FeedbackForm feedbackForm);
}
