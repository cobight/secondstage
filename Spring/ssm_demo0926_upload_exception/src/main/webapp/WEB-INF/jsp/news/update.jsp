<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: cobight
  Date: 2020/9/25 11:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门更新</title>
    <script src="/js/jquery-3.3.1.js"></script>
</head>
<body>
    <center>
        <h3>新闻更新</h3>
        <div style="color:red">${erroInfo}</div>
        <form action="/news/update.do" method="post" enctype="multipart/form-data">
            <table border="1" width="800px">
                <tr>
                    <td>标题</td><td><input type="hidden" name="newsId" value="${news.newsId}"/><input type="text" name="title" value="${news.title}"/></td>
                </tr>
                <tr><td>类型</td><td>
                    <select name="typeId" id="selectNewsType">
                        <c:forEach items="${newsTypeList}" var="nt">
                            <c:choose>
                                <c:when test="${nt.TYPE_ID}=${news.typeId}">
                                    <option selected VALUE="${nt.TYPE_ID}">${nt.TNAME}</option>
                                </c:when>
                                <c:otherwise>
                                    <option VALUE="${nt.TYPE_ID}">${nt.TNAME}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select> </td></tr>
                <tr>
                    <td>内容</td><td><textarea name="content" rows="5" cols="30">${news.content}</textarea></td>
                </tr>
                <tr>
                    <td>附件</td>
                    <td>
                        <input type="button" onclick="add(this)" value="添加" /><br>
                        <c:forEach items="${news.newsFileList}" var="nf">
                            ${nf.fileName} -- ${nf.filePath}<a href="/news/delFile.do?newsId=${news.newsId}&id=${nf.id}">删除</a><br>
                        </c:forEach>

                    </td>
                </tr>
                    <td colspan="2"><input type="submit" value="更新"/></td>
                </tr>

            </table>
        </form>

    </center>
</body>
</html>
<script>
    function add(t) {
        $(t).parent().append("<div><input type='file' name='pic'/><input type='button' value='删除' onclick='del(this)'/></div>")
    }
    function del(t) {
        $(t).parent().remove();
    }
</script>