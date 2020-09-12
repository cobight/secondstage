-- ������tb_client  
create table tb_client(
   client_no  char(10) primary key,
   client_name  varchar2(30),
   birthday  date
);
create   sequence  seq_pro_test_add_client_0912;
-- ��д�洢����pro_test_add_client_0912 �� tb_client��������,���سɹ�����������Ҫ��һ��������50����ÿ�β��벻���ԭ������ 
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
  o_result:='����'||i_num||'������';
end;

--     client_no���ݸ�ʽΪ 'client0001','client0002','client0003'......
--     client_name ����Ϊ'test1','test2','test3'......
--     birthday ��1������Ϊ���죬��2������ǰ�죬��3������Ϊ��ǰ��......
  -- ��ʾ1��ʹ�����к�lpad
  -- ��ʾ2������ select sysdate-1 from dual; 
  --        ǰ�� select sysdate-2 from dual;
  --      ��ǰ�� select sysdate-3 from dual;
  --        ....
select sysdate-1 from dual;
select * from tb_client;
--����
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
