<%--
  User: cobight
  Date: 2020/9/24 19:28
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${deptList}" var="dept">
    ${dept.deptNo}--${dept.deptName}--${dept.loc}<br>
</c:forEach>
</body>
</html>
