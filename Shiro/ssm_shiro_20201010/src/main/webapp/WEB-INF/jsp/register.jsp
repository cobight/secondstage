<%--
  User: cobight
  Date: 2020/10/10 11:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<center>
    <h3>用户注册</h3><br>
    <div style="color: red">${errorInfo}</div>
    <form action="/register.do" method="post">
        <!--content="aaa"&title="test"  {content:"aaaa",title:'test'}-->
        <table border="1" width="400px;">
            <tr align="center"><td>用户名</td><td><input type="text"  name="userName"  > </td></tr>
            <tr align="center"><td>姓名</td><td><input type="text"  name="realName"  > </td></tr>
            <tr align="center"><td>密码</td><td><input type="text"  name="passWord"  >  </td></tr>
            <tr align="center"><td colspan="2"><input type="submit" value="登录"> </td></tr>
        </table>
    </form>
</center>
</body>
</html>
