/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.dao;

import com.se.ebid.entity.Message;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Quxiz
 */
@Repository
public class MessageDAOImpl implements MessageDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Message message) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(message);
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Message> messageList = session.createQuery("from Message").list();
        session.getTransaction().commit();
        return messageList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> findByReceiverID(long receiverID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Message> messageList = sessionFactory.getCurrentSession()
                .createQuery("from Message where receiverID=:receiverID")
                .setParameter("receiverID", receiverID)
                .list();
        session.getTransaction().commit();
        return messageList;
    }

}
