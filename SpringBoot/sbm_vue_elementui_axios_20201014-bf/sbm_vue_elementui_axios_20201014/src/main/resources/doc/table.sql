-- 权限表
create table tb_power(
              power_id  int   primary key,
              power_name varchar2(50) ,
              parent_id int,
              icon  varchar2(50),
              menu_url varchar2(200) ,
              add_time date
        );
comment on column tb_power.power_name
  is '权限名称';
comment on column tb_power.parent_id
  is '父节点ID,如果是一级目录父节点为0';
comment on column tb_power.icon
  is '该节点的图标';
comment on column tb_power.menu_url
  is '该节点的地址';
comment on column tb_power.add_time
  is '添加时间';
 --角色表
 select *from tb_role;
 -- 角色权限关联表
  create table tb_role_power(
            role_id  int,
            power_id  int,
            constraint  fk_role_id_tbrole_roleid   foreign key(role_id) references tb_role(role_id),
     constraint  fk_power_id_tbpower_id  foreign key(power_id) references tb_power(power_id)
 );


 INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (1, '信息管理', 0, null, null, sysdate);
INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (2, '订单管理', 0, null, null, sysdate);
INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (3, '商品管理', 0, null, null, sysdate);
INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (4, '仓库管理', 0, null, null, sysdate);
INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (5, '采购管理', 0, null, null, sysdate);


 INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (6, '用户管理', 1, null, null, sysdate);
INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (7, '职位管理', 1, null, 'www.baidu.com', sysdate);
INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (8, '部门管理', 1, null, 'www.baidu.com', sysdate);
INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (9, '角色管理', 1, null, 'www.baidu.com', sysdate);
INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (10, '权限管理', 1, null, 'www.baidu.com', sysdate);
INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (11, '用户查询', 6, null, 'www.baidu.com', sysdate);
INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (12, '用户添加', 6, null, 'www.baidu.com', sysdate);
-- 订单管理
INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (13, '订单查询', 2, null, 'www.baidu.com', sysdate);
INSERT INTO tb_power (power_id, power_name, parent_id, icon, menu_url, add_time)
 VALUES (14, '订单统计', 2, null, 'www.baidu.com', sysdate);