--  创建表
-- drop table if exits tb_emp;
create table tb_emp as select * from emp;
-- 创建两张空的备份表
create table tb_emp_backup1 as select empno,ename,hiredate from emp where 1=2;
create table tb_emp_backup2 as select empno,ename,hiredate from emp where 1=2;
-- 使用触发器tgr_test_0915调用存储过程(pro_backup_emp_test0915)，完成如下功能：
-- 当删除tb_emp表数据时，向备份表tb_emp_backup1和tb_emp_backup2插入数据，
-- 当雇佣日期为偶数月--  ，插入tb_emp_backup1，当雇佣日期为奇数月，插入tb_emp_backup2
-- 提示    
    -- select mod(to_char(sysdate,'mm'),2) from dual;    对2取余
    -- select mod(to_char(sysdate+30,'mm'),2) from dual;
    
    /*  if mod(to_char(i_hiredate,'mm'),2)=0 then
          
       else
         
       end if;   */
create or replace procedure pro_backup_emp_test0915
(i_empno in emp.empno%type,i_ename in emp.ename%type,i_hiredate in emp.hiredate%type)
is
begin
        -- 省略了打开和关闭过程
      if mod(to_char(i_hiredate,'mm'),2)=0 then
         insert into tb_emp_backup1 values(i_empno,i_ename,i_hiredate);
       else
         insert into tb_emp_backup2 values(i_empno,i_ename,i_hiredate);
       end if;
end;  

delete from tb_emp where empno=7369;
select * from tb_emp_backup1;
select * from tb_emp_backup2;


create or replace trigger tgr_test_0915 after delete on tb_emp for each row
begin
  --dbms_output.put_line("-----");
  pro_backup_emp_test0915(:old.empno,:old.ename,:old.hiredate);
end;
