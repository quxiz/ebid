/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.dao;

import com.se.ebid.entity.Member;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface MemberDAO {
 
    public void save(Member p);
     
    public List<Member> list();
     
}
