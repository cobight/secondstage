package cn.cobight.sbm.entity;

import lombok.Data;

import java.util.Date;

/**
 * fileName:Emp
 * description:
 * author:cobight
 * createTime:2020/10/15 17:38
 * version:1.0.0
 */
@Data
public class Emp {
    private Integer empNo;//员工号
    private String ename;//员工姓名
    private String job;//工作
    private Integer mgr;//上司编号
    private Date hireDate;//雇用日期
    private double sal;//工资salary
    private double comm;//奖金commission
    private Integer deptNo;//部门号
}
