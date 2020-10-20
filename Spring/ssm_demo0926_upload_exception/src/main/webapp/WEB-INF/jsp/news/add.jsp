<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: cobight
  Date: 2020/9/26 10:36
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加</title>
    <script src="/js/jquery-3.3.1.js"></script>
    <script>
        //初始化方法
        $(function(){
            $("#btnAdd").click(function(){
                $(this).parent().append("<div><input name=\"pic\" type=\"file\" /><input type='button' value='删除' onclick='del(this)'/></div>");
            });
        });
        function del(t){
            $(t).parent().remove();
        }
    </script>
</head>
<body>
<center>
    <h3>新闻添加</h3>
    <div style="color:red">${erroInfo}</div>
    <form action="/news/add.do" method="post" enctype="multipart/form-data">
        <table border="1" width="800px">
            <tr>
                <td>标题</td><td><input type="text" name="title"/></td>
            </tr>
            <tr><td>类型</td><td>
                <select name="typeId" id="selectNewsType">
                    <c:forEach items="${newsTypeList}" var="nt">
                        <option VALUE="${nt.TYPE_ID}">${nt.TNAME}</option>
                    </c:forEach>
                </select> </td></tr>
            <tr>
                <td>内容</td><td><textarea name="content" rows="5" cols="30"></textarea></td>
            </tr>
            <tr>
                <td>图片</td><td><input name="pic" type="file" /><input type="button" id="btnAdd" value="添加"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="添加"/></td>
            </tr>

        </table>
    </form>

</center>
</body>
</html>
