package com.aaa.sbm.dao;

import com.aaa.sbm.entity.TreeNode;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * fileName:PowerDao
 * description:
 * author:zz
 * createTime:2020/10/15 11:33
 * version:1.0.0
 */
public interface PowerDao {

    /**
     * 查询所有菜单数据
     * @return
     */
    @Select("select power_id as id, power_name label, parent_id parentId, icon, menu_url menuUrl from tb_power")
    List<TreeNode>  listAll();

}
