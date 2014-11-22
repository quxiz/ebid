/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.quartz;

/**
 *
 * @author Quxiz
 */
import com.se.ebid.service.ItemService;
import java.sql.Timestamp;
import javax.transaction.Transactional;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
@Component
public class ReportBidResultJob extends QuartzJobBean {

    @Autowired
    private ItemService itemService;

    @Override
    public void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        try {
        System.out.println("test test");
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        java.util.Date nowDate= new java.util.Date();
        System.out.println("ReportBidResultJob :" + new Timestamp(nowDate.getTime()));
        long itemID = (Long) context.getMergedJobDataMap().get("itemID");
        System.out.println("Got itemID : " + itemID);
        if(this.itemService == null) { 
            System.out.println("itemService null");
        } else {
            System.out.println("itemService not null");
        }
        this.itemService.reportBidResult(itemID);
        System.out.println("itemService call finish");
        } catch(Exception e){
            System.out.println(e.getCause());
        }
    }
}
