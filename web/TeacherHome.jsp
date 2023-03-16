<%@ page import="servlet.data.TaskInfoList" %>
<%@ page import="Util.useDatabase.DataSelect" %>
<%@ page import="servlet.data.TaskInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.util.Map" %><%--
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
    <title>欢迎您，${user.name}老师</title>
    <link rel="stylesheet" href="style.css">
    <style>
        .custom-divider {
            border: 0;
            height: 1px;
            background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
            margin: 1rem 0;
        }
        .sidebar {
            height: 100%;
            width: 250px;
            position: fixed;
            top: 0;
            right: -250px;
            background-color: #111;
            overflow-x: hidden;
            padding: 8px 0;
            transition: 0.3s;
            z-index: 1000;
        }

        .sidebar .closebtn {
            position: absolute;
            top: 0;
            left: 0;
            font-size: 36px;
            margin-left: 15px;
            color: white;
            cursor: pointer;
        }

        .openbtn {
            font-size: 20px;
            cursor: pointer;
            background-color: #111;
            color: white;
            padding: 10px 15px;
            border: none;
            position: fixed;
            top: 20px;
            right: 15px;
            z-index: 1001;
        }

        .openbtn:hover {
            background-color: #444;
        }

        .sidebar:hover {
            right: 0;
        }

        #noticeContainer {
            color: white;
            padding: 15px;
        }
    </style>
</head>
<body>
<div id="header">
    <h1>欢迎您，<span>${user.name}</span>老师</h1>
</div>
<div id="sidebar" class="sidebar">
    <div class="closebtn" onclick="toggleSidebar()">&times;</div>
    <div id="noticeContainer"></div>
</div>

<button id="openbtn" class="openbtn" style="display: block" onclick="toggleSidebar()">展示公告</button>

<div id="nav">
    <a href="#course-release">课程发布</a>
    <a href="#course-management">课程管理</a>
    <a href="#test-release">测试发布</a>
    <a href="#test-management">测试管理</a>
    <a href="#view-consultation">查看咨询</a>
