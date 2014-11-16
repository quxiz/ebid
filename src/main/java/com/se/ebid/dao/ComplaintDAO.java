/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.dao;

import com.se.ebid.entity.Complaint;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface ComplaintDAO {
 
    public void save(Complaint complaint);
     
    public List<Complaint> list();
    
    public List<Complaint> getComplaint();
    
    public Complaint findByComplaintID(long complaintID);
     
}
