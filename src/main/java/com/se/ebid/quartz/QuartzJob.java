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
import java.sql.Timestamp;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        java.util.Date nowDate= new java.util.Date();
        System.out.println("Java web application + Quartz 2.2.1 :" + new Timestamp(nowDate.getTime()));
    }
}
