package com.donbala.controller;

import ch.qos.logback.classic.Logger;
import com.donbala.model.Quartz;
import com.donbala.service.intf.QuartzServiceIntf;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @CLassName: JobplanController
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/8/8-14:51
 * @Description: todo
 **/
@Controller
public class JobplanController {

    public final static Logger log = (Logger) LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private QuartzServiceIntf quartzServiceIntf;

    @GetMapping("/controller/queryjobplanlist")
    @ResponseBody
    public List<Quartz> getJobPlanList() {
        return quartzServiceIntf.getJobPlanList();
    }
}
