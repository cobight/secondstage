package com.aaa.sbm.service;

import com.aaa.sbm.dao.DeptDao;
import com.aaa.sbm.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
