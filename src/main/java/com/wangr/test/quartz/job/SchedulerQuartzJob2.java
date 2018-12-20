package com.wangr.test.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author 王瑞
 * @description
 * @date 2018-12-14 16:19
 */
public class SchedulerQuartzJob2 implements Job {

    private void before() {
        System.out.println("++++++++++++++  【job2 任务开始】  +++++++++++++++++++++");
        System.out.println("开始：" + System.currentTimeMillis());
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        before();

        after();
    }

    private void after() {
        System.out.println("结束：" + System.currentTimeMillis());
        System.out.println("++++++++++++++  【job2 任务结束】  +++++++++++++++++++++");
    }

}
