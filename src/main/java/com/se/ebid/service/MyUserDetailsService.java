/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.dao.MemberDAO;
import com.se.ebid.entity.Member;
import java.util.Collection;
import java.util.HashSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Quxiz
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    private MemberDAO memberDAO;

    @Override
    public UserDetails loadUserByUsername(final String userID)
            throws UsernameNotFoundException {

        Member member = memberDAO.findByUserID(userID);
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        
        if(userID.equals("admin")) {
            System.out.println("ADMIN");
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else{
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        CustomUser customUser = buildUserForAuthentication(member, authorities);
        return customUser;
    }

    /* User(String username,
     String password, 
     boolean enabled, 
     boolean accountNonExpired, 
     boolean credentialsNonExpired, 
     boolean accountNonLocked, 
     Collection<? extends GrantedAuthority> authorities)
     if implement email activate, can use this function
     */
    private CustomUser buildUserForAuthentication(Member member,
            Collection<GrantedAuthority> authorities) {
        /*return new User(member.getUserID(), 
         member.getPassword(), true, 
         true, true, true, authorities);*/
        return new CustomUser(member.getUserID(),
                member.getPassword(), true,
                true, true, true, authorities, member.getMemberID());
    }

    public MemberDAO getMemberDAO() {
        return memberDAO;
    }

    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

}
