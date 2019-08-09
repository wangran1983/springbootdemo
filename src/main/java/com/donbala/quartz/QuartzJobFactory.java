package com.donbala.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @CLassName: QuartzConfig
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/8/2-14:27
 * @Description: quartz的实例是AdaptableJobFactory在运行时创建的，正常情况下无法使用spring容器的注入类，
 * 该类的作用是在创建Job实例时，使用spring容器的方法将容器中的bean注入到Job实例中
 **/
@Component
public class QuartzJobFactory extends AdaptableJobFactory {
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);//创建Job实例
        autowireCapableBeanFactory.autowireBean(jobInstance);//Ioc容器向job实例中通过autowire注解注入bean
        return jobInstance;
    }
}
