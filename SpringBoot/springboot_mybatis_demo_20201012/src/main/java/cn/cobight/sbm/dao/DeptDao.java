package cn.cobight.sbm.dao;

import cn.cobight.sbm.entity.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * fileName:DeptDao
 * description:
 * author:cobight
 * createTime:2020/10/12 16:56
 * version:1.0.0
 */

public interface DeptDao {
    @Select("select deptno,dname,loc from dept") //建议使用mapper
    List<Dept> list();

    @Insert("insert into dept(deptno,dname,loc) values(seq_dept_id.nextval,#{dname},#{loc})")
    int add(Dept dept);

    @Delete("delete from dept where deptno=#{deptNo}")
    int del(int deptNo);

    @Update("update dept set dname=#{dname},loc=#{loc} where deptNo=#{deptNo}")
    int upd(Dept dept);

}
