# vmware

## 网卡

```
vm 0:
桥接模式的虚拟机，就像一个在路由器”民政局”那里”上过户口”的成年人，有自己单独的居住地址，虽然和主机住在同一个大院里，但好歹是有户口的人，可以大摇大摆地直接和外面通信。
vm8:
NAT模式的虚拟机，纯粹就是一个没上过户口的黑户，路由器”民政局”根本不知道有这么个人，自然也不会主动和它通信。即使虚拟机偶尔要向外面发送点的信件，都得交给主机以主机的名义转发出去，主机还专门请了一位叫做NAT的老大爷来专门负责这些虚拟机的发信、收信事宜。
vm1:
仅主机模式的虚拟机，纯粹是一个彻彻底底的黑奴，不仅没有户口、路由器”民政局”不知道这么号人，还被主机关在小黑屋里，连信件也不准往外发。
```

## Xshell连接异常处理

* 服务启动（dhcp与nat必须启动，否则连不了网）

  ![img](readme.assets/3429116874.bmp)

* vmware配置

  ![img](readme.assets/1136011658.bmp)

* service sshd status 查看是否启动

* vim /etc/sysconfig/network-scripts/ifcfg-ens33 看配置

* ping主机与外网，还不行就百度，或者重装linux



# 命令

## 分类

内部命令：属于Shell解析器的一部分（cd  pwd）,执行速度会快

外部命令：独立于Shell解析器之外的文件程序(ls mkdir)

帮助文档

* 内部命令：help + 命令（help cd）
* 外部命令：man + 命令（man ls）

## 命令提示功能

```
一下tab，路径补全
两下tab，路径提示

例子：
	vi /etc/sys  + 两下TAB
```



## 开关机

```
-h   hot 挂起
关机(root用户使用)：
 shutdown  -h  now
 poweroff 
 init  0     （0-6 分别代表启动状态  6重启）
 shutdown -h 5    //5分钟后关机
 重启：   
 reboot 
 shutdown  -r now
 init 6
 shutdown  -r  5   //5分钟后重启
 shutdown  -r  11:11  //11点11分重启
 shutdown  -c  //取消重启   
```

## 时间相关

```
date
```

## 清屏

```
ctrl + L
clear
```

## IP相关

```
dhclient 动态配置ip
帮助文档：man dhclient
dhclient -r 释放IP
```



## 文件/路径相关

```
单词
cd = cahnge directory
ls = list 
pwd = print working directory
```



### 查看目录内容

```
ls /  显示指定文件夹下的文件（.开头的文件看不到）
		-l		(long)	  显示内容详情
        -a	(all)         显示隐藏的文件、目录（和不忽略.开头文件 ）
        -t		(time)	  
ls -l /  显示根路径下的文件详细信息（命令+选项+参数）
ls -a -l -t  多个选项一起用
ls -alt		多个选项连用

ll=ls -l 查看目录详情
pwd  #print name of current/working directory打印当前工作文件夹(目录)
```

### 路径切换

```
cd = Change the shell working directory改变当前工作目录
cd /etc/sysconfig 	当前路径跳转 = cd /etc/sysconfig/
cd ..(/)	   路径向上一级
cd ../..(/)	   路径向上两级
cd ../../../../..(/)	   路径向上多级
cd /	切换到根目录
cd ~  /或者啥都不跟		切换到用户主目录root目录还是home用户，看用户
```

### 路径创建

```
mkdir = make directories 创建多个目录
mkdir .aa 创建一个隐藏.aa目录
mkdir spring mybatis springmvc  空格分隔创多个目录
mkdir -p frame/springboot/controller 创建多级目录（无父目录）
mkdir  spring/ioc spring/aop 创建多级多个目录（有父目录可以省略-p）
```

### 文件创建

```
touch 改变文件时间戳（文件创建时间），文件不存在就创建一个空文件
touch .a.txt  创建一个隐藏文件
touch q.txt w.txt e.txt  创建多个文件
touch (/)spring/spring-config.xml  创建一个文件
```



### 删除文件或路径

```
rmdir  #remove empty directories 只能删除空目录
rm		#remove files or directories 删除文件或文件夹
rm cc.txt 有提示的删除
-f forece 强制删除，不提示
-r recursive 递归，循环删除
rm -f cc.txt  强制删除cc.txt ，不再询问
rm -f cc.txt dd.txt 删两个文件
rm -rf a  强制删除文件夹a，包括下面的所有子文件 ，不再询问
rm -rf a spring mybatis 删三个目录 
******不要rm -rf /*******
```





