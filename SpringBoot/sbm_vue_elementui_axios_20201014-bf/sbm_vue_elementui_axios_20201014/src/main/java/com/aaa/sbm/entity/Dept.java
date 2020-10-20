package com.aaa.sbm.entity;

import lombok.Data;

/**
 * fileName:Dept
 * description:
 * author:zz
 * createTime:2020/10/12 11:51
 * version:1.0.0
 */
@Data  //默认加上getter setter   constructor  toString 等功能
public class Dept {

    private Integer deptNo;
    private String dname;
    private String loc;
}
