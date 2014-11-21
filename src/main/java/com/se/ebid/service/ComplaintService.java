/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.controller.ComplaintForm;
import com.se.ebid.controller.SolveComplaintForm;
import com.se.ebid.entity.Complaint;
import java.util.List;

/**
 *
 * @author Nuttapong
 */
public interface ComplaintService {
    public boolean complaint(ComplaintForm complaintForm);
    public List<Complaint> getComplaint();
    public boolean solveComplaint(SolveComplaintForm solveComplaintForm);
}
