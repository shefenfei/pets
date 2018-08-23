package com.fenfei.pets.timer;

import com.fenfei.pets.drools.DroolRuleService;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.time.impl.CronExpression;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 动态定时任务调度管理器
 */
@Component
@Scope("singleton")
@Slf4j
public class QuartzJobManager implements ApplicationContextAware {

    private final String JOB_TASK_GROUP_NAME = "job_shefenfei_";

    private SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    private Scheduler scheduler;

    private ApplicationContext applicationContext;

    @Autowired
    private DroolRuleService droolRuleService;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    public void init() {
        //启动所有任务
        try {
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("init Scheduler failed");
        }
    }


    //动态添加一个定时任务
    public boolean addJob(String jobName, String jobClass, String cronExp) {
        boolean result = false;
        if (!CronExpression.isValidExpression(cronExp)) {
            log.error("Illegal cron expression format : {} ", cronExp);
            return false;
        }

        try {
            //由JobBuilder 定义与生成JobDetail
            //JobDetail: 定义Job的实例。接收Job名字、描述以及Job类等信息
            JobDetail jobDetail = JobBuilder.newJob().withIdentity(new JobKey(jobName, JOB_TASK_GROUP_NAME))
                    .ofType((Class<Job>) Class.forName(jobClass))
                    .build();

            JobDataMap jobDataMap = jobDetail.getJobDataMap();
            jobDataMap.put("droolService", droolRuleService);
            jobDataMap.put("testKey", "vava");

            //由TriggerBuilder 定义与生成trigger
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                    .withIdentity(new TriggerKey(jobName, JOB_TASK_GROUP_NAME))
                    .build();

            //注册 jobdetail与trigger 到 scheduler 中
            scheduler.scheduleJob(jobDetail, trigger);
            //启动任务
            scheduler.start();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    //删除一个定时任务
    public boolean deleteJob(String jobName) {
        boolean result = false;
        JobKey jobKey = new JobKey(jobName, JOB_TASK_GROUP_NAME);
        try {
            if (scheduler.checkExists(jobKey)) {
                result = scheduler.deleteJob(jobKey);
            } else {
                log.error("delete job name:{},group name:{} not exists.", jobName, jobKey.getGroup());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("delete job name:{},group name:{} failed!", jobKey.getName(), jobKey.getGroup());
        }
        return result;
    }


    //更新一个定时任务
    public boolean updateJob(String jobName, String cronExp) {
        boolean result = false;
        if (!CronExpression.isValidExpression(cronExp)) {
            log.error("Illegal cron expression format({})", cronExp);
            return result;
        }

        JobKey jobKey = new JobKey(jobName, JOB_TASK_GROUP_NAME);
        TriggerKey triggerKey = new TriggerKey(jobName, JOB_TASK_GROUP_NAME);

        try {
            if (scheduler.checkExists(jobKey) && scheduler.checkExists(triggerKey)) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                Trigger trigger = TriggerBuilder.newTrigger()
                        .forJob(jobName)
                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                        .withIdentity(new TriggerKey(jobName, JOB_TASK_GROUP_NAME))
                        .build();

                scheduler.rescheduleJob(triggerKey , trigger);
                result = true;
            } else {
                log.error("update job name:{},group name:{} or trigger name:{},group name:{} not exists..",
                        jobKey.getName(), jobKey.getGroup(), triggerKey.getName(), triggerKey.getGroup());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("update job name:{},group name:{} failed!", jobKey.getName(), jobKey.getGroup());
        }
        return result;
    }
}
