# Oracle数据库

## 五大服务

vss(volume shadow cppy service) writr service
基础设备（磁盘，阵列等）创建高保真的时间点影像的一个服务

dbconsoleorcl(sid)
使用em ( enterprise manager )进行后台管理,必须打开该服务

job scheduler orcl
服务作业调度的一个服务

TNSListener（使用oralce时，必须启动的）1521 mysql:3306  sqlserver:1433
监听服务，当数据库实例需要被访问时，该服务就必须开启

oracleServiceOrcl
当前实例的核心服务，必须启动，才能使用当前数据库实例

## 主要用户

### sys用户

相当于mysql中root用户，是超级用户，该用户维护系统信息和数据库字典的所有表和视图。数据备份，恢复，上层更改数据操作，一般使用该用户。

### system用户

默认管理员用户，拥有DBA ( database admin ）权限,管理和使用一些内部表和视图。管理其他用户功能（第2章学习用户创建，修改，删除和赋权等等，就使用该用户）。

### scott用户

一个示范用户，有4张示例表（重点两张表dept ,emp )

## plsql简介

* pl/sql(procedural language/structure query language)是一个集成开发环境

* (IDE Integrated Development Environment ) ,专门为oracle数据库设计的应用软件，对普通的sql做了增强的作用，让我们对oracle操作变得更简单。

## 数据类型

### 字符类型

**char (character)**	固定长度，最大长度2000字节。加入声明一个列的长度为phonenum char(11)电话号码11位，填写电话号码时，填的值为110剩余的8位使用空格补全，不会让其他字段再使用，所以叫固定长度。

**varchar2(var = variable可变的)	**可变长度，最大长度4000字节。数据库表设计时，最常使用，加入设计一个描述列的长度为200	describe varchar2(200)使用时只放入一个单词，good占用了4个字节，其余的196字节可以被其他的字段使用，不会被浪费

### 数值类型

