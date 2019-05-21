package com.restep.juc.timer;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.TimeUnit;

/**
 * @author restep
 * @date 2019/5/20
 */
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("=====================" + System.currentTimeMillis());
        try {
            TimeUnit.MINUTES.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
