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
    <title>部门更新</title>
</head>
<body>
   <center>
       <h3>部门更新</h3><br>
       <div style="color: red">${errorInfo}</div>
       <form action="/dept/update.do" method="post">
       <table border="1" width="400px;">
           <tr><td>编号</td><td><input type="text"  name="deptNo" value="${dept.deptNo}" readonly="readonly"> </td></tr>
           <tr><td>名称</td><td><input type="text"  name="dname" value="${dept.dname}"> </td></tr>
           <tr><td>位置</td><td><input type="text"  name="loc" value="${dept.loc}"> </td></tr>
           <tr><td colspan="2"><input type="submit" value="更新"> </td></tr>
       </table>
       </form>
   </center>
</body>
</html>
