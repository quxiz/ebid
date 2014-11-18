/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.dao;

import com.se.ebid.entity.BidSchedule;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Quxiz
 */
@Repository("BidScheduleDAO")
public class BidScheduleDAOImpl implements BidScheduleDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BidScheduleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /*public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }*/

    @Override
    public void save(BidSchedule bidSchedule) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(bidSchedule);
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BidSchedule> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<BidSchedule> bidscheduleList = session.createQuery("from BidSchedule").list();
        session.getTransaction().commit();
        return bidscheduleList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BidSchedule findByItemID(long itemID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<BidSchedule> bidschedules = new ArrayList<BidSchedule>();
        bidschedules = sessionFactory.getCurrentSession()
                .createQuery("from BidSchedule where itemID=?")
                .setParameter(0, itemID)
                .list();
        session.getTransaction().commit();
        if (bidschedules.size() > 0) {
            return bidschedules.get(0);
        } else {
            return null;
        }
    }

}
