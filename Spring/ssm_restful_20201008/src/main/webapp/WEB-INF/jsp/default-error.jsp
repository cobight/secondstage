<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/28
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统一异常处理页面</title>
</head>
<body>
      <center>
           不好意思，本模块出错了，请联系技术部相关人员(陈工 13866668888)<br>
            <a href="/">人力资源首页</a>&nbsp;
            <a href="/">订单首页</a>&nbsp;
            <a href="/">商品首页</a><br>
      <%
         //java脚本
           //根据配置的异常属性名称，获取异常对象
          Exception exception =   (Exception)request.getAttribute("ex");
          // out 是jsp 9大内置对象之一 向response做打印   相当于response.getWriter()
          // exception.getMessage()
          out.print("错误对象："+exception.getClass().getName()+",描述："+exception.getMessage()+"，异常栈追踪：");
          //异常栈追踪打印到页面
          exception.printStackTrace(new PrintWriter(out));
      %>
      </center>
</body>
</html>
