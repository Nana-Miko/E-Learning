<%@ page import="servlet.data.TaskInfoList" %>
<%@ page import="servlet.data.TaskInfo" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.time.Instant" %>
<%@ page import="servlet.data.TaskDoInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>测试详情</title>
    <link rel="stylesheet" href="style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
        }
    </style>
</head>
<%@ include file="homebtn.html" %>


<body>

<h1>测试详情</h1>

<%
    TaskInfoList taskInfoList = (TaskInfoList) request.getAttribute("taskInfoList");
    TaskInfo taskInfo = taskInfoList.get(0); // 获取第一个TaskInfo对象，用于显示c_id和time
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    ZoneId zoneId = ZoneId.systemDefault();
    String taskInfoTimeStr = formatter.format(Instant.ofEpochMilli(taskInfo.getTime()).atZone(zoneId));
%>

<p>测试课程号： <%= taskInfo.getC_id() %> 发布时间: <%= taskInfoTimeStr %></p>

<h2>学生完成情况</h2>
<table>
    <thead>
    <tr>
        <th>学生ID</th>
        <th>学生姓名</th>
        <th>得分</th>
        <th>完成时间</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (TaskDoInfo taskDoInfo : taskInfo.getTaskDoInfoList()) {
            String taskDoInfoTimeStr = formatter.format(Instant.ofEpochMilli(taskDoInfo.getFinish_time()).atZone(zoneId));
    %>
    <tr>
        <td><%= taskDoInfo.getS_id() %></td>
        <td><%= taskDoInfo.getS_name() %></td>
        <td><%= taskDoInfo.getScore() %></td>
        <td><%= taskDoInfoTimeStr %></td>
    </tr>
    <% } %>
    </tbody>
</table>

<h2>题目列表</h2>
<table>
    <thead>
    <tr>
        <th>题目</th>
        <th>选项A</th>
        <th>选项B</th>
        <th>选项C</th>
        <th>选项D</th>
        <th>正确选项</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (TaskInfo ti : taskInfoList) {
    %>
    <tr>
        <td><%= ti.getName() %></td>
        <td><%= ti.getA() %></td>
        <td><%= ti.getB() %></td>
        <td><%= ti.getC() %></td>
        <td><%= ti.getD() %></td>
        <td><%= ti.getRight() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>
