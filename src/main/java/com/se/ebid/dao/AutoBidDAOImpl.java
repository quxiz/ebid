/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.dao;

import com.se.ebid.entity.AutoBid;
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
public class AutoBidDAOImpl implements AutoBidDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(AutoBid autoBid) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(autoBid);
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AutoBid> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<AutoBid> autobidList = session.createQuery("from AutoBid").list();
        session.getTransaction().commit();
        return autobidList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public AutoBid findByItemID(long itemID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<AutoBid> autobids = new ArrayList<AutoBid>();
        autobids = sessionFactory.getCurrentSession()
                .createQuery("from AutoBid where itemID=:itemID")
                .setParameter("itemID", itemID)
                .list();
        session.getTransaction().commit();
        if (autobids.size() > 0) {
            return autobids.get(0);
        } else {
            return null;
        }
    }

}