### 文件（路径）复制与移动

#### 复制cp

recuisive

```
cp a.txt spring  复制当前文件夹下的a.txt 到当前文件夹下的spring文件夹下、
cp /root/b.txt /tmp/  复制指定路径下的指定文件，到指定路径下

cp -r spring frame/  复制当前路径下的spring文件夹，到当前路径下的frame路径下
指定路径
```

#### 移动mv

```
文件
mv a.txt a   移动 当前路径下的a.txt到a目录下
mv b.txt a/b(/)
mv c.txt cc.txt  修改c.txt文件名 至 cc.txt
路径
mv spring frame/   spring覆盖路径frame   ，还有提示
mv springmvc/ frame/   移动springmvc路径到  frame路径下
mv frame/ frames/  修改目录名称frame 至 frames，重复会提示是否覆盖
```

### 文件目录查找find

```
find #search for files in a directory hierarchy
find /etc -name "*.txt " 找指定路径下的txt结尾文件，隐藏文件也显示
find -name "*.txt " 找本路径下的txt结尾文件，隐藏文件也显示
输出中./表示当前目录
```

### 文件内容匹配grep

```
grep，egrep，fgrep - print lines matching a pattern
	(global regular expression(regexp)正则表达式)
	（缩写来自Globally search a Regular Expression and Print）是一种强大的文本搜索工具，它能使用特定模式匹配（包括正则表达式）搜索文本，并默认输出匹配行。
grep 20 a.txt  从a.txt中按照正则表达式查找含有20的行，并打印
```



### echo、cat、more、less、head、tail的读写相关

```
echo 'hello' 显示一行内容
echo 'hello' >> aa.txt 
echo 'hello word' > aa.txt  
/*
	>写入（覆盖原有内容）
	>>追加（追加到原内容后）
*/
--------------------------------------------
cat concatenate 连锁的，连续的，串级
	concatenate	files and print on the standard output

    cat aa.txt 显示aa.txt 文件内容
    cat a.txt b.txt c.txt 串联多个文件并打印，会自动换行
    cat setting.xml 文件太大只会显示最后一页
---------------------------------------------
more  #file perusal filter for crt(显示器) viewing  文件阅读显示器
		分页显示内容，读取完后显示，会慢
		不支持/查找
    more setting.xml 显示文件第一页
    enter键向下一行一行显示
    space键向下一页一页显示
    ctrl + f   forward向前向下翻页
    ctrl + b   back   向后向上翻页
    q  退出
-----------------------------------------------
less 
        #与more相似的命令，一点一点读，比vi快，比more快
        #支持关键字查找  /关键字	全文高亮
	enter键向下一行一行显示
    space键向下一页一页显示
    ctrl + f   forward向前向下翻页
    ctrl + b   back   向后向上翻页
    q  退出
--------------------------------------------------
head output the first part of files显示文件开头部分
head settings.xml  默认显示setting前十行
head -1 setting.xml  显示一行
head -5 setting.xml	 显示5行
--------------------------------------------------
tail output the last part of files显示文件结尾部分
tail settings.xml  默认显示setting后十行
tail -1 setting.xml  显示一行
tail -5 setting.xml	 显示五行
tail -f setting.xml  实时追踪文件的最后部分
另开会话，在窗口
```

### 文件统计

```
wc - print newline，word，and byte icounts for each file
echo的换行也是字节，汉字utf-8是三个字节
wc b.txt
行 单词 字节 文件名
```



## vi相关

https://www.runoob.com/linux/linux-vim.html

**命令模式（Command mode）**，**输入模式（Insert mode）**和**底线命令模式（Last line mode）**。 这三种模式的作用分别是：

### 命令模式：

用户刚刚启动 vi/vim，便进入了命令模式。

此状态下敲击键盘动作会被Vim识别为命令，而非输入字符。比如我们此时按下i，并不会输入一个字符，i被当作了一个命令。

以下是常用的几个命令：

- **i** 切换到输入模式，以输入字符。
- **x** 删除当前光标所在处的字符。
- **:** 切换到底线命令模式，以在最底一行输入命令。

若想要编辑文本：启动Vim，进入了命令模式，按下i，切换到输入模式。

命令模式只有一些最基本的命令，因此仍要依靠底线命令模式输入更多命令。

### 输入模式

在命令模式下按下 i 就进入了输入模式。

在输入模式中，可以使用以下按键：

