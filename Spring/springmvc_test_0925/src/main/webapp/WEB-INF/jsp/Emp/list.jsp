<%--
  User: cobight
  Date: 2020/9/25 8:36
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${empList}" var="emp">
        ${emp.ename}---${emp.sal}
    </c:forEach>
</body>
</html>
