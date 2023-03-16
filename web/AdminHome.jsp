<%--
  Created by IntelliJ IDEA.
  User: 11219
  Date: 2023/3/8
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎您，${user.name}</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div id="header">
    <h1>欢迎您，<span>${user.name}</span></h1>
</div>
<div id="nav">
    <a href="#account-add">账号添加</a>
    <a href="#account-manage">账号管理</a>
    <a href="#announcement-manage">公告管理</a>
</div>
<div id="content">
    <div id="account-add">
        <form action="AddUser" method="post">
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username">

            <label for="password">密码：</label>
            <input type="password" id="password" name="password">

            <label for="name">名称：</label>
            <input type="text" id="name" name="name">

            <label for="type">类型：</label>
            <select id="type" name="type">
                <option value="student">学生</option>
                <option value="teacher">老师</option>
            </select>

            <button type="submit">添加</button>
            <button type="reset">重置</button>
        </form>
    </div>
    <div id="account-manage" style="display:none">
        <table>
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>角色</th>
                <th></th>
            </tr>
            <c:forEach items="${userinfoList}" var="userinfo">
                <tr>
                    <td>${userinfo.id}</td>
                    <td>${userinfo.name}</td>
                    <td>${userinfo.role}</td>
                    <td><a href="SelectUserList?id=${userinfo.id}&page=UpdateUser.jsp"><button class="linkbutton">修改</button></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="announcement-manage" style="display:none">
        <form action="AddNotic" method="post">
            <label for="notic">公告内容</label>
            <textarea id="notic" name="notic" rows="5" cols="50"></textarea><br>
            <button type="submit">发布</button>
            <button type="reset">重置</button>
        </form>
    </div>
</div>
<script>
    // JS代码
    const navLinks = document.querySelectorAll('#nav a');
    const contentDivs = document.querySelectorAll('#content div');

    navLinks.forEach((link) => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            const targetId = link.getAttribute('href').slice(1);
            contentDivs.forEach((div) => {
                if (div.id === targetId) {
                    div.style.display = 'block';
                } else {
                    div.style.display = 'none';
                }
            });
        });
    });
</script>
</body>
</html>


