/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.dao;

import com.se.ebid.entity.Blacklist;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface BlacklistDAO {
 
    public void save(Blacklist blacklist);
     
    public List<Blacklist> list();
     
}
