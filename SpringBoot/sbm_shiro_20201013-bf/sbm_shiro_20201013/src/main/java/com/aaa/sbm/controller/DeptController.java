package com.aaa.sbm.controller;

import com.aaa.sbm.entity.Dept;
import com.aaa.sbm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * fileName:DeptController
 * description:
 * author:zz
 * createTime:2020/10/12 11:55
 * version:1.0.0
 */
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 部门列表
     * @return
     */
    @GetMapping("list")
    //@PutMapping
    public List<Dept>  list(){
        return deptService.list();
    }
}
