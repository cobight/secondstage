package cn.cobight.sbm.service;

import cn.cobight.sbm.entity.Emp;
import cn.cobight.sbm.util.Result;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * fileName:EmpService
 * description:
 * author:cobight
 * createTime:2020/10/15 20:51
 * version:1.0.0
 */
public interface EmpService {
    List<Emp> list();

    Result page(Map paramMap);

    int  pageCount(Map paramMap);

    Result getById(int empNo);

    Result add(Emp emp);

    Result del(int empNo);

    Result upd(Emp emp);
}
