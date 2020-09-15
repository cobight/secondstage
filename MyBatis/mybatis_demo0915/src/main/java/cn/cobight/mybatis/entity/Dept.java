package cn.cobight.mybatis.entity;

/**
 * fileName:Dept
 * description:
 * author:cobight
 * createTime:2020/9/15 20:29
 * version:1.0.0
 */
public class Dept {

    private Integer deptNo;
    private String deptName;
    private String loc;

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
