package com.aaa.ssm.schedule;

import com.aaa.ssm.entity.Dept;
import com.aaa.ssm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * fileName:DeptSchedule
 * description:
 * author:zz
 * createTime:2020/9/29 11:30
 * version:1.0.0
 */
@Component  //交给spring 管理，不属于任何分层
public class DeptSchedule {

    @Autowired  // 依赖注入
    private DeptService deptService;

    /**
     * 定时向部门备份表添加数据
     *   *      *      *     *    *     ?
     *   秒    分钟    时    日   月    周
     *  0-59   0-59   0-23   1-31 1-12  1-7
     *   /  每
     *   -   开始到结束  10-20  第10秒开始 到20秒，每秒都执行一次
     *   ,   指定时间    10,20,30  每分钟的第10秒，第20秒，第30秒执行一次
     */
    @Scheduled(cron="*/5 * * * * ?")
    public void  addDeptBak(){
        Dept dept =new Dept();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        dept.setDname("开发"+simpleDateFormat.format(date));
        dept.setLoc(simpleDateFormat.format(date)+"楼");
        deptService.addBak(dept);
    }
}
