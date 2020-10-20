<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/24
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门列表</title>
</head>
<body>
   <!--$ {deptList}  默认从requestScope  sessionScope  applicationScope-->
   <c:forEach items="${deptList}" var="dept">
       ${dept.deptNo}-${dept.dname}-${dept.loc}<br>
   </c:forEach>
</body>
</html>
