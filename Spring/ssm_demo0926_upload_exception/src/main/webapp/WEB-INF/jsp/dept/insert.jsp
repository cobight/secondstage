<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: cobight
  Date: 2020/9/25 11:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门添加</title>
</head>
<body>
    <center>
        <h3>部门添加</h3>
        <div style="color:red">${erroInfo}</div>
        <form action="/dept/insert.do" method="post">
            <table border="1" width="800px">
                <tr>
                    <td>编号</td><td><input type="text" name="deptNo" value="" /></td>
                </tr>
                <tr>
                    <td>部门名称</td><td><input type="text" name="dName" value=""/></td>
                </tr>
                <tr>
                    <td>位置</td><td><input type="text" name="loc" value=""/></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="添加"/></td>
                </tr>

            </table>
        </form>

    </center>
</body>
</html>
