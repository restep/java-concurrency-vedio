package com.restep.juc.timer;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author restep
 * @date 2019/5/20
 */
public class QuartzSchedule {
    public static void main(String[] args) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("Job1", "Group1").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
