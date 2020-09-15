-- 20200912 ��������
-- ʵ��1 ���ݺ��������Ա����ְλjob �����ظ�ְλ��ƽ����н sal
--  distinct  ȥ�� 
select distinct job from emp;

select job,round(avg(sal+nvl(comm,0)),2) avgsal  
from emp group by job order by  avgsal desc;

select round(avg(sal+nvl(comm,0)),2) avgsal  
from emp where job='CLERK';
------����
create or replace function fun_query_avgsal_byjob
(i_job in emp.job%type)
return varchar2  -- ָ������ֵ����
is
   t_avgsal emp.sal%type; --����ƽ�����ʱ���
begin
   -- ��ѯ��ֵ
   select round(avg(sal+nvl(comm,0)),2) into t_avgsal  
    from emp where job=i_job;
   
   -- �ж��Ƿ�Ϊ��
   if t_avgsal is null then 
      return '����ְλ'||i_job||'������';
   end if;
   
    return 'ְλΪ:'||i_job||'��ƽ������Ϊ:'||t_avgsal;
    
  /* exception
     when no_data_found then 
       return '����ְλ'||i_job||'������';
       */
end;
--  ʵ��2 ���ݲ������Ʋ�ѯ���������������η����ַ�����Ϣ��������ֱֵ��Ϊ����������

select d.dname,count(e.empno) total from dept d left join emp e on d.deptno=e.deptno 
group by d.dname order by total desc;

create or replace function fun_query_deptnum_bydname
(i_dname in dept.dname%type,o_result out varchar2)
return number
is 
  t_deptno dept.deptno%type;
  t_deptnum  number(3);
begin
    -- ��ѯ���ű�ţ�����ֵ
    select  deptno  into t_deptno from dept where dname=i_dname;
    -- ���ݲ��ű�Ų�ѯ��������
    select count(*) into t_deptnum from emp where deptno=t_deptno;
    -- �������������ֵ
    o_result := '��������Ϊ'||i_dname||'������Ϊ'||t_deptnum;
    -- ����ֵ
    return t_deptnum;
    
    exception 
      when no_data_found then
            o_result := '��������Ϊ'||i_dname||'�Ĳ��Ų�����';
            return 0;
end;

-- ���������
declare
  t_result varchar2(200);
  t_return number(3);
begin
    -- ���巵��ֵ�����������ִ�к�����ֵ
    t_return := fun_query_deptnum_bydname('ACCOUNTING',t_result);
    dbms_output.put_line(t_result||','||t_return);
  end;
 

-- ��
 -- ����ͷ�� (����java�еĽӿ�)
  create or replace package pkg_permission
  is
      /*
        ����ְλ����ƽ������
        i_job  ����ְλ
        return  ���ظ���ְλ��ѯ��ƽ�����ʽ��
      */
      function fun_query_avgsal_byjob(i_job in emp.job%type) return varchar2;
      -- ѭ��������� һ��
      procedure pro_batch_add_deptbak (i_num in number,o_result out varchar2);
      --  ѭ��������� for
      procedure pro_batch_add_deptbak_for (i_num in number,o_result out varchar2);
  end pkg_permission;
  
  
  -- ��������
  create or replace package body pkg_permission
  is
      -- ����ְλ����ƽ������
      function fun_query_avgsal_byjob
      (i_job in emp.job%type)
      return varchar2  -- ָ������ֵ����
      is
         t_avgsal emp.sal%type; --����ƽ�����ʱ���
      begin
         -- ��ѯ��ֵ
         select round(avg(sal+nvl(comm,0)),2) into t_avgsal
          from emp where job=i_job;

         -- �ж��Ƿ�Ϊ��
         if t_avgsal is null then
            return '����ְλ'||i_job||'������';
         end if;

          return 'ְλΪ:'||i_job||'��ƽ������Ϊ:'||t_avgsal;

        /* exception
           when no_data_found then
             return '����ְλ'||i_job||'������';
             */
      end;
      -- ѭ��������� һ��
      procedure pro_batch_add_deptbak
      (i_num in number,o_result out varchar2)
      as
         t_i number(10):=1; --����ѭ��������Ĭ�Ͽ�ʼֵΪ1
      begin
          if  i_num<1 or i_num>10000 then
               o_result:='�����������';
               return; --�жϳ���
          end if;
          -- ɾ������
          -- truncate table dept;
          delete from dept_bak;
         -- ѭ�����ű��ݱ��������
          loop
            -- �ж��˳�
            if t_i>i_num then
                exit; -- �˳�
            end if;
            -- exit when t_i>i_num;

            -- ��������
            insert into dept_bak values(t_i,'devteam'||t_i,t_i||'floor');
            -- ��������
            t_i:=t_i+1;
          end loop;
          commit; -- �ύ����
          o_result := '����ɹ���'||(t_i-1)||'��';
      end;
      -- ѭ��������� for
      procedure pro_batch_add_deptbak_for
      (i_num in number,o_result out varchar2)
      as
      begin
          if  i_num<1 or i_num>10000 then
               o_result:='�����������';
               return; --�жϳ���
          end if;
          -- ɾ������
          -- truncate table dept;
          delete from dept_bak;
         -- ѭ�����ű��ݱ��������  ������������ѭ��
          for t_i in 1..i_num loop
            -- ��������
            insert into dept_bak values(t_i,'devteam'||t_i,t_i||'floor');

          end loop;
          commit; -- �ύ����
          o_result := '����ɹ���'||i_num||'��';
      end;
  end pkg_permission;
  
  
