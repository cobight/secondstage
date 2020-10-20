package com.aaa.sbm.service;

import com.aaa.sbm.constants.ExceptionConstant;
import com.aaa.sbm.constants.ReturnStatus;
import com.aaa.sbm.dao.DeptDao;
import com.aaa.sbm.entity.Dept;
import com.aaa.sbm.util.CustomException;
import com.aaa.sbm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fileName:DeptServiceImpl
 * description:
 * author:zz
 * createTime:2020/10/12 11:54
 * version:1.0.0
 */
@Service
@Transactional  //事务
public class DeptServiceImpl implements  DeptService {


    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> list() {
        return deptDao.list();
    }

    @Override
    public Result page(Map paramMap) {
        Object pageNo = paramMap.get("pageNo");//第几页
        Object pageSize = paramMap.get("pageSize");//每页显示数量
        if(pageNo==null || pageSize==null){
            throw new CustomException(ExceptionConstant.INVALID_ARGUMENT.getErrorCode(),
                    ExceptionConstant.INVALID_ARGUMENT.getErrorMessage());
        }
        int start = (Integer.valueOf(pageNo+"")-1)*Integer.valueOf(pageSize+"");
        int end = Integer.valueOf(pageNo+"")*Integer.valueOf(pageSize+"")+1;
        paramMap.put("start",start);
        paramMap.put("end",end);
        List<Dept> pageDept = deptDao.page(paramMap);
        int pageCount = deptDao.pageCount(paramMap);
        Map rmap  = new HashMap();
        rmap.put("pageDept",pageDept);
        rmap.put("total",pageCount);
        return new Result<Map>(ReturnStatus.SUCCESS.getReturnCode(),
                ReturnStatus.SUCCESS.getReturnMsg(),rmap);
    }

    @Override
    public Result getById(int deptNo) {
        return null;
    }

    @Override
    public Result add(Dept dept) {
        return null;
    }

    @Override
    public Result update(Dept dept) {
        return null;
    }

    @Override
    public Result delete(int deptNo) {
        return null;
    }
}
