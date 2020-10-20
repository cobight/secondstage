<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: cobight
  Date: 2020/9/26 10:36
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <script src="/js/jquery-3.3.1.js"></script>

</head>
<body>
    <center>
        <h3>用户登录</h3>
        <div style="color:red">${erroInfo}</div>
        <form action="/userLogin.do" method="post" enctype="multipart/form-data">
            <table border="1" width="800px">
                <tr>
                    <td>用户名</td><td><input type="text" name="userName"/></td>
                </tr>
                <tr>
                    <td>密码</td><td><input type="text" name="passWord"/></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="添加"/></td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
