package cn.cobight.sbm.controller;

import cn.cobight.sbm.constants.ReturnStatus;
import cn.cobight.sbm.entity.Emp;
import cn.cobight.sbm.service.EmpService;
import cn.cobight.sbm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * fileName:EmpController
 * description:
 * author:cobight
 * createTime:2020/10/15 18:29
 * version:1.0.0
 */
@RestController
@RequestMapping("emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping("list")
    public Result list(){
        return new Result(ReturnStatus.SUCCESS.getReturnCode(),ReturnStatus.SUCCESS.getReturnMsg(),empService.list());
    }

    @GetMapping("page")
    public Result page(@RequestParam Map paramMap){
        return empService.page(paramMap);
    }

    @PostMapping("add")
    public Result add(@RequestBody Emp emp){
        return empService.add(emp);
    }

    @DeleteMapping("del{empNo}")
    public Result del(@PathVariable Integer empNo){
        return empService.del(empNo);
    }

    @PostMapping("upd")
    public Result upd(@RequestBody Emp emp){
        return empService.upd(emp);
    }
}
