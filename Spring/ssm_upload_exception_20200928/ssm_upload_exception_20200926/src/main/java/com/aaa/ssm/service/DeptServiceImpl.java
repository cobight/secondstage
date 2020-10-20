package com.aaa.ssm.service;

import com.aaa.ssm.dao.DeptDao;
import com.aaa.ssm.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * fileName:DeptServiceImpl
 * description:
 * author:zz
 * createTime:2020/9/25 11:10
 * version:1.0.0
 */
@Service
@Transactional  //注解式事务配置
public class DeptServiceImpl implements DeptService {

    @Autowired  //byType 依赖注入
    private DeptDao deptDao;

    @Override
    public List<Dept> list() {
        int[] intArr = {1};
        System.out.println(intArr[2]);
        return deptDao.list();
    }

    @Override
    public Dept getById(int deptNo) {
        return deptDao.getById(deptNo);
    }

    @Override
    public int update(Dept dept) {
        return deptDao.update(dept);
    }
}
