package cn.cobight.sbm.controller;

import cn.cobight.sbm.constants.ExceptionConstant;
import cn.cobight.sbm.constants.ReturnStatus;
import cn.cobight.sbm.entity.Dept;
import cn.cobight.sbm.service.DeptService;
import cn.cobight.sbm.util.CustomException;
import cn.cobight.sbm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * fileName:DeptController
 * description:
 * author:cobight
 * createTime:2020/10/12 17:11
 * version:1.0.0
 */
@RestController
@RequestMapping("dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("list")
    public Result list(){

        /*String id = null;
        if (StringUtils.isEmpty(id)){
            throw new CustomException(ExceptionConstant.ID_NOT_NULL.getErrorCode(),"");
        }*/
        //System.out.println(1/0);
        return new Result(ReturnStatus.SUCCESS.getReturnCode(),ReturnStatus.SUCCESS.getReturnMsg(),deptService.list());
    }

    @GetMapping("page")
    public Result page(@RequestParam Map paramMap){
    //public Result page(@RequestBody Map paramMap){ //     POST  json提交用requestbody
        return deptService.page(paramMap);
    }

    @GetMapping("getById")
    public  Result  getById(int deptNo){
        return deptService.getById(deptNo);
    }

    @PostMapping("add")
    public Result add(@RequestBody Dept dept){
        return deptService.add(dept);
    }

    @DeleteMapping("del{deptNo}")
    public Result del(@PathVariable Integer deptNo){
        return deptService.del(deptNo);
    }

    @PostMapping("upd")
    public Result upd(@RequestBody Dept dept){
        System.out.println(dept);
        return deptService.upd(dept);
    }
}
