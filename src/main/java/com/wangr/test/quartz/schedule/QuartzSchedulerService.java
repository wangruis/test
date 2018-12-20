package com.wangr.test.quartz.schedule;

import com.wangr.test.quartz.job.SchedulerQuartzJob1;
import com.wangr.test.quartz.job.SchedulerQuartzJob2;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王瑞
 * @description
 * @date 2018-12-14 16:25
 */
@Service
public class QuartzSchedulerService {

    @Autowired
    private Scheduler scheduler;

    // 开始任务
    public void startJob() throws SchedulerException {
        startJob1(scheduler);
        startJob2(scheduler);
        scheduler.start();
    }


    // 获取工作信息
    public String getJobInfo(String jobName, String groupName) throws SchedulerException {

        TriggerKey triggerKey = new TriggerKey(jobName, groupName);

        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(), scheduler.getTriggerState(triggerKey));

    }

    // 修改定时任务时间
    public void modifJobTime(String jobName, String groupName, String cronTime) throws SchedulerException {

        TriggerKey triggerKey = new TriggerKey(jobName, groupName);
//        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronTime);
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, groupName)
                .withSchedule(cronScheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }


    // 停止所有定时任务
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    // 停止某个任务
    public void pauseJob(String jobName, String groupName) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, groupName);
        scheduler.pauseJob(jobKey);
    }

    // 恢复所有任务
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    // 恢复某个任务
    public void resumeJob(String jobName, String groupName) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, groupName);
        scheduler.resumeJob(jobKey);
    }

    // 删除某个任务
    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.deleteJob(jobKey);
    }

    private void startJob1(Scheduler scheduler) throws SchedulerException {

        // 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
        // JobDetail 是具体Job实例
        JobDetail jobDetail = JobBuilder.newJob(SchedulerQuartzJob1.class).withIdentity("job1", "group1").build();
        // 基于表达式构建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");

        // CronTrigger 表达式触发器
        // TriggerBuilder 用于构建触发器
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("job1", "group1").withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }


    private void startJob2(Scheduler scheduler) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(SchedulerQuartzJob2.class).withIdentity("job2", "group2").build();

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("job2", "group2").withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);

    }
}
