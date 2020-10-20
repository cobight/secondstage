package cn.cobight.dao;

import cn.cobight.entity.Dept;
import cn.cobight.util.Page;

import java.util.List;
import java.util.Map;

/**
 * fileName:DeptDao
 * description:
 * author:cobight
 * createTime:2020/9/21 20:09
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
}
