package cn.cobight.sbm.service;

import cn.cobight.sbm.constants.ExceptionConstant;
import cn.cobight.sbm.constants.ReturnStatus;
import cn.cobight.sbm.dao.DeptDao;
import cn.cobight.sbm.entity.Dept;
import cn.cobight.sbm.util.CustomException;
import cn.cobight.sbm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        return new Result(ReturnStatus.SUCCESS.getReturnCode(),
                ReturnStatus.SUCCESS.getReturnMsg(),deptDao.getById(deptNo));
    }

    @Override
    public Result add(Dept dept) {
        return new Result(ReturnStatus.SUCCESS.getReturnCode(),
                ReturnStatus.SUCCESS.getReturnMsg(),deptDao.add(dept));
    }

    @Override
    public Result del(int deptNo) {
        return new Result(ReturnStatus.SUCCESS.getReturnCode(),
                ReturnStatus.SUCCESS.getReturnMsg(),deptDao.del(deptNo));
    }

    @Override
    public Result upd(Dept dept) {
        return new Result(ReturnStatus.SUCCESS.getReturnCode(),
                ReturnStatus.SUCCESS.getReturnMsg(),deptDao.upd(dept));
    }

}
