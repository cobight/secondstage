--  ������
-- drop table if exits tb_emp;
create table tb_emp as select * from emp;
-- �������ſյı��ݱ�
create table tb_emp_backup1 as select empno,ename,hiredate from emp where 1=2;
create table tb_emp_backup2 as select empno,ename,hiredate from emp where 1=2;
-- ʹ�ô�����tgr_test_0915���ô洢����(pro_backup_emp_test0915)��������¹��ܣ�
-- ��ɾ��tb_emp������ʱ���򱸷ݱ�tb_emp_backup1��tb_emp_backup2�������ݣ�
-- ����Ӷ����Ϊż����--  ������tb_emp_backup1������Ӷ����Ϊ�����£�����tb_emp_backup2
-- ��ʾ    
    -- select mod(to_char(sysdate,'mm'),2) from dual;    ��2ȡ��
    -- select mod(to_char(sysdate+30,'mm'),2) from dual;
    
    /*  if mod(to_char(i_hiredate,'mm'),2)=0 then
          
       else
         
       end if;   */
create or replace procedure pro_backup_emp_test0915
(i_empno in emp.empno%type,i_ename in emp.ename%type,i_hiredate in emp.hiredate%type)
is
begin
        -- ʡ���˴򿪺͹رչ���
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
