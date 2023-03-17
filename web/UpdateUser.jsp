<%@ page import="java.net.URLEncoder" %>
<%@ page import="servlet.data.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: 11219
  Date: 2023/3/15
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="homebtn.html" %>

<html>
<head>
    <title>更新信息</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
</div>
<div id="account-add">
    <form action="UpdateUser" method="post">
        <input type="hidden" name="id" value="${userinfo.id}">

        <label for="username">用户名：</label>
        <input type="text" id="username" name="username" value="${userinfo.no}">

        <label for="password">密码：</label>
        <input type="password" id="password" name="password" value="${userinfo.psw}">

        <label for="name">名称：</label>
        <input type="text" id="name" name="name" value="${userinfo.name}">

        <label for="type">类型：</label>
        <select id="type" name="type">
            <c:choose>
                <c:when test="${userinfo.role == '管理员'}">
                    <option value="0" selected>管理员</option>
                </c:when>
                <c:otherwise>
                    <option value="2" selected>学生</option>
                    <option value="1">老师</option>
                </c:otherwise>
            </c:choose>
        </select>

        <button type="submit">更新</button>
        <% UserInfo userinfo = (UserInfo) request.getAttribute("userinfo");
            if(!userinfo.getRole().equals("管理员")) { %>
        <button type="button" class="deletebutton" onclick="link()">删除</button>
        <% } %>
    </form>
</div>
</body>
<script>
    function link(){
        window.location.href = "DeleteUser?id=${userinfo.id}";
    }
</script>
</html>
