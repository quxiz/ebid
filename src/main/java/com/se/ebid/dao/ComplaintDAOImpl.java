/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.dao;

import com.se.ebid.entity.Complaint;
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
public class ComplaintDAOImpl implements ComplaintDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Complaint complaint) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(complaint);
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Complaint> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Complaint> complaintList = session.createQuery("from Complaint").list();
        session.getTransaction().commit();
        return complaintList;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Complaint> getComplaint() {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Complaint> complaintList = session.createQuery("from Complaint where solved is false").list();
        session.getTransaction().commit();
        return complaintList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Complaint findByComplaintID(long complaintID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Complaint> complaints = new ArrayList<Complaint>();
        complaints = sessionFactory.getCurrentSession()
                .createQuery("from Complaint where complaintID=:complaintID")
                .setParameter("complaintID", complaintID)
                .list();
        session.getTransaction().commit();
        if (complaints.size() > 0) {
            return complaints.get(0);
        } else {
            return null;
        }
    }

}
