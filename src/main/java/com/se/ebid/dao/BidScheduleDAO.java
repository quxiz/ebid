/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.dao;

import com.se.ebid.entity.BidSchedule;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface BidScheduleDAO {
 
    public void save(BidSchedule bidSchedule);
     
    public List<BidSchedule> list();
    
    public BidSchedule findByItemID(long itemID);
     
}
