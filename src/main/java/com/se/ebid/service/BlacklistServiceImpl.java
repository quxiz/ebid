/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.dao.MemberDAO;
import com.se.ebid.dao.BlacklistDAO;
import com.se.ebid.controller.BlacklistForm;
import com.se.ebid.entity.Member;
import com.se.ebid.entity.Blacklist;
import java.sql.Timestamp;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author Nuttapong
 */
public class BlacklistServiceImpl implements BlacklistService{

    private MemberDAO memberDAO;
    private BlacklistDAO blacklistDAO;
    
    @Autowired
    public void setMemberDAO(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
    }
    
    @Autowired
    public void setBlacklistDAO(BlacklistDAO blacklistDAO){
        this.blacklistDAO = blacklistDAO;
    }
    
    @Override
    @Transactional
    public boolean blacklist(BlacklistForm blacklistForm) {
        Member member = this.memberDAO.findByUserID(blacklistForm.getUserId());
	if(member == null) return false;
	//member.setBlacklisted(blacklistForm.getBlacklistStatus());
	this.memberDAO.save(member);
	
	Blacklist blacklist = new Blacklist();
	blacklist.setMemberID(member.getMemberID());
	blacklist.setDetail(blacklistForm.getDetail());
	//blacklist.setStatus(blacklistForm.getBlacklistStatus());
	blacklist.setTimestamp(new Timestamp(System.currentTimeMillis()));
	this.blacklistDAO.save(blacklist);
	
	return true;
    }
    
}
