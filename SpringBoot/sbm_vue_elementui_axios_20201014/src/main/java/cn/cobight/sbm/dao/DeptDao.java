package cn.cobight.sbm.dao;

import cn.cobight.sbm.entity.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

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

    @Select("<script> select * from (select rownum rn,deptno,dname,loc from dept <where> " +
            "<if test=\"dname!=null and dname!=''\"> and dname like '%'||#{dname}||'%'</if>"+
            "<if test=\"loc!=null and loc!=''\"> and loc like '%'||#{loc}||'%'</if>"+
            "</where>) a where rn &gt; #{start} and rn &lt; #{end}</script>")  //建议使用mapper
    List<Dept>  page(Map paramMap);

    @Select("<script>select count(*) from dept <where>" +
            "<if test=\"dname!=null and dname!=''\"> and dname like '%'||#{dname}||'%'</if>"+
            "<if test=\"loc!=null and loc!=''\"> and loc like '%'||#{loc}||'%'</if>"+
            "</where></script>")
    int  pageCount(Map paramMap);

    @Select("select deptno,dname,loc from dept where deptno=#{deptNo}")
    Dept  getById(int deptNo);

    @Insert("insert into dept(deptno,dname,loc) values(seq_dept_id.nextval,#{dname},#{loc})")
    int add(Dept dept);

    @Delete("delete from dept where deptno=#{deptNo}")
    int del(int deptNo);

    @Update("update dept set dname=#{dname},loc=#{loc} where deptNo=#{deptNo}")
    int upd(Dept dept);

}
