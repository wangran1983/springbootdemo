package com.donbala.service.impl;

import ch.qos.logback.classic.Logger;
import com.donbala.mapper.QuartzMapper;
import com.donbala.model.Quartz;
import com.donbala.quartz.MyTriggerListener;
import com.donbala.quartz.QuartzUtils;
import com.donbala.service.intf.QuartzServiceIntf;
import org.quartz.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @CLassName: QuatzService
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/8/2-15:18
 * @Description: todo
 **/
@Service
public class QuartzServiceImpl implements QuartzServiceIntf {

    public final static Logger log = (Logger) LoggerFactory.getLogger(QuartzServiceImpl.class);

    @Autowired
    QuartzMapper quartzMapper;


    @Autowired
    private SchedulerFactoryBean schedulerFactory;


/**
*@description: 获取所有的定时任务添加到调度器里面
*/
    public void initJobsOnstart() {
        log.info("开始初始化所有批处理任务");
        List<Quartz> list = quartzMapper.getAvailableJobPlan();
        if(list.size()>0) {
            for (Quartz qz : list) {
                //逐个将启动的任务计划添加到任务调度器中
                try {
                    addQuartz(qz);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            log.info("任务调度初始化完成");
        }
    }


    public  void addQuartz(Quartz qz) throws ClassNotFoundException, ParseException, SchedulerException {
        //根据jobPlanCode确定唯一的作业名称，作业组名，触发器名称，触发器组名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String jobCode = qz.getJobCode();
        String jobPlanCode = qz.getJobPlanCode();
        String className = qz.getJobClassName();

        Class<?> clazz = Class.forName(className);
        String cron = QuartzUtils.cron(qz);
        Date startDate = sdf.parse(qz.getStartDate());
        Date endDate = sdf.parse(qz.getEndDate());

        addJob(jobCode, jobPlanCode, jobPlanCode, jobPlanCode, clazz, cron, startDate, endDate);
    }

/**
*@description:  更改触发器执行开始时间 不是刚创建就执行  而是根据表达式执行
 * 如果触发器错过触发时间(例如手动暂停、服务器挂了),当启动任务后或服务器恢复正常后
 * 任务将从下一次触发时间再执行,否则停止任务再重新启动或服务器挂掉重新启动后任务会立即执行
 *
*/
    public void addJob(String jobCode, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass,
                       String cron, Date startDate, Date endDate) throws SchedulerException {

            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                                            .withIdentity(jobCode, jobGroupName).build();
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName);

            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing());
            triggerBuilder.startAt(startDate);
            triggerBuilder.endAt(endDate);
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            Scheduler scheduler = schedulerFactory.getScheduler();
            TriggerListener myTriggerListener = new MyTriggerListener();
            scheduler.getListenerManager().addTriggerListener(myTriggerListener);
            scheduler.scheduleJob(jobDetail, trigger);

            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
    }


    @Override
    public List<Quartz> getJobPlanList() {
        return quartzMapper.getAllJobPlan();
    }
}
