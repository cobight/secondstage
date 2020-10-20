package com.aaa.sbm.controller;

import com.aaa.sbm.constants.ExceptionConstant;
import com.aaa.sbm.constants.ReturnStatus;
import com.aaa.sbm.entity.Dept;
import com.aaa.sbm.service.DeptService;
import com.aaa.sbm.util.CustomException;
import com.aaa.sbm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public Result list(){
       // System.out.println(1/0);
        String id=null;
        if(StringUtils.isEmpty(id)){
            throw new CustomException(ExceptionConstant.ID_NOT_NULL.getErrorCode(),
                    ExceptionConstant.ID_NOT_NULL.getErrorMessage());
        }
        return new Result(ReturnStatus.SUCCESS.getReturnCode(),
                ReturnStatus.SUCCESS.getReturnMsg(),deptService.list());
    }
    /**
     * 部门分页列表
     * @return
     */
    @GetMapping("page")
    public Result page(Map paramMap){
        return deptService.page(paramMap);
    }
    /**
     * 根据编号查询
     * @param deptNo
     * @return
     */
    @GetMapping("getById")
    public  Result  getById(int deptNo){
        return null;
    }

    /**
     * 添加
     * @param dept
     * @return
     */
    @PostMapping("add")
    public Result add(@RequestBody  Dept dept){
        return null;
    }

    /**
     * 更新
     * @param dept
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody  Dept dept){
        return null;
    }

    /**
     * 根据编号删除
     * @param deptNo
     * @return
     */
    @DeleteMapping("delete")
    public Result delete(int deptNo){
        return null;
    }
}
