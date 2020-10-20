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
    <title>用户登录</title>

</head>
<body>
   <center>
       <h3>用户登录</h3><br>
       <div style="color: red">${errorInfo}</div>
       <form action="/userLogin.do" method="post">
           <!--content="aaa"&title="test"  {content:"aaaa",title:'test'}-->
       <table border="1" width="400px;">
           <tr align="center"><td>用户名</td><td><input type="text"  name="userName"  > </td></tr>
           <tr align="center"><td>密码</td><td><input type="text"  name="passWord"  >  </td></tr>
           <tr align="center"><td colspan="2"><input type="submit" value="登录"> </td></tr>
       </table>
       </form>
   </center>
</body>
</html>
