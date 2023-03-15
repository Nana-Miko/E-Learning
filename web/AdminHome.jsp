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
    <title>欢迎您，<span>${name}</span></title>
    <style>
        /* 样式 */
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        #header {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 20px;
        }

        #nav {
            background-color: #f2f2f2;
            overflow: hidden;
        }

        #nav a {
            float: left;
            display: block;
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        #nav a:hover {
            background-color: #ddd;
        }

        #content {
            padding: 20px;
        }
        /* 设置表单样式 */
        form {
            background-color: #FFFFFF;
            border: 1px solid #DDDDDD;
            border-radius: 5px;
            box-shadow: 0px 0px 5px #CCCCCC;
            margin: 50px auto;
            max-width: 400px;
            padding: 20px;
        }

        /* 设置标签样式 */
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        /* 设置输入框样式 */
        input[type=text], input[type=password], select {
            border: 1px solid #CCCCCC;
            border-radius: 3px;
            font-size: 16px;
            padding: 8px;
            width: 100%;
        }

        /* 设置按钮样式 */
        button[type=submit], button[type=reset] {
            background-color: #4CAF50;
            border: none;
            border-radius: 3px;
            color: #FFFFFF;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
            padding: 10px;
            width: 100%;
        }

        /* 设置提交按钮的悬停样式 */
        button[type=submit]:hover {
            background-color: #3E8E41;
        }

        /* 设置重置按钮的悬停样式 */
        button[type=reset]:hover {
            background-color: #CCCCCC;
            color: #000000;
        }
    </style>
</head>
<body>
<div id="header">
    <h1>欢迎您，<span>${name}</span></h1>
</div>
<div id="nav">
    <a href="#account-add">账号添加</a>
    <a href="#account-manage">账号管理</a>
    <a href="#announcement-manage">公告管理</a>
    <a href="#account-reset" >账号设置</a>
</div>
<div id="content">
    <div id="account-reset">

    </div>
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
            </tr>
            <c:forEach items="${userinfoList}" var="userinfo">
                <tr>
                    <td>${userinfo.id}</td>
                    <td>${userinfo.name}</td>
                    <td>${userinfo.role}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="announcement-manage" style="display:none">
        <h2>公告管理</h2>
        <p>这是公告管理的内容。</p>
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


