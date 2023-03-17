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
    <form action="UpdateCourse" method="post">
        <input type="hidden" name="id" value="${courseInfo.id}">
        <input type="hidden" name="t_id" value="${courseInfo.t_id}">

        <label for="name">课程名：</label>
        <input type="text" id="name" name="name" value="${courseInfo.name}" required>

        <label for="score">课程学分</label>
        <input type="number" id="score" name="score" value="${courseInfo.score}" required>

        <button type="submit">更新</button>

        <button type="button" class="deletebutton" onclick="link()">删除</button>

    </form>
</div>
</body>
<script>
    function link(){
        window.location.href = "DeleteCourse?id=${courseInfo.id}";
    }
</script>
</html>
