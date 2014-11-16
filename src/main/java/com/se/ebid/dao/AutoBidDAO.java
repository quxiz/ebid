/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.dao;

import com.se.ebid.entity.AutoBid;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface AutoBidDAO {
 
    public void save(AutoBid autoBid);
     
    public List<AutoBid> list();
    
    public AutoBid findByItemID(long itemID);
     
}
