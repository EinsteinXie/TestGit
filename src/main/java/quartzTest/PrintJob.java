package quartzTest;

import cn.hutool.core.date.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.Map;

/**
 * Author: xcq
 * Date: 2021/11/26 10:40 上午
 * FileName: PrintJob
 */
public class PrintJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Map<String, Object> map = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println("printJon.time:" + DateUtil.formatDateTime(new Date()));
        System.out.println(map.get("appId"));
    }
}
