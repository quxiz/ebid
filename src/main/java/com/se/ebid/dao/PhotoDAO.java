/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.dao;

import com.se.ebid.entity.Photo;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface PhotoDAO {
 
    public void save(Photo photo);
     
    public List<Photo> list();
    
    public Photo findByItemID(long itemID);
     
}
