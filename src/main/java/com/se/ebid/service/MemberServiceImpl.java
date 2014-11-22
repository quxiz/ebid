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
    public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
        this.feedbackDAO = feedbackDAO;
    }

    @Autowired
    public void setTransactionDAO(TransactionDAO transactionDAO) {
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
        if (this.memberDAO.findByUserID(registrationForm.getUserID()) != null) {
            return false;
        }
        if (this.memberDAO.findByEmail(registrationForm.getEmail()) != null) {
            return false;
        }
        Member member = new Member();
        member.setFirstName(registrationForm.getFirstName());
        member.setLastName(registrationForm.getLastName());
        member.setAddress(registrationForm.getAddress());
        member.setCountry(registrationForm.getCountry());
        member.setEmail(registrationForm.getEmail());
        member.setPhoneNo(registrationForm.getPhoneNo());
        member.setUserID(registrationForm.getUserID());
        member.setPassword(toSHA256(registrationForm.getPassword()));
        member.setTimestamp(new Timestamp((System.currentTimeMillis())));
        this.sendActivateEmail(member);
        this.memberDAO.save(member);

        return true;
    }

    @Override
    public boolean sendActivateEmail(Member member) {
        String activateURL = null;
        return Common.sendMail(member.getEmail(), "[ebid] Signup confirmation",
                "Hello " + member.getFirstName() + ",\n"
                + "You have requested a new user account on ebid:\n"
                + "User name:     " + member.getUserID() + "\n"
                + "\n"
                + "-------------------------------------\n"
                + "To confirm your user registration, you have to follow this link\n:"
                + activateURL + "\n"
                + "\n"
                + "Regards,\n"
                + "ebid Staff"
        );
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public boolean activateMember(String activateKey) {
        Member member = this.memberDAO.findByActivateKey(activateKey);
        if (member == null) {
            return false;
        }
        member.setActivated(true);
        this.memberDAO.save(member);
        return true;
    }

    @Override
    @Transactional
    public boolean forgotPassword(ForgotPasswordForm forgotPasswordForm) {
        Member member = this.memberDAO.findByEmail(forgotPasswordForm.getEmail());
        if (member == null) {
            return false;
        }
        this.sendResetPasswordEmail(member);
        return true;
    }

    @Override
    public boolean sendResetPasswordEmail(Member member) {
        return Common.sendMail(member.getEmail(), "[ebid] Reset your account password",
        "ebid received a request to resset the password for your account\n" +
        "\n" +
        "To reset your password, click on the link below (or copy and paste the URL into your browser): \n" +
        Common.BASE_URL + Common.RESET_PASSWORD_URL);
        
    }

    @Override
    @Transactional
    public boolean resetPassword(ResetPasswordForm resetPasswordForm) {
        if(!resetPasswordForm.getSecret().equals(encodeEmail(resetPasswordForm.getEmail()))) return false;
        Member member = this.memberDAO.findByEmail(resetPasswordForm.getEmail());
        
        if (member == null) {
            return false;
        }
        member.setPassword(resetPasswordForm.getNewPassword());
        this.memberDAO.save(member);
        return true;
    }

    @Override
    public Member getMember() {
        return this.memberDAO.findByMemberID(Common.getMemberID());
    }

    @Override
    @Transactional
    public boolean editPersonalInfo(PersonalInfoForm personalInfoForm) {
        Member member = this.memberDAO.findByMemberID(Common.getMemberID());
        member.setAddress(personalInfoForm.getAddress());
        member.setCountry(personalInfoForm.getCountry());
        member.setFirstName(personalInfoForm.getFirstName());
        member.setLastName(personalInfoForm.getLastName());
        member.setPhoneNo(personalInfoForm.getPhoneNo());
        this.memberDAO.save(member);
        return true;
    }

    @Override
    @Transactional
    public boolean editPassword(EditPasswordForm editPasswordForm) {
        Member member = this.memberDAO.findByMemberID(Common.getMemberID());
        if (member == null) {
            return false;
        }
        if (!member.getPassword().equals(toSHA256(editPasswordForm.getOldPassword()))) {
            return false;
        }
        //if(editPasswordForm.getNewPassword()!=editPasswordForm.getConformNewPassword()) return false;
        member.setPassword(toSHA256(editPasswordForm.getNewPassword()));
        this.memberDAO.save(member);
        return true;
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
    public List<Feedback> getSellerFeedback(long sellerID) {
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
    
    private String encodeEmail(String email){
        char[] charArray = email.toCharArray();
        char[] encodeArray = new char[30];
        for(int i=0;i<charArray.length;i++){
            encodeArray[i] = (char)((((int)charArray[i])+(i%9)*9+3-33)%90+33);
        }
        return new String(encodeArray);
    }

}