- **字符按键以及Shift组合**，输入字符
- **ENTER**，回车键，换行
- **BACK SPACE**，退格键，删除光标前一个字符
- **DEL**，删除键，删除光标后一个字符
- **方向键**，在文本中移动光标
- **HOME**/**END**，移动光标到行首/行尾
- **Page Up**/**Page Down**，上/下翻页
- **Insert**，切换光标为输入/替换模式，光标将变成竖线/下划线
- **ESC**，退出输入模式，切换到命令模式

### 底线命令模式

在命令模式下按下:（英文冒号）就进入了底线命令模式。

底线命令模式可以输入单个或多个字符的命令，可用的命令非常多。

在底线命令模式中，基本的命令有（已经省略了冒号）：

- q 退出程序
- w 保存文件

按ESC键可随时退出底线命令模式。

### vi/vim 按键说明

除了上面简易范例的 i, Esc, :wq 之外，其实 vim 还有非常多的按键可以使用。

#### 第一部分：一般模式可用的光标移动、复制粘贴、搜索替换等

| 移动光标的方法                                               |                                                              |
| :----------------------------------------------------------- | ------------------------------------------------------------ |
| h 或 向左箭头键(←)                                           | 光标向左移动一个字符                                         |
| j 或 向下箭头键(↓)                                           | 光标向下移动一个字符                                         |
| k 或 向上箭头键(↑)                                           | 光标向上移动一个字符                                         |
| l 或 向右箭头键(→)                                           | 光标向右移动一个字符                                         |
| 如果你将右手放在键盘上的话，你会发现 hjkl 是排列在一起的，因此可以使用这四个按钮来移动光标。 如果想要进行多次移动的话，例如向下移动 30 行，可以使用 "30j" 或 "30↓" 的组合按键， 亦即加上想要进行的次数(数字)后，按下动作即可！ |                                                              |
| [Ctrl] + [f]                                                 | 屏幕『向下』移动一页，相当于 [Page Down]按键 (常用)          |
| [Ctrl] + [b]                                                 | 屏幕『向上』移动一页，相当于 [Page Up] 按键 (常用)           |
| [Ctrl] + [d]                                                 | 屏幕『向下』移动半页                                         |
| [Ctrl] + [u]                                                 | 屏幕『向上』移动半页                                         |
| +                                                            | 光标移动到非空格符的下一行                                   |
| -                                                            | 光标移动到非空格符的上一行                                   |
| n<space>                                                     | 那个 n 表示『数字』，例如 20 。按下数字后再按空格键，光标会向右移动这一行的 n 个字符。例如 20<space> 则光标会向后面移动 20 个字符距离。 |
| 0 或功能键[Home]                                             | 这是数字『 0 』：移动到这一行的最前面字符处 (常用)           |
| $ 或功能键[End]                                              | 移动到这一行的最后面字符处(常用)                             |
| H                                                            | 光标移动到这个屏幕的最上方那一行的第一个字符                 |
| M                                                            | 光标移动到这个屏幕的中央那一行的第一个字符                   |
| L                                                            | 光标移动到这个屏幕的最下方那一行的第一个字符                 |
| G                                                            | 移动到这个档案的最后一行(常用)                               |
| nG                                                           | n 为数字。移动到这个档案的第 n 行。例如 20G 则会移动到这个档案的第 20 行(可配合 :set nu) |
| gg                                                           | 移动到这个档案的第一行，相当于 1G 啊！ (常用)                |
| n<Enter>                                                     | n 为数字。光标向下移动 n 行(常用)                            |
| 搜索替换                                                     |                                                              |
| /word                                                        | 向光标之下寻找一个名称为 word 的字符串。例如要在档案内搜寻 vbird 这个字符串，就输入 /vbird 即可！ (常用) |
| ?word                                                        | 向光标之上寻找一个字符串名称为 word 的字符串。               |
| n                                                            | 这个 n 是英文按键。代表重复前一个搜寻的动作。举例来说， 如果刚刚我们执行 /vbird 去向下搜寻 vbird 这个字符串，则按下 n 后，会向下继续搜寻下一个名称为 vbird 的字符串。如果是执行 ?vbird 的话，那么按下 n 则会向上继续搜寻名称为 vbird 的字符串！ |
| N                                                            | 这个 N 是英文按键。与 n 刚好相反，为『反向』进行前一个搜寻动作。 例如 /vbird 后，按下 N 则表示『向上』搜寻 vbird 。 |
| 使用 /word 配合 n 及 N 是非常有帮助的！可以让你重复的找到一些你搜寻的关键词！ |                                                              |
| :n1,n2s/word1/word2/g                                        | n1 与 n2 为数字。在第 n1 与 n2 行之间寻找 word1 这个字符串，并将该字符串取代为 word2 ！举例来说，在 100 到 200 行之间搜寻 vbird 并取代为 VBIRD 则： 『:100,200s/vbird/VBIRD/g』。(常用) |
| **:1,$s/word1/word2/g** 或 **:%s/word1/word2/g**             | 从第一行到最后一行寻找 word1 字符串，并将该字符串取代为 word2 ！(常用) |
| **:1,$s/word1/word2/gc** 或 **:%s/word1/word2/gc**           | 从第一行到最后一行寻找 word1 字符串，并将该字符串取代为 word2 ！且在取代前显示提示字符给用户确认 (confirm) 是否需要取代！(常用) |
| 删除、复制与贴上                                             |                                                              |
| x, X                                                         | 在一行字当中，x 为向后删除一个字符 (相当于 [del] 按键)， X 为向前删除一个字符(相当于 [backspace] 亦即是退格键) (常用) |
| nx                                                           | n 为数字，连续向后删除 n 个字符。举例来说，我要连续删除 10 个字符， 『10x』。 |
| dd                                                           | 删除游标所在的那一整行(常用)                                 |
| ndd                                                          | n 为数字。删除光标所在的向下 n 行，例如 20dd 则是删除 20 行 (常用) |
| d1G                                                          | 删除光标所在到第一行的所有数据                               |
| dG                                                           | 删除光标所在到最后一行的所有数据                             |
| d$                                                           | 删除游标所在处，到该行的最后一个字符                         |
| d0                                                           | 那个是数字的 0 ，删除游标所在处，到该行的最前面一个字符      |
| yy                                                           | 复制游标所在的那一行(常用)                                   |
| nyy                                                          | n 为数字。复制光标所在的向下 n 行，例如 20yy 则是复制 20 行(常用) |
| y1G                                                          | 复制游标所在行到第一行的所有数据                             |
| yG                                                           | 复制游标所在行到最后一行的所有数据                           |
| y0                                                           | 复制光标所在的那个字符到该行行首的所有数据                   |
| y$                                                           | 复制光标所在的那个字符到该行行尾的所有数据                   |
| p, P                                                         | p 为将已复制的数据在光标下一行贴上，P 则为贴在游标上一行！ 举例来说，我目前光标在第 20 行，且已经复制了 10 行数据。则按下 p 后， 那 10 行数据会贴在原本的 20 行之后，亦即由 21 行开始贴。但如果是按下 P 呢？ 那么原本的第 20 行会被推到变成 30 行。 (常用) |
| J                                                            | 将光标所在行与下一行的数据结合成同一行                       |
| c                                                            | 重复删除多个数据，例如向下删除 10 行，[ 10cj ]               |
| u                                                            | 复原前一个动作。(常用)                                       |
| [Ctrl]+r                                                     | 重做上一个动作。(常用)                                       |
| 这个 u 与 [Ctrl]+r 是很常用的指令！一个是复原，另一个则是重做一次～ 利用这两个功能按键，你的编辑，嘿嘿！很快乐的啦！ |                                                              |
| .                                                            | 不要怀疑！这就是小数点！意思是重复前一个动作的意思。 如果你想要重复删除、重复贴上等等动作，按下小数点『.』就好了！ (常用) |

