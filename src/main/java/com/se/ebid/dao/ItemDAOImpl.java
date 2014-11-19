/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.dao;

import com.se.ebid.controller.SearchForm;
import com.se.ebid.entity.Item;
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
public class ItemDAOImpl implements ItemDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Item item) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(item);
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Item> itemList = session.createQuery("from Item").list();
        session.getTransaction().commit();
        return itemList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Item findByItemID(long itemID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Item> items = new ArrayList<Item>();
        items = sessionFactory.getCurrentSession()
                .createQuery("from Item where itemID=?")
                .setParameter(0, itemID)
                .list();
        session.getTransaction().commit();
        if (items.size() > 0) {
            return items.get(0);
        } else {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Item> search(SearchForm searchForm) {
        String category = searchForm.getCategory();
        String keyword = searchForm.getKeyword();
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Item> items = new ArrayList<Item>();
        items = sessionFactory.getCurrentSession()
                .createQuery("from Item where (category=? and) lower(title) like lower('%\"+ ? +\"%')")
                .setParameter(0, category)
                .setParameter(1, keyword)
                .list();
        session.getTransaction().commit();
        return items;
    }

}
