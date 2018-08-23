package com.fenfei.pets.controller;

import com.fenfei.pets.timer.QuartzJobManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/quartz")
public class QuartzDemoController {

    @Autowired
    private QuartzJobManager quartzJobManager;

    /**
     * 添加一个任务
     * @return
     */
    @PostMapping("/addJob")
    public Map<String , Object> addJob(String jobClass) {
        quartzJobManager.init();
        quartzJobManager.addJob("demoJob" , jobClass , "0/5 * * * * ?");
        Map<String, Object> resp = new HashMap<>();
        return resp;
    }

    /**
     * 删除一个任务
     * @return
     */
    @PostMapping("/deleteJob")
    public Map<String , Object> deleteJob(String jobName) {
//        quartzJobManager.init();
        quartzJobManager.deleteJob(jobName);
        Map<String, Object> resp = new HashMap<>();
        return resp;
    }

    /**
     * 添加一个任务
     * @return
     */
    @PostMapping("/updateJob")
    public Map<String , Object> updateJob(String jobName , String cronExp) {
//        quartzJobManager.init();
        quartzJobManager.updateJob(jobName , cronExp);
        Map<String, Object> resp = new HashMap<>();
        return resp;
    }

}
