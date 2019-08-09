package com.donbala.quartz;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @CLassName: QuartzConfig
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/8/2-14:27
 * @Description: todo
 **/
@Configuration
public class QuartzConfig {

    @Autowired
    QuartzJobFactory quartzJobFactory;

    @Bean
    public SchedulerFactoryBean scheduler() throws SchedulerException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(quartzJobFactory);
        return factory;
    }

}
