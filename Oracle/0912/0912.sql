-- 20200912 讲课内容
-- 实例1 根据函数的入参员工的职位job ，返回该职位的平均工薪 sal
--  distinct  去重 
select distinct job from emp;

select job,round(avg(sal+nvl(comm,0)),2) avgsal  
from emp group by job order by  avgsal desc;

select round(avg(sal+nvl(comm,0)),2) avgsal  
from emp where job='CLERK';
------方法
create or replace function fun_query_avgsal_byjob
(i_job in emp.job%type)
return varchar2  -- 指定返回值类型
is
   t_avgsal emp.sal%type; --定义平均工资变量
begin
   -- 查询赋值
   select round(avg(sal+nvl(comm,0)),2) into t_avgsal  
    from emp where job=i_job;
   
   -- 判断是否为空
   if t_avgsal is null then 
      return '输入职位'||i_job||'不存在';
   end if;
   
    return '职位为:'||i_job||'的平均工资为:'||t_avgsal;
    
  /* exception
     when no_data_found then 
       return '输入职位'||i_job||'不存在';
       */
end;
--  实例2 根据部门名称查询部门人数（带出参返回字符串信息，带返回值直接为部门人数）

select d.dname,count(e.empno) total from dept d left join emp e on d.deptno=e.deptno 
group by d.dname order by total desc;

create or replace function fun_query_deptnum_bydname
(i_dname in dept.dname%type,o_result out varchar2)
return number
is 
  t_deptno dept.deptno%type;
  t_deptnum  number(3);
begin
    -- 查询部门编号，并赋值
    select  deptno  into t_deptno from dept where dname=i_dname;
    -- 根据部门编号查询部门人数
    select count(*) into t_deptnum from emp where deptno=t_deptno;
    -- 给输入出参数赋值
    o_result := '部门名称为'||i_dname||'的人数为'||t_deptnum;
    -- 返回值
    return t_deptnum;
    
    exception 
      when no_data_found then
            o_result := '部门名称为'||i_dname||'的部门不存在';
            return 0;
end;

-- 匿名块测试
declare
  t_result varchar2(200);
  t_return number(3);
begin
    -- 定义返回值及输出参数，执行函数赋值
    t_return := fun_query_deptnum_bydname('ACCOUNTING',t_result);
    dbms_output.put_line(t_result||','||t_return);
  end;
 

-- 包
 -- 包的头部 (类似java中的接口)
  create or replace package pkg_permission
  is
      /*
        根据职位计算平均工资
        i_job  输入职位
        return  返回根据职位查询的平均工资结果
      */
      function fun_query_avgsal_byjob(i_job in emp.job%type) return varchar2;
      -- 循环批量添加 一般
      procedure pro_batch_add_deptbak (i_num in number,o_result out varchar2);
      --  循环批量添加 for
      procedure pro_batch_add_deptbak_for (i_num in number,o_result out varchar2);
  end pkg_permission;
  
  
  -- 包的身体
  create or replace package body pkg_permission
  is
      -- 根据职位计算平均工资
      function fun_query_avgsal_byjob
      (i_job in emp.job%type)
      return varchar2  -- 指定返回值类型
      is
         t_avgsal emp.sal%type; --定义平均工资变量
      begin
         -- 查询赋值
         select round(avg(sal+nvl(comm,0)),2) into t_avgsal
          from emp where job=i_job;

         -- 判断是否为空
         if t_avgsal is null then
            return '输入职位'||i_job||'不存在';
         end if;

          return '职位为:'||i_job||'的平均工资为:'||t_avgsal;

        /* exception
           when no_data_found then
             return '输入职位'||i_job||'不存在';
             */
      end;
      -- 循环批量添加 一般
      procedure pro_batch_add_deptbak
      (i_num in number,o_result out varchar2)
      as
         t_i number(10):=1; --定义循环变量，默认开始值为1
      begin
          if  i_num<1 or i_num>10000 then
               o_result:='输入参数错误';
               return; --中断程序
          end if;
          -- 删除数据
          -- truncate table dept;
          delete from dept_bak;
         -- 循环向部门备份表插入数据
          loop
            -- 判断退出
            if t_i>i_num then
                exit; -- 退出
            end if;
            -- exit when t_i>i_num;

            -- 插入数据
            insert into dept_bak values(t_i,'devteam'||t_i,t_i||'floor');
            -- 变量自增
            t_i:=t_i+1;
          end loop;
          commit; -- 提交事务
          o_result := '插入成功了'||(t_i-1)||'条';
      end;
      -- 循环批量添加 for
      procedure pro_batch_add_deptbak_for
      (i_num in number,o_result out varchar2)
      as
      begin
          if  i_num<1 or i_num>10000 then
               o_result:='输入参数错误';
               return; --中断程序
          end if;
          -- 删除数据
          -- truncate table dept;
          delete from dept_bak;
         -- 循环向部门备份表插入数据  满足条件进入循环
          for t_i in 1..i_num loop
            -- 插入数据
            insert into dept_bak values(t_i,'devteam'||t_i,t_i||'floor');

          end loop;
          commit; -- 提交事务
          o_result := '插入成功了'||i_num||'条';
      end;
  end pkg_permission;
  
  
