-- 创建表tb_client  
create table tb_client(
   client_no  char(10) primary key,
   client_name  varchar2(30),
   birthday  date
);
create   sequence  seq_pro_test_add_client_0912;
-- 编写存储过程pro_test_add_client_0912 向 tb_client插入数据,返回成功插入条数，要求一次最多插入50条，每次插入不清除原有数据 
create or replace procedure pro_test_add_client_0912
(i_num in number,o_result out varchar2)
as
   --i_num number(10):=50;
   t_i number(10); 
   t_s number(10);
   leftc char(4);
begin
  if i_num<0 or i_num>50 then
    return;
  end if;  
  for t_i in 1..i_num loop
    t_s:=seq_pro_test_add_client_0912.nextval;
    select lpad(t_s,4,0) into leftc from dual;
    insert into tb_client values('client'||leftc,'test'||t_s,sysdate-t_s);
  end loop;
  commit; 
  o_result:='共：'||i_num||'条数据';
end;

--     client_no数据格式为 'client0001','client0002','client0003'......
--     client_name 名字为'test1','test2','test3'......
--     birthday 第1条数据为昨天，第2条数据前天，第3条数据为大前天......
  -- 提示1：使用序列和lpad
  -- 提示2：昨天 select sysdate-1 from dual; 
  --        前天 select sysdate-2 from dual;
  --      大前天 select sysdate-3 from dual;
  --        ....
select sysdate-1 from dual;
select * from tb_client;
--测试
declare
   t_result varchar2(500);
begin
    pro_test_add_client_0912(10,t_result);
    dbms_output.put_line(t_result);
end;
--------------------------------------
declare
   i_num number(10):=50;
   t_i number(10); 
   t_s number(10);
   leftc char(4);
begin
  for t_i in 1..i_num loop
    t_s:=seq_pro_test_add_client_0912.nextval;
    select lpad(t_s,4,0) into leftc from dual;
    insert into tb_client values('client'||leftc,'test'||t_s,sysdate-t_s);
  end loop;
  commit; 
end;
select * from tb_client;
