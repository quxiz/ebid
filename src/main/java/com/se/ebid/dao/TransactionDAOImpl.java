/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.dao;

import com.se.ebid.entity.Transaction;
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
public class TransactionDAOImpl implements TransactionDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Transaction transaction) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(transaction);
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Transaction> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Transaction> transactionList = session.createQuery("from Transaction").list();
        session.getTransaction().commit();
        return transactionList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Transaction> findCompletedByTimestamp(java.sql.Timestamp startTime, java.sql.Timestamp endTime) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions = sessionFactory.getCurrentSession()
                .createQuery("from Transaction where (completed=true and timestamp between :startTime and :endTime)")
                .setParameter("startTime", startTime)
                .setParameter("endTime", endTime)
                .list();
        session.getTransaction().commit();
        return transactions;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Transaction> findByBuyerID(long buyerID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions = sessionFactory.getCurrentSession()
                .createQuery("from Transaction where buyerID=?")
                .setParameter(0, buyerID)
                .list();
        session.getTransaction().commit();
        return transactions;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Transaction> findBySellerID(long sellerID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions = sessionFactory.getCurrentSession()
                .createQuery("from Transaction where sellerID=?")
                .setParameter(0, sellerID)
                .list();
        session.getTransaction().commit();
        return transactions;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Transaction findByTransactionID(long transactionID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions = sessionFactory.getCurrentSession()
                .createQuery("from Transaction where transactionID=?")
                .setParameter(0, transactionID)
                .list();
        session.getTransaction().commit();
        if (transactions.size() > 0) {
            return transactions.get(0);
        } else {
            return null;
        }
    }

}
