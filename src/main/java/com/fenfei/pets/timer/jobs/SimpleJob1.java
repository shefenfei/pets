package com.fenfei.pets.timer.jobs;

import com.fenfei.pets.drools.DroolRuleService;
import com.fenfei.pets.models.CustomTemplate;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
    Job: 就是要执行的任务，该接口只有一个方法execute（JobExecutionContext jobExecutionContext)方法，Job运行时的信息保存在JobDataMap中（JobDataMap下面介绍）
    JobDetail: 定义Job的实例。接收Job名字、描述以及Job类等信息
    Trigger: 触发Job运行的东西，主要包括四类：SimpleTriger、CronTrigger、DateIntervalTrigger和NthIncludeDayTrigger，目前常用的是前两种
    JobBuilder: 定义和创建JobDetail实例的接口
    TriggerBuilder: 定义和创建Trigger实例的接口
    Scheduler: quartz的运行容器，Trigger和JobDetail可以注册到scheduler中，
    两者在Scheduler中拥有各自的组及名称(组及名称是Scheduler查找定位容器中某一对象的依据)，
    Trigger和JobDetail的组合名称都必须唯一，但是两者的组和名称可以相同，因为它们是不同类，一个job可以对应多个trigger，但是一个trigger只能对应一个job
 */
//一个具体的job 要执行的任务
//尽管Job类不好定义静态变量，但是可以通过JobDataMap传递数据，JobDataMap是JobDetail的成员变量，可以借助JobDataMap为Job实例提供属性/配置，可以通过它来追踪Job的执行状态等等
@Component
public class SimpleJob1 implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();

        DroolRuleService droolService = (DroolRuleService) dataMap.get("droolService");
        CustomTemplate template = droolService.queryById(2);
        System.out.println(template);

        System.out.println(
                "In SimpleJob1 - executinog its JOB at " + new Date() + "by" +
                        dataMap.get("testKey") + " " +
                        jobExecutionContext.getJobDetail().getJobDataMap().get("testKey") + " " +
                        jobExecutionContext.getJobDetail().getKey() + "\n" +
                        jobExecutionContext.getJobDetail().toString()
        );
    }
}