-- 测试
 -- 方法1  点开包的头部，打开functions或者procedures 看到包中的存储过程函数右键 测试
 -- 方法2 
  declare
    t_rn  varchar2(200);
  begin
     t_rn := pkg_permission.fun_query_avgsal_byjob('SALESMAN');
     dbms_output.put_line(t_rn);
    end;
    
    
 -- 游标
 -- 隐含游标  sql 示例  
truncate table dept_bak;
  
declare
    t_dname dept.dname%type;
begin
    --  insert into dept_bak values(1,'devteam1','1floor');
    --   delete  from dept_bak where deptno=1;
    
    select dname into t_dname from dept_bak where deptno=1;
      -- 打印游标执行行数
       dbms_output.put_line(sql%rowcount);
      -- 判断是否执行成功
      if  sql%found then 
        dbms_output.put_line('成功执行');
      end if;
      
      
 end;
 
 
 -- 游标
--  显式游标  示例1
-- 使用步骤： 定义游标， 打开游标，提取数据，关闭游标  
  -- 使用游标提取部门表数据，并且打印结果
  
declare
    -- 定义游标   游标是一个select语句的结果集
    cursor csr_dept is select * from dept;
    -- 定义dept行变量
     t_dept_row dept%rowtype;
begin
   -- 打开游标
   open csr_dept;
   -- 提取数据
   loop
      -- 判断是否有下一行值
      if csr_dept%notfound then
          exit; --退出循环 
      end if;
      -- 游标是一个指针的体现，
      fetch  csr_dept into t_dept_row;  
      -- 打印
      dbms_output.put_line('行号'||csr_dept%rowcount||'部门名称：'||t_dept_row.dname||',位置：'
      ||t_dept_row.loc);
   end loop;
   -- 关闭游标
   close csr_dept;
end;
  
-- 游标
--  显式游标  示例2
--  bulk collect用法  一次性取出游标数据  游标和table结合使用 
  -- 使用获取员工数据，并打印相关列
  
declare
  -- 定义游标
  cursor csr_emp is select * from emp;
  -- 定义table 类型
  type table_emp is table of emp%rowtype index by binary_integer; 
  -- 定义变量，使用上面定义类型
  t_table_emp table_emp;
begin
  -- 打开游标
  open csr_emp;
  -- 提取数据（bulk collect 一次性提取）
  fetch csr_emp bulk collect into t_table_emp;
  -- 关闭游标
  close csr_emp;
  
  -- 遍历table变量数据  t_table_emp.first 第一条数据的下标  
  -- t_table_emp.last  最后一条数据下标
  for t_i in t_table_emp.first..t_table_emp.last loop
     dbms_output.put_line('员工姓名'||t_table_emp(t_i).ename||
     ',雇佣日期'||to_char(t_table_emp(t_i).hiredate,'yyyy-mm-dd'));
  end loop;
    
end;  

-- 游标
--  显式游标  示例3  参数游标的使用
  -- 根据部门编号查询员工,并打印
  
declare
  -- 定义游标  variable 
  cursor csr_emp(v_deptno number,v_ename emp.ename%type) is select * from emp
   where deptno=v_deptno and ename like '%'||v_ename||'%';
  -- 定义table 类型
  type table_emp is table of emp%rowtype index by binary_integer; 
  -- 定义变量，使用上面定义类型
  t_table_emp table_emp;
begin
  -- 打开游标
  open csr_emp(20,'S');
  -- 提取数据（bulk collect 一次性提取）
  fetch csr_emp bulk collect into t_table_emp;
  -- 关闭游标
  close csr_emp;
  
  -- 遍历table变量数据  t_table_emp.first 第一条数据的下标  
  -- t_table_emp.last  最后一条数据下标
  for t_i in t_table_emp.first..t_table_emp.last loop
     dbms_output.put_line('员工姓名'||t_table_emp(t_i).ename||
     ',雇佣日期'||to_char(t_table_emp(t_i).hiredate,'yyyy-mm-dd'));
  end loop;
    
end;  

 
  
  
 
