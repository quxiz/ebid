/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Complaint;
import com.se.ebid.entity.Member;
import com.se.ebid.service.ComplaintService;
import com.se.ebid.service.MemberService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mtmmoei
 */
@Controller
public class SolveComplaintController {

    private ComplaintService complaintService;

    @Autowired
    public void setComplaintService(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/solveComplaint")
    public String viewSolveComplaint(Model model) {
        model.addAttribute("title", "ตอบข้อร้องเรียน");
        List<Complaint> listComplaints = this.complaintService.getComplaint();
        model.addAttribute("listComplaints", listComplaints);
//        SolveComplaintForm solveComplaintForm = new SolveComplaintForm();
//        model.addAttribute("solveComplaintForm", solveComplaintForm);
        model.addAttribute("listSize", listComplaints.size());
        List<String> listUser = new ArrayList();
        for (Complaint listComplaint : listComplaints) {
            Member member = this.memberService.getMemberByMemberID(listComplaint.getComplainterID());
            if (member != null) {
                listUser.add(member.getUserID());
            }
        }
        model.addAttribute("listUser", listUser);

        return "solveComplaintView";
    }

    @RequestMapping(value = "/solveComplaint/{complaintID}", method = RequestMethod.GET)
    public String viewSolveComplaintID(@PathVariable("complaintID") long complaintID, Model model) {
        model.addAttribute("title", "ตอบข้อร้องเรียน");
        SolveComplaintForm solveComplaintForm = new SolveComplaintForm();
        solveComplaintForm.setComplaintID(complaintID);
        solveComplaintForm.setSolverID(this.memberService.getMember().getMemberID());
        model.addAttribute("solveComplaintForm", solveComplaintForm);

        return "solveComplaintIDView";
    }

    @RequestMapping(value = "/solveComplaint/submit", method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("solveComplaintForm") SolveComplaintForm solveComplaintForm, Model model) throws UnsupportedEncodingException {
        solveComplaintForm.setDetail(new String(solveComplaintForm.getDetail().getBytes("iso8859-1"), "UTF-8"));
        boolean isSuccess = false;
        if (!solveComplaintForm.getDetail().equals("")) {
            isSuccess = this.complaintService.solveComplaint(solveComplaintForm);
        }
        model.addAttribute("isSuccess", isSuccess);
        if (isSuccess) {
            model.addAttribute("text", "ตอบข้อร้องเรียนเรียบร้อย ");
        } else {
            model.addAttribute("text", "ตอบข้อร้องเรียนล้มเหลว ");

        }
        model.addAttribute("link", "/solveComplaint");
        model.addAttribute("btnText", "กลับหน้าดูข้อร้องเรียน");
        return "showView";
    }
}