**number(长度）**整型长度可以根据实际需求设置。

**number(m,n）**浮点型（小数）m代表当前小数的总长度，n表示小数点后面精确的位数。

举例来说salary number(8,2)工资的总长度是8为最大值:999999.99

### 日期类型

**date**使用时，不用加长度，默认长度为7个字节，世纪，年，月，日，时，分，秒取值范围从公元前4000多年到9999，一般业务需求，使用该类型完全可以。
**timestamp**使用时，可以精确到秒后六位。例如，做奥运会比赛项目存放短跑开始和结束时间的数据库需求设计。12.88

### 大对象类型

**clob**	当数据库要存储超过4000长度的数据时，无法使用varchar2类型，就使用clob,最大的
容量4G。如存放新闻内容。。 。 。
**blob**	二进度表示的大字段，一般用来存储图片，音频和视频等二进制文件。。。最大长度
也是4G

# 注释

单行

```sql
-- 注释内容
```

多行

```sql
/*
   注释1
   注释2
   ....
*/
```

# DDL

data definition language 数据定义语言，主要只对数据库对象的创建(create),修改（ alter ) ,删除(drop )的操作。

## create

```sql
-- create 
-- 创建新闻分类表
  create  table tb_news_type(
      type_id number(6) primary key,  --  不可以使用自增
      tname  varchar2(500) not null,
      addtime  date,
      describe  varchar(1000) 
  );
-- 创建新闻表(新闻表外键关联新闻分类表)
  create table tb_news(
      news_id number(11) primary key,
      title  varchar2(200),
      content clob,
      addtime  date,
      type_id  number(6),
      constraint fk_type_id_tbnewstype_id  foreign key(type_id) references  tb_news_type(type_id)
  );
```

## alter

```sql
-- alter  
   alter table tb_news add clicknum number(11);  -- 添加列
   alter table tb_news modify title  varchar2(500); --更新列  列改变类型或者长度变小保证该字段 为空
   alter table tb_news modify title  date; 
   alter table tb_news modify title  varchar2(500);
   alter table tb_news drop column  clicknum;
```

## drop

```sql
-- drop
   drop table tb_news_type;
```



# DML

data manipulation language数据操纵语言，主要是指insert update delete等操作

```sql
-- insert  操作
-- 获取当前时间oracle:  sysdate     				mysql:  now()
  select * from tb_news_type;
  insert into tb_news_type  values(1,'国际新闻',sysdate,'疫情很严重');
-- value
  insert into tb_news_type  value(2,'国际新闻1',sysdate,'疫情很严重1'); -- oracle 不可以 mysql可以
-- 时间日期
  insert into tb_news_type  values(3,'国际新闻2','2020-09-09 09:09:09','疫情很严重2'); -- oracle 不可以 mysql可以
-- 批量添加
  insert into tb_news_type values(1,'国际新闻',sysdate,'疫情很严重'), (2,'国际新闻2',sysdate,'疫情很严重2');-- oracle 不可以 mysql可以
```

## dual 虚表

从虚表读取固定数据

```sql
select 1,'国际新闻',sysdate,'疫情很严重' from dual
```

物理上不存在表，为了oracle的语法完整，虚构出一个表dual 

甲骨文被sun收购后也支持虚表了

## union  union all   合并结果集 

```sql
  
  select 1,'国际新闻',sysdate,'疫情很严重' ; -- 没有from的语句mysql 可以，但是Oracle不可以
  --  union 过滤重复合并结果集，结果集中没重复数据
  select 1,'国际新闻',sysdate,'疫情很严重' from dual union
  select 3,'国际新闻3',sysdate,'疫情很严重3' from dual union
  select 3,'国际新闻3',sysdate,'疫情很严重3' from dual union
  select 2,'国际新闻2',sysdate,'疫情很严重2' from dual ;
  --  union all 不过滤重复合并结果集，结果集中有重复数据
  select 1,'国际新闻',sysdate,'疫情很严重' from dual union all
  select 3,'国际新闻3',sysdate,'疫情很严重3' from dual union all
  select 3,'国际新闻3',sysdate,'疫情很严重3' from dual union all
  select 2,'国际新闻2',sysdate,'疫情很严重2' from dual ;
  
  -- 批量添加
  insert into tb_news_type
  select 3,'国际新闻3',sysdate,'疫情很严重3' from dual union
  select 3,'国际新闻3',sysdate,'疫情很严重3' from dual union
  select 2,'国际新闻2',sysdate,'疫情很严重2' from dual ;
```

## 更新和删除 和mysql完全一样

## 序列

oracle 主键或者唯一键能不能像mysql使用auto_increment关键字自增，
借助于序列对象sequence 来实现自增功能

```sql
-- 序列的创建
   create  sequence  seq_news_type_id  --指定序列名称
   start with 1  --  设置开始值
   increment by  1 --   设置增量
   minvalue  1 -- 开始值不能小于最小值
   maxvalue  9999  -- 最大值默认为27 个9   
--  最简单序列创建
   create  sequence  seq_news_id; 
   
   select length('999999999999999999999999999') from dual;

```

当前值  取当前值时，必须先取一次下一个值

```sql
-- 序列的使用
   --   currval  =current value   当前值  取当前值时，必须先取一次下一个值
   select seq_news_type_id.currval from dual; 
   --nextval = next  value  下一个值
   select seq_news_type_id.nextval  from dual;
-- 序列的实际使用
 insert into tb_news_type  values(seq_news_type_id.nextval,'国际新闻',sysdate,'疫情很严重');
 
 
```

**序列在union中不可用**

```sql
select * from tb_news_type;
  -- seq_news_type_id.nextval不可以在union中使用
 insert into tb_news_type  values
  select seq_news_type_id.nextval,'国际新闻',sysdate,'疫情很严重' from dual union 
   select seq_news_type_id.nextval,'国际新闻',sysdate,'疫情很严重' from dual ;
```

分页

```sql
--  oracle 分页  mysql limit
--  伪列    表的物理结构不存在，但是为了某些特性功能（例如分页或者删除重复数据等）,可以使用的一些列
--  rowid  rownum  

 select * from   tb_news_type  limit 0,2;  -- oracle不可以使用limit
 
 select * from   tb_news_type;  --物理上不存在rowid rownum
 
  select rowid,rownum,t.* from   tb_news_type t;
  select rowid,rownum,t.* from   dept t;
  select rowid,rownum,t.* from   emp t;
  
  -- 删除新闻分类中名字相同的列，留下一列
  delete from tb_news_type a  where rowid >(
  select min(rowid) from tb_news_type b where a.tname=b.tname );
```





# DQL

data query language数据查询语言主要是指select操作









