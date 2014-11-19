/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.service;

import com.se.ebid.entity.Member;
import com.se.ebid.entity.Feedback;
import com.se.ebid.entity.Transaction;
import java.util.List;
import com.se.ebid.controller.RegistrationForm;
import com.se.ebid.controller.SignInForm;
import com.se.ebid.controller.ForgotPasswordForm;
import com.se.ebid.controller.ResetPasswordForm;
import com.se.ebid.controller.PersonalInfoForm;
import com.se.ebid.controller.EditPasswordForm;
import com.se.ebid.controller.PaymentInfoForm;
import com.se.ebid.controller.ReceivingInfoForm;

/**
 *
 * @author Quxiz
 */
public interface MemberService {
    public boolean register(RegistrationForm registrationForm);
    //class diagram - String[]
    public boolean sendActivateEmail(Member member);
    public boolean activateMember(String activateKey);
    public boolean signIn(SignInForm signInForm);
    public boolean forgotPassword(ForgotPasswordForm forgotPasswordForm);
    //class diagram - String
    public boolean sendResetPasswordEmail(Member member);
    public boolean resetPassword(ResetPasswordForm resetPasswordForm);
    //????
    public Member getMember();
    public boolean editPersonalInfo(PersonalInfoForm personalInfoForm);
    public boolean editPassword(EditPasswordForm editPasswordForm);
    public boolean editPaymentInfo(PaymentInfoForm paymentInfoForm);
    public boolean editReceivingInfo(ReceivingInfoForm receivingInfoForm);
    public Member getSeller(String sellerID);
    public List<Feedback> getSellerFeedback(String sellerID);
    public List<Transaction> getBuyTransaction(long buyerID);
    public List<Transaction> getSellTransaction(long sellerID);
    public void saveMember(Member m);
    public List<Member> listMembers();
    
}