-- ����
 -- ����1  �㿪����ͷ������functions����procedures �������еĴ洢���̺����Ҽ� ����
 -- ����2 
  declare
    t_rn  varchar2(200);
  begin
     t_rn := pkg_permission.fun_query_avgsal_byjob('SALESMAN');
     dbms_output.put_line(t_rn);
    end;
    
    
 -- �α�
 -- �����α�  sql ʾ��  
truncate table dept_bak;
  
declare
    t_dname dept.dname%type;
begin
    --  insert into dept_bak values(1,'devteam1','1floor');
    --   delete  from dept_bak where deptno=1;
    
    select dname into t_dname from dept_bak where deptno=1;
      -- ��ӡ�α�ִ������
       dbms_output.put_line(sql%rowcount);
      -- �ж��Ƿ�ִ�гɹ�
      if  sql%found then 
        dbms_output.put_line('�ɹ�ִ��');
      end if;
      
      
 end;
 
 
 -- �α�
--  ��ʽ�α�  ʾ��1
-- ʹ�ò��裺 �����α꣬ ���α꣬��ȡ���ݣ��ر��α�  
  -- ʹ���α���ȡ���ű����ݣ����Ҵ�ӡ���
  
declare
    -- �����α�   �α���һ��select���Ľ����
    cursor csr_dept is select * from dept;
    -- ����dept�б���
     t_dept_row dept%rowtype;
begin
   -- ���α�
   open csr_dept;
   -- ��ȡ����
   loop
      -- �ж��Ƿ�����һ��ֵ
      if csr_dept%notfound then
          exit; --�˳�ѭ�� 
      end if;
      -- �α���һ��ָ������֣�
      fetch  csr_dept into t_dept_row;  
      -- ��ӡ
      dbms_output.put_line('�к�'||csr_dept%rowcount||'�������ƣ�'||t_dept_row.dname||',λ�ã�'
      ||t_dept_row.loc);
   end loop;
   -- �ر��α�
   close csr_dept;
end;
  
-- �α�
--  ��ʽ�α�  ʾ��2
--  bulk collect�÷�  һ����ȡ���α�����  �α��table���ʹ�� 
  -- ʹ�û�ȡԱ�����ݣ�����ӡ�����
  
declare
  -- �����α�
  cursor csr_emp is select * from emp;
  -- ����table ����
  type table_emp is table of emp%rowtype index by binary_integer; 
  -- ���������ʹ�����涨������
  t_table_emp table_emp;
begin
  -- ���α�
  open csr_emp;
  -- ��ȡ���ݣ�bulk collect һ������ȡ��
  fetch csr_emp bulk collect into t_table_emp;
  -- �ر��α�
  close csr_emp;
  
  -- ����table��������  t_table_emp.first ��һ�����ݵ��±�  
  -- t_table_emp.last  ���һ�������±�
  for t_i in t_table_emp.first..t_table_emp.last loop
     dbms_output.put_line('Ա������'||t_table_emp(t_i).ename||
     ',��Ӷ����'||to_char(t_table_emp(t_i).hiredate,'yyyy-mm-dd'));
  end loop;
    
end;  

-- �α�
--  ��ʽ�α�  ʾ��3  �����α��ʹ��
  -- ���ݲ��ű�Ų�ѯԱ��,����ӡ
  
declare
  -- �����α�  variable 
  cursor csr_emp(v_deptno number,v_ename emp.ename%type) is select * from emp
   where deptno=v_deptno and ename like '%'||v_ename||'%';
  -- ����table ����
  type table_emp is table of emp%rowtype index by binary_integer; 
  -- ���������ʹ�����涨������
  t_table_emp table_emp;
begin
  -- ���α�
  open csr_emp(20,'S');
  -- ��ȡ���ݣ�bulk collect һ������ȡ��
  fetch csr_emp bulk collect into t_table_emp;
  -- �ر��α�
  close csr_emp;
  
  -- ����table��������  t_table_emp.first ��һ�����ݵ��±�  
  -- t_table_emp.last  ���һ�������±�
  for t_i in t_table_emp.first..t_table_emp.last loop
     dbms_output.put_line('Ա������'||t_table_emp(t_i).ename||
     ',��Ӷ����'||to_char(t_table_emp(t_i).hiredate,'yyyy-mm-dd'));
  end loop;
    
end;  

 
  
  
 
