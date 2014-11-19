/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.quartz.QuartzJob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.transaction.Transactional;
import org.quartz.CronScheduleBuilder;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Quxiz
 */
@Service
public class TestQuartzServiceImpl implements TestQuartzService{
    
    @Override
    @Transactional
    public void addQuartz() {
        java.util.Date nowDate= new java.util.Date();
        java.util.Date date = new java.util.Date(nowDate.getTime() + (1000 * 60));
        //String cronDate = "" + date.getSeconds() + " " + date.getMinutes() + " " + date.getHours() + " " + date.getDate() + " " + date.getMonth() + " " + "*" + " " + date.getYear();
        SimpleDateFormat formatter = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String cronDate = formatter.format(date);
        try {
            //SchedulerFactory schdFact = new StdSchedulerFactory("quartz.properties");
            //Scheduler scheduler = schdFact.getScheduler();
            Scheduler scheduler;
            // Setup the Job class and the Job group
            JobDetail job = newJob(QuartzJob.class).withIdentity(
                    "CronQuartzJob" + new Timestamp(date.getTime()), "Group").build();

            // Create a Trigger that fires every 5 minutes.
            Trigger trigger = newTrigger()
                    .withIdentity("" + new Timestamp(date.getTime()), "Group")
                    .withSchedule(cronSchedule(cronDate))
                    .build();

            // Setup the Job and Trigger with Scheduler & schedule jobs
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
