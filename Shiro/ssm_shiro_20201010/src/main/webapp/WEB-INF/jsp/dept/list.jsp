<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/25
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门列表</title>
</head>
<body>
   <center>
       <h3>部门列表</h3><br>
       <h4>欢迎${userInfo.realName}登录网站！</h4>
       <s:hasAnyRoles name="副总1"><a href="">添加</a><br></s:hasAnyRoles>

       <table border="1" width="800px;">
           <tr><td>编号</td><td>名称</td><td>位置</td><td>操作</td></tr>
           <c:forEach items="${deptList}" var="dept">
               <tr><td>${dept.deptNo}</td><td>${dept.dname}</td><td>${dept.loc}</td>
                   <td><a href="/dept/toUpdate.do?deptNo=${dept.deptNo}">更新</a>&nbsp;<a href="">删除</a></td></tr>
           </c:forEach>
       </table>
   </center>
</body>
</html>
