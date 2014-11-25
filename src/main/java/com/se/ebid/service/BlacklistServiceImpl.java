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
import com.se.ebid.entity.BlacklistStatus;
import java.sql.Timestamp;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Nuttapong
 */
@Service
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
        Member member = this.memberDAO.findByUserID(blacklistForm.getUserID());
	if(member == null) return false;
        boolean blacklistStatus;
        switch(blacklistForm.getBlacklistStatus()){
            case "NORMAL":
                blacklistStatus = false;
                break;
            case "BLACKLIST":
                blacklistStatus = true;
                break;
            default:
                return false;
        }
        member.setBlacklisted(blacklistStatus);
	this.memberDAO.save(member);
	
	Blacklist blacklist = new Blacklist();
	blacklist.setMemberID(member.getMemberID());
	blacklist.setDetail(blacklistForm.getDetail());
        if(blacklistStatus == false)
            blacklist.setStatus(BlacklistStatus.NORMAL);
        else
            blacklist.setStatus(BlacklistStatus.BLACKLIST);
	blacklist.setTimestamp(new Timestamp(System.currentTimeMillis()));
	this.blacklistDAO.save(blacklist);
	
	return true;
    }
    
}
