package cn.cobight.mybatis.entity;

import cn.cobight.mybatis.entity.Dept;

/**
 * fileName:Emp
 * description:
 * author:cobight
 * createTime:2020/9/16 22:00
 * version:1.0.0
 */
public class Emp {

    private Integer empNo;
    private String empName;
    private Double salary;
    private String job;
    //多对一，一对一
    private Dept dept;

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
