package cn.cobight.sbm.service;

import cn.cobight.sbm.entity.Dept;
import cn.cobight.sbm.util.Result;

import java.util.List;
import java.util.Map;

/**
 * fileName:DeptService
 * description:
 * author:cobight
 * createTime:2020/10/12 16:58
 * version:1.0.0
 */
public interface DeptService {

    List<Dept> list();

    Result page(Map paramMap);

    Result  getById(int deptNo);
    Result add(Dept dept);

    Result del(int deptNo);

    Result upd(Dept dept);
}
