/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.service;

import com.se.ebid.dao.MemberDAO;
import com.se.ebid.entity.Member;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Quxiz
 */
@Service
public class MemberServiceImpl implements MemberService{
    private MemberDAO memberDAO;
    
    @Autowired
    public void setPersonDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }
    
    @Override
    @Transactional
    public void saveMember(Member m) {
        this.memberDAO.save(m);
    }
 
    @Override
    @Transactional
    public List<Member> listMembers() {
        System.out.println("member service");
        return this.memberDAO.list();
    }
}
