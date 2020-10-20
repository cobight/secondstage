package com.aaa.sbm.controller;

import com.aaa.sbm.service.PowerService;
import com.aaa.sbm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * fileName:PowerController
 * description:
 * author:zz
 * createTime:2020/10/15 11:54
 * version:1.0.0
 */
@RestController
@RequestMapping("power")
public class PowerController {

    @Autowired
    private PowerService powerService;

    /**
     * 树形数据
     * @return
     */
    @GetMapping("treeData")
    public Result  powerMenuTreeData(){
       return powerService.treeData();
    }
}
