/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.se.ebid.entity.Member;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Quxiz
 */
@Repository("MemberDAO")
public class MemberDAOImpl implements MemberDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Member member) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(member);
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Member> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Member> memberList = session.createQuery("from Member").list();
        session.getTransaction().commit();
        return memberList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Member findByUserID(String userID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Member> members = new ArrayList<Member>();
        members = sessionFactory.getCurrentSession()
                .createQuery("from Member where userID=?")
                .setParameter(0, userID)
                .list();
        session.getTransaction().commit();
        if (members.size() > 0) {
            return members.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Member findByActivateKey(String activateKey) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Member> members = new ArrayList<Member>();
        members = sessionFactory.getCurrentSession()
                .createQuery("from Member where activateKey=?")
                .setParameter(0, activateKey)
                .list();
        session.getTransaction().commit();
        if (members.size() > 0) {
            return members.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Member findByMemberID(long memberID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Member> members = new ArrayList<Member>();
        members = sessionFactory.getCurrentSession()
                .createQuery("from Member where memberID=?")
                .setParameter(0, memberID)
                .list();
        session.getTransaction().commit();
        if (members.size() > 0) {
            return members.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Member findByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Member> members = new ArrayList<Member>();
        members = sessionFactory.getCurrentSession()
                .createQuery("from Member where email=?")
                .setParameter(0, email)
                .list();
        session.getTransaction().commit();
        if (members.size() > 0) {
            return members.get(0);
        } else {
            return null;
        }
    }

}