</div>
<div id="content">
    <div id="course-release">
        <form action="AddCourse" method="post">
            <label for="course-name">课程名称</label>
            <input type="text" id="course-name" name="course-name" required>

            <label for="course-credits">课程学分</label>
            <input type="number" id="course-credits" name="course-credits" required>


            <button type="submit">发布</button>
            <button type="reset">重置</button>

        </form>
    </div>
    <div id="course-management"style="display:none">
        <table>
            <tr>
                <th>课题编号</th>
                <th>课题名称</th>
                <th>学分</th>
                <th>已选人数</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${courseInfoList}" var="courseInfo">
                <c:if test="${courseInfo.t_id == user.id}">
                <tr>
                    <td>${courseInfo.id}</td>
                    <td>${courseInfo.name}</td>
                    <td>${courseInfo.score}</td>
                    <td>${courseInfo.count}</td>
                    <td><a href="SelectCourseList?id=${courseInfo.id}&page=CourseFile.jsp"><button class="linkbutton">资源</button></a></td>
                    <td><a href="SelectCourseList?id=${courseInfo.id}&page=UpdateCourse.jsp"><button class="linkbutton">修改</button></a></td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
    <div id="test-release" style="display:none">
        <form action="AddTask" method="post">
            <label for="question-count">输入题目数量</label>
            <input type="number" id="question-count" name="question-count" min="1" required>
            <label for="c_id">对应课程id</label>
            <input type="number" id="c_id" name="c_id" min="1" required>
            <!-- 题目将根据题目数量动态显示在这里 -->
            <span id="questions-container"></span>
            <button type="submit">发布</button>
        </form>
    </div>
    <div id="test-management" style="display:none">
        <%
            TaskInfoList taskInfoList = new TaskInfoList();
            DataSelect.select(taskInfoList);

            HashMap<String,ArrayList<Integer>> sla = new HashMap<>();
            HashMap<String,Long> th = new HashMap<>();

            for (TaskInfo taskInfo:
                 taskInfoList) {
                ArrayList<Integer> la = new ArrayList<Integer>();
                Instant instant = Instant.ofEpochMilli(taskInfo.getTime()); // 将时间戳转换为Instant对象

                // 使用系统默认时区
                ZoneId zoneId = ZoneId.systemDefault();
                // 创建一个自定义的日期时间格式
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                // 使用指定的时区和格式将Instant对象转换为时间字符串
                String dateTimeStr = formatter.format(instant.atZone(zoneId));
                la.add(taskInfo.getC_id());la.add(taskInfo.getTaskDoInfoList().size());
                sla.put(dateTimeStr,la);
                th.put(dateTimeStr,taskInfo.getTime());

            }
        %>
        <table>
            <tr>
                <th>测试对应的课题编号</th>
                <th>发布时间</th>
                <th>已做人数</th>
                <th></th>
            </tr>
            <%
                for (Map.Entry<String,ArrayList<Integer>> entry:
                     sla.entrySet()) {
                    %>
                    <tr>
                        <td><%=entry.getValue().get(0)%></td>
                        <td><%=entry.getKey()%></td>
                        <td><%=entry.getValue().get(1)%></td>
                        <td><a href="SeeTask?c_id=<%=entry.getValue().get(0)%>&time=<%=th.get(entry.getKey())%>"><button class="linkbutton">查看详情</button></a></td>
                    </tr>
            <%}%>

        </table>
    </div>
    <div id="view-consultation" style="display:none">

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
<script>
    var notice = "公告：<br>";

    function updateNotice() {
        document.getElementById("noticeContainer").innerHTML = notice;
    }
    window.onload = updateNotice;
    var sidebarOpened = false;

    function toggleSidebar() {
        if (sidebarOpened) {
            closeSidebar();
        } else {
            openSidebar();
        }
    }

    function openSidebar() {
        document.getElementById("sidebar").style.right = "0";
        document.getElementById("openbtn").style.display = "none";
        sidebarOpened = true;
    }

    function closeSidebar() {
        document.getElementById("sidebar").style.right = "-250px";
        document.getElementById("openbtn").style.display = "block";
        sidebarOpened = false;
    }
    function fetchNotice() {

        fetch('GetNotice')
            .then(response => response.text())
            .then(newNotice => {
                notice = "公告：<br>"+newNotice;
                updateNotice();
            });
    }

    setInterval(fetchNotice, 1000);
</script>
<script>
    document.getElementById('question-count').addEventListener('input', function (event) {
        const questionCount = parseInt(event.target.value, 10);
        const questionsContainer = document.getElementById('questions-container');
        questionsContainer.innerHTML = '';

        for (let i = 0; i < questionCount; i++) {
            questionsContainer.innerHTML += (i > 0 ? '<hr class="custom-divider">' : '') +
                '<span>' +
                '<label for="question-' + i + '-name">题目名称</label>' +
                '<input type="text" id="question-' + i + '-name" name="question-' + i + '-name">' +
                '<label for="question-' + i + '-answer-a">答案A</label>' +
                '<input type="text" id="question-' + i + '-answer-a" name="question-' + i + '-answer-a">' +
                '<label for="question-' + i + '-answer-b">答案B</label>' +
                '<input type="text" id="question-' + i + '-answer-b" name="question-' + i + '-answer-b">' +
                '<label for="question-' + i + '-answer-c">答案C</label>' +
                '<input type="text" id="question-' + i + '-answer-c" name="question-' + i + '-answer-c">' +
                '<label for="question-' + i + '-answer-d">答案D</label>' +
                '<input type="text" id="question-' + i + '-answer-d" name="question-' + i + '-answer-d">' +
                '<label for="question-' + i + '-correct-answer">正确答案选项</label>' +
                '<select id="question-' + i + '-correct-answer" name="question-' + i + '-correct-answer">' +
                '<option value="A">A</option>' +
                '<option value="B">B</option>' +
                '<option value="C">C</option>' +
                '<option value="D">D</option>' +
                '</select>' +
                '</span>';
        }
    });
</script>
</body>
</html>


