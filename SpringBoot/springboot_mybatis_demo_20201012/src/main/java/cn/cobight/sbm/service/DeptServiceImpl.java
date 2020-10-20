package cn.cobight.sbm.service;

import cn.cobight.sbm.dao.DeptDao;
import cn.cobight.sbm.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * fileName:DeptServiceImpl
 * description:
 * author:cobight
 * createTime:2020/10/12 16:59
 * version:1.0.0
 */
@Service
@Transactional  //事务
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> list() {
        return deptDao.list();
    }

    @Override
    public int add(Dept dept) {
        return deptDao.add(dept);
    }

    @Override
    public int del(int deptNo) {
        return deptDao.del(deptNo);
    }

    @Override
    public int upd(Dept dept) {
        return deptDao.upd(dept);
    }

}
