<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: cobight
  Date: 2020/9/25 11:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门列表</title>
</head>
<body>
    <center>
        <h3>部门列表</h3>
        <h4>欢迎${userInfo.realName}登录网站！</h4>
        <a href="/dept/toInsert.do">添加</a>
        <table border="1" width="800px">
            <tr>
                <td>编号</td>
                <td>名称</td>
                <td>位置</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${deptList}" var="dept">
                <tr>
                    <td>${dept.deptNo}</td>
                    <td>${dept.dName}</td>
                    <td>${dept.loc}</td>
                    <td><a href="/dept/toUpdate.do?deptNo=${dept.deptNo}">更新</a>&nbsp;<a onclick="del(${dept.deptNo})" href="javascript:void(0);">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </center>
</body>
</html>
<script>
    function del(deptNo) {
        var msg = "您确定要删除部门吗？";
        if (confirm(msg)==true){
            window.location.href="/dept/toDelete.do?deptNo="+deptNo;
        }
    }
</script>