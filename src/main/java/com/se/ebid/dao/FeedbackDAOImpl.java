/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.dao;

import com.se.ebid.entity.Feedback;
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
public class FeedbackDAOImpl implements FeedbackDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Feedback feedback) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(feedback);
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Feedback> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Feedback> feedbackList = session.createQuery("from Feedback").list();
        session.getTransaction().commit();
        return feedbackList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Feedback findByTransactionID(long transactionID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Feedback> feedbacks = new ArrayList<Feedback>();
        feedbacks = sessionFactory.getCurrentSession()
                .createQuery("from Feedback where transactionID=:transactionID")
                .setParameter("transactionID", transactionID)
                .list();
        session.getTransaction().commit();
        if (feedbacks.size() > 0) {
            return feedbacks.get(0);
        } else {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Feedback> findByBuyerID(long buyerID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Feedback> feedbacks = new ArrayList<Feedback>();
        feedbacks = sessionFactory.getCurrentSession()
                .createQuery("from Feedback where buyerID=:buyerID and sellerFeedbacked=true order by timestamp desc")
                .setParameter("buyerID", buyerID)
                .list();
        session.getTransaction().commit();
        return feedbacks;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Feedback> findBySellerID(long sellerID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Feedback> feedbacks = new ArrayList<Feedback>();
        feedbacks = sessionFactory.getCurrentSession()
                .createQuery("from Feedback where sellerID=:sellerID and buyerFeedbacked=true order by timestamp desc")
                .setParameter("sellerID", sellerID)
                .list();
        session.getTransaction().commit();
        return feedbacks;
    }

}
