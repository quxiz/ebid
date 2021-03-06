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
    public int register(RegistrationForm registrationForm) {
        if (this.memberDAO.findByUserID(registrationForm.getUserID()) != null) {
            return MemberService.ERR_DUP_USER;
        }
        if (this.memberDAO.findByEmail(registrationForm.getEmail()) != null) {
            return MemberService.ERR_DUP_EMAIL;
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
        member.setActivateKey(generateActivateKey());
        if(!this.sendActivateEmail(member)) return -3;
        this.memberDAO.save(member);

        return 1;
    }

    @Override
    public boolean sendActivateEmail(Member member) {
        return Common.sendMail(member.getEmail(), "[ebid] Signup confirmation",
                "Hello " + member.getFirstName() + ",\n"
                + "You have requested a new user account on ebid:\n"
                + "User name:     " + member.getUserID() + "\n"
                + "\n"
                + "-------------------------------------\n"
                + "To confirm your user registration, you have to follow this link:\n"
                + Common.BASE_URL + Common.ACTIVATE_MEMBER_URL + member.getActivateKey()+ "\n"
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
        System.out.println(Common.BASE_URL + Common.RESET_PASSWORD_URL + member.getEmail() + "_" + encodeEmail(member.getEmail()));
        return Common.sendMail(member.getEmail(), "[ebid] Reset your account password",
        "ebid received a request to reset the password for your account\n" +
        "\n" +
        "To reset your password, click on the link below (or copy and paste the URL into your browser): \n" +
        Common.BASE_URL + Common.RESET_PASSWORD_URL + member.getEmail() + "_" + encodeEmail(member.getEmail()));
        
    }

    @Override
    @Transactional
    public boolean resetPassword(ResetPasswordForm resetPasswordForm) {
        System.out.println("reset password req");
        if(!resetPasswordForm.getSecret().trim().equals(encodeEmail(resetPasswordForm.getEmail()).trim())) {
            System.out.println(resetPasswordForm.getSecret());
            System.out.println(encodeEmail(resetPasswordForm.getEmail()));
            System.out.println(resetPasswordForm.getSecret().trim().length());
            System.out.println(encodeEmail(resetPasswordForm.getEmail()).trim().length());
                    
            return false;
        }
        Member member = this.memberDAO.findByEmail(resetPasswordForm.getEmail());
        
        if (member == null) {
            return false;
        }
        member.setPassword(toSHA256(resetPasswordForm.getNewPassword()));
        this.memberDAO.save(member);
        return true;
    }

    @Override
    @Transactional
    public Member getMember() {
        return this.memberDAO.findByMemberID(Common.getMemberID());
    }
    
    @Override
    @Transactional
    public Member getMemberByUserID(String userID) {
        return this.memberDAO.findByUserID(userID);
    }
@Override
    @Transactional
    public Member getMemberByMemberID(long memberID) {
        return this.memberDAO.findByMemberID(memberID);
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
    @Transactional
    public boolean editPaymentInfo(PaymentInfoForm paymentInfoForm) {
	Member member = this.memberDAO.findByMemberID(Common.getMemberID());
	if(member == null) return false;
	member.setPaymentAccount(paymentInfoForm.getPayPalAccount());
	this.memberDAO.save(member);
	return true;
    }

    @Override
    @Transactional
    public boolean editReceivingInfo(ReceivingInfoForm receivingInfoForm) {
	Member member = this.memberDAO.findByMemberID(Common.getMemberID());
	if(member == null) return false;
	member.setReceivingAccount(receivingInfoForm.getPayPalAccount());
	this.memberDAO.save(member);
	return true;
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
        int[] start = {48,65,97};
        int[] mod = {10,26,26};
        for(int i=0;i<charArray.length;i++){
            int k = (i+1)%3;
            encodeArray[i] = (char)((((int)charArray[i])+(i%9)*9+3-33)%mod[k]+start[k]);
        }
        return new String(encodeArray);
    }
    
    private String generateActivateKey(){
        long time = System.currentTimeMillis();
        time %= 31536000000L;
        char[] encodeArray = new char[30];
        int[] start = {48,65,97};
        int[] mod = {10,26,26};
        int i=0;
        while(time!=0){
            int k = i%3;
            encodeArray[i] = (char)((time%10*6+i*5)%mod[k] + start[k]);
            time/=10;
            i++;
        }
        encodeArray[i] = (char)95;
        i++;
        for(;i<encodeArray.length;i++){
            int k = i%3;
            encodeArray[i] = (char)(((Math.random()*26)%mod[k]) + start[k]);
        }
        return new String(encodeArray);
    }

}
