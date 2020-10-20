package cn.cobight.sbm.service;

import cn.cobight.sbm.util.Result;

/**
 * fileName:PowerService
 * description:
 * author:zz
 * createTime:2020/10/15 11:37
 * version:1.0.0
 */
public interface PowerService {

    /**
     * 树形菜单数据
     * @return
     */
    Result treeData();
    Result treeData(Integer roleId);
}
