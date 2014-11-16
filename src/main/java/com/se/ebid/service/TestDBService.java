/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.service;

import com.se.ebid.entity.Member;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface TestDBService {
    
    public void saveMember(Member m);
    public List<Member> listMembers();
    public Member findByMemberID(long memberID);
    
}
