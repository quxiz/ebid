/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.dao;

import com.se.ebid.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Quxiz
 */
@Repository
public class MemberDAOImpl implements MemberDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(Member m) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction trans = session.beginTransaction();
        session.save(m);
        trans.commit();
        //session.persist(m);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Member> list() {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction trans = session.beginTransaction();
        List<Member> memberList = session.createQuery("from Member").list();
        trans.commit();
        return memberList;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Member findByUserID(String userID) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction trans = session.beginTransaction();
        List<Member> members = new ArrayList<Member>();
        members = sessionFactory.getCurrentSession()
                .createQuery("from Member where userID=?")
                .setParameter(0, userID)
                .list();
        trans.commit();
        if (members.size() > 0) {
            return members.get(0);
        } else {
            return null;
        }
    }

}
