<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/25
  Time: 11:33
  To change this template use File | Settings | File Templates.
  request.getContextPath() 获取上下文对象（项目）
--%>
<%
    // request.getScheme() 协议名称 http     getServerName 获取域名或者请求IP  getContextPath 获取项目名称
  String path  =  request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
  request.setAttribute("path",path);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新闻列表</title>
</head>
<body>
   <center>
       <h3>新闻列表</h3><br>
       <a href="/news/toAdd.do">添加</a><br>
       <table border="1" width="800px;">
           <tr><td>标题</td><td>内容</td><td>点击量</td><td>图片</td><td>操作</td></tr>
           <c:forEach items="${newsList}" var="news">
               <tr><td>${news.title}</td><td>${news.content}</td><td>${news.clickNum}</td>
                   <td>
                       <c:forEach items="${news.newsFileList}" var="nf">
                          ${nf.fileName}-${nf.filePath}-<img src="${path}${nf.filePath}" height="100px;" width="100px;"> <br>
                       </c:forEach>
                   </td>
                   <td><a href="/news/toUpdate.do?newsId=${news.newsId}">更新</a>&nbsp;<a href="">删除</a></td></tr>
           </c:forEach>
       </table>
   </center>
</body>
</html>
