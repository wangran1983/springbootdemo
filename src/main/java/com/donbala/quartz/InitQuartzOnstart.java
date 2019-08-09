package com.donbala.quartz;

import com.donbala.service.intf.QuartzServiceIntf;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @CLassName: InitQuartzOnstart
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/8/7-13:07
 * @Description: todoq
 **/
public class InitQuartzOnstart {

   /* @Autowired
    SchedulerFactoryBean schedulerFactoryBean ;
*/

   @Autowired
   QuartzServiceIntf quartzService;

    @PostConstruct
    public void startQuartz() throws SchedulerException {
        quartzService.initJobsOnstart();
/*        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = newJob(JobTest.class)
                .withIdentity("myjob1","group1")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("mytrigger", "group1")
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();*/
    }

}
