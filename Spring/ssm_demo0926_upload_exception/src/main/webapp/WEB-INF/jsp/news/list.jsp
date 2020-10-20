<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: cobight
  Date: 2020/9/26 10:36
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    request.setAttribute("path",path);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h3>新闻列表</h3>
    <a href="/news/toAdd.do">添加</a>
    <table border="1" width="800px">
        <tr>
            <td>标题</td>
            <td>内容</td>
            <td>点击量</td>
            <td>图片</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${newsList}" var="news">
            <tr>
                <td>${news.title}</td>
                <td>${news.content}</td>
                <td>${news.clickNum}</td>
                <td>
                    <c:forEach items="${news.newsFileList}" var="nf">
                        ${nf.fileName} -- ${nf.filePath}-<img src="/<%=request.getContextPath()%>${nf.filePath}" alt="${nf.fileName}" height="100ox" width="100px"/><br>
                    </c:forEach>
                </td>
                <td><a href="/news/toUpdate.do?newsId=${news.newsId}">更新</a>&nbsp;<a onclick="del(${news.newsId})" href="javascript:void(0);">删除</a></td>
            </tr>
        </c:forEach>
    </table>
</center>
</body>
</html>
<script>
    function del(newsId){
        if (confirm("删除这条新闻和附件?")==true){
            window.location.href="/news/delete.do?newsId="+newsId;
        }
    }
</script>



