package quartzTest;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: xcq
 * Date: 2021/11/26 10:43 上午
 * FileName: TestScheduler
 */
public class TestScheduler {
    public static void main(String[] args) throws Exception {
        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(PrintJob.class)
                .withIdentity("job1", "group1").build();
        Map<String, Object> params = new HashMap<>();
        params.put("appId", "123");
        jobDetail.getJobDataMap().putAll(params);

        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1").withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ? "))
                .build();

        //4、执行
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();

//        TimeUnit.MINUTES.sleep(1);
//
//        CronTrigger newCronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "triggerGroup1").withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ? "))
//                .build();
//
//        Date date = scheduler.rescheduleJob(TriggerKey.triggerKey("trigger1", "triggerGroup1"), newCronTrigger);
//        if (null == date) {
//            System.out.println("替换失败");
//        }

        System.out.println("### 打印内存中的所有 Job的状态 ###");
        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
        for (TriggerKey tKey : triggerKeys) {
            CronTrigger t = (CronTrigger) scheduler.getTrigger(tKey);
            System.out.println("Trigger details: " + t.getJobKey().getName() + ", " + t.getJobKey().getGroup()
                                       + ", " + scheduler.getTriggerState(tKey) + ", " + t.getFinalFireTime() + ", "
                                       + t.getCronExpression());
        }
//        TimeUnit.MINUTES.sleep(1);
//
//        System.out.println("### 打印内存中的所有 Job的状态 ###");
//        Set<TriggerKey> triggerKeys2 = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
//        for (TriggerKey tKey : triggerKeys2) {
//            CronTrigger t = (CronTrigger) scheduler.getTrigger(tKey);
//            System.out.println("Trigger details: " + t.getJobKey().getName() + ", " + t.getJobKey().getGroup()
//                                       + ", " + scheduler.getTriggerState(tKey) + ", " + t.getFinalFireTime() + ", "
//                                       + t.getCronExpression());
//        }

        System.out.println("--------end ! ------------");
    }
}
