/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.controller.ComplaintForm;
import com.se.ebid.controller.SolveComplaintForm;
import com.se.ebid.entity.Complaint;
import com.se.ebid.dao.ComplaintDAO;
import com.se.ebid.dao.MessageDAO;
import com.se.ebid.entity.Message;
import java.sql.Timestamp;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nuttapong
 */
public class ComplaintServiceImpl implements ComplaintService{

    private ComplaintDAO complaintDAO;
    private MessageDAO messageDAO;
    
    @Autowired
    public void setComplaintDAO(ComplaintDAO complaintDAO){
        this.complaintDAO = complaintDAO;
    }
    
    @Autowired
    public void setMessageDAO(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    
    @Override
    @Transactional
    public boolean complaint(ComplaintForm complaintForm) {
        long MemberID = Common.getMemberID();
        Complaint complaint = new Complaint();
        complaint.setComplaintTitle(complaintForm.getTitle());
        complaint.setComplaintDetail(complaintForm.getDetail());
        complaint.setComplainterID(MemberID);
        complaint.setComplaintTimestamp(new Timestamp(System.currentTimeMillis()));
        this.complaintDAO.save(complaint);
        
        Message message = new Message();
        message.setSenderID(MemberID);
        message.setReceiverID(Common.ADMIN_ID);
        message.setMessage("complaint");
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        message.setSeen(false);
        this.messageDAO.save(message);
        
        return true;
    }

    @Override
    public List<Complaint> getComplaint() {
        return this.complaintDAO.getComplaint();
    }

    @Override
    @Transactional
    public boolean solveComplaint(SolveComplaintForm solveComplaintForm) {
        Complaint complaint = this.complaintDAO.findByComplaintID(solveComplaintForm.getComplaintID());
        if(complaint == null) return false;
        complaint.setSolverID(solveComplaintForm.getSolverID());
        complaint.setSolveDetail(solveComplaintForm.getDetail());
        complaint.setSolveTimestamp(new Timestamp(System.currentTimeMillis()));
        this.complaintDAO.save(complaint);
        
        Message message = new Message();
        message.setSenderID(solveComplaintForm.getSolverID());
        message.setReceiverID(complaint.getComplainterID());
        message.setMessage("solve complaint");
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        message.setSeen(false);
        this.messageDAO.save(message);
        
        return true;
    }
    
}
