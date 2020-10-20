package cn.cobight.ssm.service;

import cn.cobight.ssm.dao.DeptDao;
import cn.cobight.ssm.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * fileName:DeptServiceImpl
 * description:
 * author:cobight
 * createTime:2020/9/25 11:10
 * version:1.0.0
 */
@Service
@Transactional  //注解式 事务配置
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> list() {
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

    @Override
    public int insert(Dept dept) {
        return deptDao.insert(dept);
    }

    @Override
    public int delete(int deptNo) {
        return deptDao.delete(deptNo);
    }
}