#### 第二部分：一般模式切换到编辑模式的可用的按钮说明

| 进入输入或取代的编辑模式                                     |                                                              |
| :----------------------------------------------------------- | ------------------------------------------------------------ |
| i, I                                                         | 进入输入模式(Insert mode)： i 为『从目前光标所在处输入』， I 为『在目前所在行的第一个非空格符处开始输入』。 (常用) |
| a, A                                                         | 进入输入模式(Insert mode)： a 为『从目前光标所在的下一个字符处开始输入』， A 为『从光标所在行的最后一个字符处开始输入』。(常用) |
| o, O                                                         | 进入输入模式(Insert mode)： 这是英文字母 o 的大小写。o 为『在目前光标所在的下一行处输入新的一行』； O 为在目前光标所在处的上一行输入新的一行！(常用) |
| r, R                                                         | 进入取代模式(Replace mode)： r 只会取代光标所在的那一个字符一次；R会一直取代光标所在的文字，直到按下 ESC 为止；(常用) |
| 上面这些按键中，在 vi 画面的左下角处会出现『--INSERT--』或『--REPLACE--』的字样。 由名称就知道该动作了吧！！特别注意的是，我们上面也提过了，你想要在档案里面输入字符时， 一定要在左下角处看到 INSERT 或 REPLACE 才能输入喔！ |                                                              |
| [Esc]                                                        | 退出编辑模式，回到一般模式中(常用)                           |

