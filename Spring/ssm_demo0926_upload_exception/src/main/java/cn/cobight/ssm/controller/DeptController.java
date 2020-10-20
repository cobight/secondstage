package cn.cobight.ssm.controller;

import cn.cobight.ssm.dao.DeptDao;
import cn.cobight.ssm.entity.Dept;
import cn.cobight.ssm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * fileName:DeptController
 * description:
 * author:cobight
 * createTime:2020/9/25 11:23
 * version:1.0.0
 */
@Controller
@RequestMapping("dept") //父路径，/dept
public class DeptController {
    @Autowired
    //private DeptDao deptDao;
    private DeptService deptService;

    @RequestMapping(value = {"list","listD"}) //可以 /list ,      dept/list
    public ModelAndView list(){
        //ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("dept/list");
        ModelAndView modelAndView = new ModelAndView("dept/list");
        modelAndView.addObject("deptList",deptService.list());
        return modelAndView;
    }
    @RequestMapping("toUpdate")
    public String toUpdate(Integer deptNo, Model model){
        model.addAttribute("dept",deptService.getById(deptNo));
        return "dept/update";
    }
    /*部门更新*/
    @RequestMapping("update")
    public String update(Dept dept,Model model){
        int result = deptService.update(dept);
        if (result>0){
            // 重定向
            return "redirect:list.do";
        }
        model.addAttribute("erroInfo","操作失败");
        //转发
        return "forward:toUpdate.do";
    }

    @RequestMapping("toInsert")
    public String toInsert(){
        return "dept/insert";
    }

    @RequestMapping("insert")
    public String insert(Dept dept,Model model){
        try{
            int result = deptService.insert(dept);
            if (result>0){
                return "redirect:list.do";
            }
            model.addAttribute("erroInfo","添加失败");
            return "dept/insert";
        }catch (Exception e){
            System.out.println(e.getClass().getName());
            model.addAttribute("erroInfo","异常崩溃");
            return "dept/insert";
        }
    }

    @RequestMapping("toDelete")
    public String delete(Integer deptNo){
        deptService.delete(deptNo);
        return "redirect:list.do";
    }
}
