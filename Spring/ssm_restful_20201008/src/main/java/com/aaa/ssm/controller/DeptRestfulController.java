package com.aaa.ssm.controller;

import com.aaa.ssm.entity.Dept;
import com.aaa.ssm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * fileName:DeptRestfulController
 * description:
 * author:zz
 * createTime:2020/10/8 9:55
 * version:1.0.0
 */
@Controller
@RequestMapping("deptRestful")
public class DeptRestfulController {

    @Autowired
    private DeptService deptService;

    /**
     * 部门列表
     * @return
     */
    @ResponseBody  // 不再走视图解析器，而是直接把返回值解析后放入response 的body中返回
    //method = RequestMethod.GET 标识该方法只能是GET请求，代表获取资源
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public List<Dept>  list(){
        return deptService.list();
    }

    /**
     * 根据编号获取部门信息
     * @param deptNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getById",method = RequestMethod.GET)
    public Dept  getById(Integer deptNo){
        return deptService.getById(deptNo);
    }

    /**
     * 部门添加
     * @param dept
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add",method = RequestMethod.POST)  //post 新建资源
    //RequestBody  把请求的参数封装为json字符串，并且放入request的body中   使用配置文件中配置的
   //  FastJsonHttpMessageConverter 把json字符串转为dept对象
    public  Object  add(@RequestBody Dept dept){
        System.out.println("start to add");
        return deptService.add(dept);
    }

    /**
     * 部门更新
     * @param dept
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Object update(@RequestBody Dept dept){
        return deptService.update(dept);
    }

    /**
     * 部门删除   讲解PathVariable
     * @param deptNo
     * @param deptName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteBak/{deptId}-{dname}.do",method = RequestMethod.GET) //@PathVariable  路径变量   可以从请求路径中，获取变量
    public Object deleteBak(@PathVariable("deptId") Integer deptNo,@PathVariable("dname") String deptName){
        System.out.println("接受的参数为："+deptNo+","+deptName);
        return null;
    }
    /**
     * 部门删除   讲解PathVariable
     * @param deptNo
     * @return
     */
    @ResponseBody
  //  @RequestMapping(value = "/{deptId}/delete",method = RequestMethod.DELETE) //@PathVariable  路径变量   可以从请求路径中，获取变量
    @RequestMapping(value = "/delete/{deptId}.do",method = RequestMethod.DELETE)
    public Object delete(@PathVariable("deptId") Integer deptNo){
        return deptService.delete(deptNo);
    }
}
