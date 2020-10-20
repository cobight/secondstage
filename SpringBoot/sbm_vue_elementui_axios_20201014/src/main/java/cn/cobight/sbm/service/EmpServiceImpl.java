package cn.cobight.sbm.service;

import cn.cobight.sbm.constants.ExceptionConstant;
import cn.cobight.sbm.constants.ReturnStatus;
import cn.cobight.sbm.dao.DeptDao;
import cn.cobight.sbm.dao.EmpDao;
import cn.cobight.sbm.entity.Dept;
import cn.cobight.sbm.entity.Emp;
import cn.cobight.sbm.util.CustomException;
import cn.cobight.sbm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fileName:EmpServiceImpl
 * description:
 * author:cobight
 * createTime:2020/10/15 20:53
 * version:1.0.0
 */
@Service
@Transactional  //事务
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpDao empDao;
    @Autowired
    private DeptDao deptDao;
    @Override
    public List<Emp> list() {

        return empDao.list();
    }

    @Override
    public Result page(Map paramMap) {
        Object pageNo = paramMap.get("pageNo");//第几页
        Object pageSize = paramMap.get("pageSize");//每页显示数量
        System.out.println(pageNo);
        System.out.println(pageSize);
        if(pageNo==null || pageSize==null){
            throw new CustomException(ExceptionConstant.INVALID_ARGUMENT.getErrorCode(),
                    ExceptionConstant.INVALID_ARGUMENT.getErrorMessage());
        }
        int start = (Integer.valueOf(pageNo+"")-1)*Integer.valueOf(pageSize+"");
        int end = Integer.valueOf(pageNo+"")*Integer.valueOf(pageSize+"")+1;
        paramMap.put("start",start);
        paramMap.put("end",end);
        List<Emp> pageEmp = empDao.page(paramMap);
        List<Dept> pageDept = deptDao.list();
        //System.out.println(pageEmp);
        int pageCount = empDao.pageCount(paramMap);
        Map rmap  = new HashMap();
        rmap.put("pageEmp",pageEmp);
        rmap.put("total",pageCount);
        rmap.put("pageDept",pageDept);
        return new Result<Map>(ReturnStatus.SUCCESS.getReturnCode(),
                ReturnStatus.SUCCESS.getReturnMsg(),rmap);
    }

    @Override
    public int pageCount(Map paramMap) {
        return 0;
    }

    @Override
    public Result getById(int empNo) {
        return new Result(ReturnStatus.SUCCESS.getReturnCode(),
                ReturnStatus.SUCCESS.getReturnMsg(),empDao.getById(empNo));
    }

    @Override
    public Result add(Emp emp) {
        System.out.println(emp);
        return new Result(ReturnStatus.SUCCESS.getReturnCode(),
                ReturnStatus.SUCCESS.getReturnMsg(),empDao.add(emp));
    }

    @Override
    public Result del(int empNo) {
        return new Result(ReturnStatus.SUCCESS.getReturnCode(),
                ReturnStatus.SUCCESS.getReturnMsg(),empDao.del(empNo));
    }

    @Override
    public Result upd(Emp emp) {
        return new Result(ReturnStatus.SUCCESS.getReturnCode(),
                ReturnStatus.SUCCESS.getReturnMsg(),empDao.upd(emp));
    }
}
