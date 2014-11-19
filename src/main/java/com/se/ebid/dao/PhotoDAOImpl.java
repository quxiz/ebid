/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.dao;

import com.se.ebid.entity.Photo;
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
public class PhotoDAOImpl implements PhotoDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long save(Photo photo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(photo);
        session.getTransaction().commit();
        return photo.getPhotoID();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Photo> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Photo> photoList = session.createQuery("from Photo").list();
        session.getTransaction().commit();
        return photoList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Photo> findByItemID(long itemID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Photo> photoList = new ArrayList<Photo>();
        photoList = sessionFactory.getCurrentSession()
                .createQuery("from Photo where itemID=?")
                .setParameter(0, itemID)
                .list();
        session.getTransaction().commit();
        return photoList;
    }

}
