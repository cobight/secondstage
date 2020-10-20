package com.aaa.ssm.entity;

/**
 * fileName:Dept
 * description:
 * author:zz
 * createTime:2020/9/25 10:56
 * version:1.0.0
 */
public class Dept {

    private Integer deptNo;
    private String dname;
    private String loc;

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
