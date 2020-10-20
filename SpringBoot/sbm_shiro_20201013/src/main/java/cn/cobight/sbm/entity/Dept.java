package cn.cobight.sbm.entity;

import lombok.Data;

/**
 * fileName:Dept
 * description:
 * author:cobight
 * createTime:2020/10/12 16:54
 * version:1.0.0
 */
@Data   //默认加上getter setter constructor toString 等功能
public class Dept {
    private Integer deptNo;
    private String dname;
    private String loc;
}
