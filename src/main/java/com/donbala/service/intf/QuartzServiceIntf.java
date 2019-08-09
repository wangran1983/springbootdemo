package com.donbala.service.intf;

import com.donbala.model.Quartz;

import java.util.List;

/**
 * @CLassName: QuartzServiceIntf
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/8/7-17:24
 * @Description: todo
 **/
public interface QuartzServiceIntf {
      void initJobsOnstart();

      List<Quartz> getJobPlanList();
}
