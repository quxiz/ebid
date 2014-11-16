/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.dao.MemberDAO;
import com.se.ebid.entity.Member;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Formatter;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Quxiz
 */
@Service
public class TestDBServiceImpl implements TestDBService {
    private SecureRandom random = new SecureRandom();
    private MemberDAO memberDAO;
    

    @Autowired
    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    @Transactional
    public void saveMember(Member m) {
        m.setPassword(toSHA256(m.getPassword()));
        m.setActivated(true);
        java.util.Date date= new java.util.Date();
        m.setTimestamp(new Timestamp(date.getTime()));
        m.setBlacklisted(false);
        m.setActivateKey(new BigInteger(130, random).toString(32));
        this.memberDAO.save(m);
    }

    @Override
    @Transactional
    public List<Member> listMembers() {
        return this.memberDAO.list();
    }
    
    @Override
    @Transactional
    public Member findByMemberID(long memberID) {
        return this.memberDAO.findByMemberID(memberID);
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
}
