/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.controller.EditPasswordForm;
import com.se.ebid.controller.ForgotPasswordForm;
import com.se.ebid.controller.PaymentInfoForm;
import com.se.ebid.controller.PersonalInfoForm;
import com.se.ebid.controller.ReceivingInfoForm;
import com.se.ebid.controller.RegistrationForm;
import com.se.ebid.controller.ResetPasswordForm;
import com.se.ebid.controller.SignInForm;
import com.se.ebid.dao.MemberDAO;
import com.se.ebid.dao.FeedbackDAO;
import com.se.ebid.dao.TransactionDAO;
import com.se.ebid.entity.Feedback;
import com.se.ebid.entity.Member;
import com.se.ebid.entity.Transaction;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

/**
 *
 * @author Quxiz
 */
@Service
public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO;
    private FeedbackDAO feedbackDAO;
    private TransactionDAO transactionDAO;

    @Autowired
    public void setPersonDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }
    
    @Autowired
    public void setFeedbackDAO(FeedbackDAO feedbackDAO){
        this.feedbackDAO = feedbackDAO;
    }
    
    @Autowired
    public void setTransactionDAO(TransactionDAO transactionDAO){
        this.transactionDAO = transactionDAO;
    }
    

    @Override
    @Transactional
    public void saveMember(Member m) {
        m.setPassword(toSHA256(m.getPassword()));
        this.memberDAO.save(m);
    }

    @Override
    @Transactional
    public List<Member> listMembers() {
        return this.memberDAO.list();
    }

    private static String toSHA256(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-256");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    @Override
    @Transactional
    public boolean register(RegistrationForm registrationForm) {
        if(this.memberDAO.findByUserID(registrationForm.getUserID()) != null) return false;
        if(this.memberDAO.findByEmail(registrationForm.getEmail()) != null) return false;
        Member member = new Member();
	member.setFirstName(registrationForm.getFirstName());
	member.setLastName(registrationForm.getLastName());
	member.setAddress(registrationForm.getAddress());
	member.setCountry(registrationForm.getCountry());
	member.setEmail(registrationForm.getEmail());
	member.setPhoneNo(registrationForm.getPhoneNo());
	member.setUserID(registrationForm.getUserID());
	member.setPassword(registrationForm.getPassword());
	member.setTimestamp(new Timestamp((System.currentTimeMillis())));
	this.sendActivateEmail(member);
        this.memberDAO.save(member);
	
	return true;
    }

    @Override
    public boolean sendActivateEmail(Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public boolean activateMember(String activateKey) {
        Member member = this.memberDAO.findByActivateKey(activateKey);
        if(member == null) return false;
        member.setActivated(true);
        return true;
    }

    @Override
    @Transactional
    public boolean signIn(SignInForm signInForm) {
        Member member = this.memberDAO.findByUserID(signInForm.getUserID());
        if(member == null) return false;
        return member.getPassword().equals(signInForm.getPassword());
    }

    @Override
    @Transactional
    public boolean forgotPassword(ForgotPasswordForm forgotPasswordForm) {
        Member member = this.memberDAO.findByEmail(forgotPasswordForm.getEmail());
        if(member == null) return false;
        this.sendResetPasswordEmail(member);
        return true;
    }

    @Override
    public boolean sendResetPasswordEmail(Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public boolean resetPassword(ResetPasswordForm resetPasswordForm) {
        Member member = this.memberDAO.findByEmail(resetPasswordForm.getEmail());
        if(member == null) return false;
        member.setPassword(resetPasswordForm.getNewPassword());
        this.memberDAO.save(member);
        return true;
    }

    @Override
    public Member getMember() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editPersonalInfo(PersonalInfoForm personalInfoForm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editPassword(EditPasswordForm editPasswordForm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editPaymentInfo(PaymentInfoForm paymentInfoForm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editReceivingInfo(ReceivingInfoForm receivingInfoForm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Member getSeller(String sellerID) {
        return this.memberDAO.findByUserID(sellerID);
    }

    @Override
    public List<Feedback> getSellerFeedback(String sellerID) {
        return this.feedbackDAO.findBySellerID(sellerID);
    }

    @Override
    public List<Transaction> getBuyTransaction(long buyerID) {
        return this.transactionDAO.findByBuyerID(buyerID);
    }

    @Override
    public List<Transaction> getSellTransaction(long sellerID) {
        return this.transactionDAO.findBySellerID(sellerID);
    }

}
