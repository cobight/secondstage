<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
       <a href="">添加</a><br>
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
