package com.api.quartz;

import org.quartz.*;
import org.quartz.ee.jmx.jboss.QuartzService;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class QuartzDemo {
    public void quart() {
        try {
            System.out.println("quartzDemo");
            //创建一个scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.getContext().put("skey", "svalue");

            //创建一个Trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger", "myGroup")
                    .usingJobData("t1", "tv1")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)
                            .repeatForever()).build();
            trigger.getJobDataMap().put("t2", "tv2");
            //创建一个job
            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .usingJobData("j1", "jv1")
                    .withIdentity("myjob", "mygroup").build();
            job.getJobDataMap().put("j2", "jv2");
            //注册trigger并启动scheduler
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        }catch (Exception e){
            System.out.println("quartz出错!");
        }
    }
}