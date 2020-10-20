package com.aaa.ssm.controller;

import com.aaa.ssm.entity.Dept;
import com.aaa.ssm.service.DeptService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * fileName:DeptController
 * description:
 * author:zz
 * createTime:2020/9/25 11:23
 * version:1.0.0
 */
@Controller
@RequestMapping("/dept")  //父路径映射  dept/list
public class DeptController  {

    @Autowired  //依赖注入 DI
    private DeptService deptService;

    /**
     * 部门列表   "/list","/listD"  地址栏中 请求地址为 /dept/list   /dept/listD
     * @return
     */
    @RequestMapping(value = {"/list","/listD"})
    public ModelAndView list(){
        //System.out.println(1/0);

      //  ModelAndView modelAndView =new ModelAndView();
       // modelAndView.setViewName("dept/list");
        //设置逻辑视图名
        ModelAndView modelAndView =new ModelAndView("dept/list");
        //绑定数据
         modelAndView.addObject("deptList",deptService.list());
        return modelAndView;
    }

    /**
     * 根据编号获取对象
     * @param deptNo
     * @param model
     * @return
     */
    //@ResponseBody
    @RequestMapping("toUpdate")
    public String toUpdate(Integer deptNo, Model model){
        model.addAttribute("dept",deptService.getById(deptNo));
        return "dept/update";
    }

    /**
     * 部门更新
     * @param dept
     * @param model
     * @return
     */
    @RequestMapping("update")
    public String update(Dept dept,Model model){
        try {
            deptService.update(dept);
            return "redirect:list.do";
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("errorInfo","操作失败");
        //转发
        return "forward:toUpdate.do";
    }

}
