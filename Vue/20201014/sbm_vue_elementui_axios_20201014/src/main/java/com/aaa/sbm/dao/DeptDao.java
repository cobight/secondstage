package com.aaa.sbm.dao;

import com.aaa.sbm.entity.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * fileName:DeptDao
 * description:
 * author:zz
 * createTime:2020/10/12 11:52
 * version:1.0.0
 */
public interface DeptDao {

    /**
     * 部门列表
     * @return
     */
    @Select("select deptno,dname,loc from dept")  //建议使用mapper
    List<Dept>  list();

    /**
     * 部门分页列表
     * @return
     */
    @Select("<script> select * from (select rownum rn,deptno,dname,loc from dept <where> " +
            "<if test=\"dname!=null and dname!=''\"> and dname like '%'||#{dname}||'%'</if>"+
            "<if test=\"loc!=null and loc!=''\"> and loc like '%'||#{loc}||'%'</if>"+
            "</where>) a where rn &gt; #{start} and rn &lt; #{end}</script>")  //建议使用mapper
    List<Dept>  page(Map paramMap);

    /**
     * 分页数据总数量
     * @param paramMap
     * @return
     */
    @Select("<script>select count(*) from dept <where>" +
            "<if test=\"dname!=null and dname!=''\"> and dname like '%'||#{dname}||'%'</if>"+
            "<if test=\"loc!=null and loc!=''\"> and loc like '%'||#{loc}||'%'</if>"+
            "</where></script>")
    int  pageCount(Map paramMap);

    /**
     * 根据编号查询
     * @param deptNo
     * @return
     */
    @Select("select deptno,dname,loc from dept where deptno=#{deptNo}")
    Dept  getById(int deptNo);

    /**
     * 添加
     * @param dept
     * @return
     */
    @Insert("insert into dept values(seq_dept_id.nextval,#{dname},#{loc})")
    int add(Dept dept);

    /**
     * 更新
     * @param dept
     * @return
     */
    @Update("update dept set dname=#{dname},loc=#{loc} where deptno=#{deptNo}")
    int update(Dept dept);

    /**
     * 根据编号删除
     * @param deptNo
     * @return
     */
    @Delete("delete from dept where deptno=#{deptNo}")
    int delete(int deptNo);
}