#### 第三部分：一般模式切换到指令行模式的可用的按钮说明

| 指令行的储存、离开等指令                                     |                                                              |
| :----------------------------------------------------------- | ------------------------------------------------------------ |
| :w                                                           | 将编辑的数据写入硬盘档案中(常用)                             |
| :w!                                                          | 若文件属性为『只读』时，强制写入该档案。不过，到底能不能写入， 还是跟你对该档案的档案权限有关啊！ |
| :q                                                           | 离开 vi (常用)                                               |
| :q!                                                          | 若曾修改过档案，又不想储存，使用 ! 为强制离开不储存档案。    |
| 注意一下啊，那个惊叹号 (!) 在 vi 当中，常常具有『强制』的意思～ |                                                              |
| :wq                                                          | 储存后离开，若为 :wq! 则为强制储存后离开 (常用)              |
| ZZ                                                           | 这是大写的 Z 喔！如果修改过，保存当前文件，然后退出！效果等同于(保存并退出) |
| ZQ                                                           | 不保存，强制退出。效果等同于 **:q!**。                       |
| :w [filename]                                                | 将编辑的数据储存成另一个档案（类似另存新档）                 |
| :r [filename]                                                | 在编辑的数据中，读入另一个档案的数据。亦即将 『filename』 这个档案内容加到游标所在行后面 |
| :n1,n2 w [filename]                                          | 将 n1 到 n2 的内容储存成 filename 这个档案。                 |
| :! command                                                   | 暂时离开 vi 到指令行模式下执行 command 的显示结果！例如 『:! ls /home』即可在 vi 当中察看 /home 底下以 ls 输出的档案信息！ |
| vim 环境的变更                                               |                                                              |
| :set nu                                                      | 显示行号，设定之后，会在每一行的前缀显示该行的行号           |
| :set nonu                                                    | 与 set nu 相反，为取消行号！                                 |

特别注意，在 vi/vim 中，数字是很有意义的！数字通常代表重复做几次的意思！ 也有可能是代表去到第几个什么什么的意思。

举例来说，要删除 50 行，则是用 『50dd』 对吧！ 数字加在动作之前，如我要向下移动 20 行呢？那就是『20j』或者是『20↓』即可



## ssh相关

```
service sshd status查看状态
```

## firewalld防火墙

```
service firewalld status
systemctl status firewalld防火墙状态

firewall-cmd--status
是否running

service firewalld restart 重启
service firewalld start  开启
service firewalld stop  关闭

systemctl restart firewalld 重启
systemctl start firewalld 开启
systemctl stop firewalld  关闭

systemctl  disable firewalld  关闭开机启动
systemctl  enable firewalld  设置开机启动

***
# service firewalld disable
The service command supports only basic LSB actions (start, stop, restart, try-restart, reload, force-reload, status). For other actions, please try to use systemctl.
```

### 防火墙列表

```
 firewall-cmd --list-all
```

```
[root@localhost ~]#  firewall-cmd --list-all
public (active)
  target: default
  icmp-block-inversion: no
  interfaces: ens33
  sources: 
  services: ssh dhcpv6-client
  ports: 80/tcp 1433/tcp 1251/tcp 3306/tcp 8080/tcp 8888/tcp
  protocols: 
  masquerade: no
  forward-ports: 
  source-ports: 
  icmp-blocks: 
  rich rules:
```

### 端口放行与关闭

```
firewall开启和关闭端口
以下都是指在public的zone下的操作，不同的Zone只要改变Zone后面的值就可以
添加：
firewall-cmd --zone=public --add-port=3306/tcp --permanent    （--permanent永久生效，没有此参数重启后失效）
重新载入：
firewall-cmd --reload
查看：	
firewall-cmd --zone=public --query-port=3306/tcp   
删除：
firewall-cmd --zone=public --remove-port=3306/tcp --permanent
```

### 常用端口介绍

```
1521 oracle
3306 mysql
1433 sql server
8080/8888 程序猿web项目
80 http
443 https
```

