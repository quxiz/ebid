/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.dao;

import com.se.ebid.controller.SearchForm;
import com.se.ebid.entity.Item;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface ItemDAO {
 
    public void save(Item item);
     
    public List<Item> list();
    
    public Item findByItemID(long itemID);
    
    public List<Item> search(SearchForm searchForm);
     
}
