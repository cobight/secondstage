package cn.cobight.sbm.controller;

import cn.cobight.sbm.entity.Dept;
import cn.cobight.sbm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Dept> list(){
        return deptService.list();
    }

    @PutMapping(value = "add")
    public String add(Dept dept){
        return deptService.add(dept)==1?"{code:1}":"{code:0}";
    }

    @DeleteMapping("del{deptNo}")
    public String del(@PathVariable Integer deptNo){
        return deptService.del(deptNo)==1?"{code:1}":"{code:0}";
    }
    @PostMapping("upd")
    public String upd(Dept dept){
        System.out.println(dept);
        return deptService.upd(dept)==1?"{code:1}":"{code:0}";
    }
}
