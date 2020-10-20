<%--
  User: cobight
  Date: 2020/9/28 11:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统一异常处理页面</title>
</head>
<body>
    <center>
        不好意思，模块出错，请联系相关人员（陈工 13866668888）<br>
        <a href="/">人力资源首页</a>
        <%
            //java脚本
            //根据配置的异常属性名称，获取异常对象
            Exception exception = (Exception)request.getAttribute("ex");
            //out 是9大内置对象之一，向response做打印，相当于response.getWrite()
            out.print("错误对象："+exception.getClass().getName()+"<br>描述"+exception.getMessage());
            exception.printStackTrace();
        %>
    </center>

</body>
</html>
