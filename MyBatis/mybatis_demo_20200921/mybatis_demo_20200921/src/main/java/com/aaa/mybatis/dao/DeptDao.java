package com.aaa.mybatis.dao;

import com.aaa.mybatis.entity.Dept;
import com.aaa.mybatis.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * fileName:DeptDao
 * description:
 * author:zz
 * createTime:2020/9/15 11:14
 * version:1.0.0
 */
public interface DeptDao {


    /**
     * 调用插件分页
     * @param page
     * @return
     */
    List<Dept> pageDeptPlugin(Page page);
    /**
     * 分页带参数查询
     * @param map
     * @return
     */
    List<Dept>  pageDept(Map map);

    /**
     * 带参查询分页总数量
     * @param map
     * @return
     */
    int pageDeptCount(Map map);

    /**
     * 部门列表
     * @return
     */
   List<Dept> listDept();

    /**
     * 部门更新（set+if根据参数动态拼接更新语句）
     * @param dept
     * @return
     */
    int  updateDeptByParam(Dept dept);

    /**
     * 部门删除
     * @param deptNo
     * @return
     */
    int  deleteById(int deptNo);
   /* int  deleteById(@Param("dn") int deptNo,@Param("de") String dname);*/

    /**
     * 部门添加
     * @param dept
     * @return
     */
   int add(Dept dept);

}
