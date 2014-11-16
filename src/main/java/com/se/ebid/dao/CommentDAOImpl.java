/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.dao;

import com.se.ebid.entity.Comment;
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
public class CommentDAOImpl implements CommentDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Comment comment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(comment);
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Comment> commentList = session.createQuery("from Comment").list();
        session.getTransaction().commit();
        return commentList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Comment findByItemID(long itemID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Comment> comments = new ArrayList<Comment>();
        comments = sessionFactory.getCurrentSession()
                .createQuery("from Comment where itemID=?")
                .setParameter(0, itemID)
                .list();
        session.getTransaction().commit();
        if (comments.size() > 0) {
            return comments.get(0);
        } else {
            return null;
        }
    }

}
