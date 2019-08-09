package com.donbala.quartz.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @CLassName: JobTest
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/8/1-18:17
 * @Description: todo
 **/
@DisallowConcurrentExecution
public class JobTest implements Job {



    public void execute(JobExecutionContext context)
            throws JobExecutionException
    {
        System.out.println("2020309292393239023932923923");
    }


}