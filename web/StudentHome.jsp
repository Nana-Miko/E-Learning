<%@ page import="Util.useDatabase.DataSelect" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Map" %>
<%@ page import="servlet.data.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>

    </style>
    <meta charset="UTF-8">
    <title>欢迎您，${user.name}同学</title>
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
        /*已选课程的字体大小*/
        .center-text {
            text-align: center;
            text-transform: uppercase;
            font-size: 1.2em;
        }
        .added {
            background-color: grey;
            color: white;
        }

        #header {
            background-image: url('headback.jpg'); /* 设置背景图片 */
            background-size: cover;
            color: #fff;
            text-align: center;
            padding: 50px 0;
            margin: 0;
        }


    </style>
</head>



<body>
<div id="header">
    <h1>欢迎您，<span>${user.name}</span>同学</h1>
</div>

<div id="sidebar" class="sidebar">
    <div class="closebtn" onclick="toggleSidebar()">&times;</div>
    <div id="noticeContainer"></div>
</div>

<button id="openbtn" class="openbtn" style="display: block" onclick="toggleSidebar()">展示公告</button>


<div id="nav">

    <a href="#course">课程选择</a>
    <a href="#test">在线考试</a>
    <a href="#question">问题咨询</a>
</div>


<div id="content">
    <div id="course">
        <label class="center-text" >可选课程</label>
        <span>
            <table>
                <tr>
                    <th>课题编号</th>
                    <th>课题名称</th>
                    <th>学分</th>
                    <th></th>
                    <th></th>
                </tr>
                <%--显示该学生可选择 --%>
                <c:forEach items="${noSelect}" var="ns">
                    <tr>
                        <td>${ns.id}</td>
                        <td>${ns.name}</td>
                        <td>${ns.score}</td>
                    <%--点击以后，向课程选择数据库里添加数据--%>
                        <td><a href="insertCrouseServlet?c_id=${ns.id}&s_id=${user.id}"><button id=btn_${ns.id} class="linkbutton" onclick="handleAddCourseBtnClick('btn_${ns.id}')">添加课程</button></a></td>
                    </tr>
                </c:forEach>
            </table>
        </span>

        <HR style="margin-top: 50px;">

        <%--已选课程菜单栏--%>
        <label style="margin-top: 50px;" class="center-text" >已经选课程</label>
        <span style="margin-top: 50px;">
            <table>
                <tr>
                    <th>课题编号</th>
                    <th>课题名称</th>
                    <th>任课老师</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${sc}" var="s" >
                    <tr>
                        <td>${s.cId}</td>
                        <td>${s.cName}</td>
                        <td>${s.tName}</td>
                        <td><a href="SelectCourseList?id=${s.cId}&page=CourseFile.jsp"><button class="linkbutton">资源</button></a></td>
                    </tr>
                </c:forEach>
            </table>
        </span>



    </div>



    <div id="test" style="display:none" >
        <%

            TaskInfoList taskInfoList = new TaskInfoList();
            // 从考试表选出所有数据
            DataSelect.select(taskInfoList);

            // 两个hash数组
            HashMap<String, ArrayList<Integer>> sla = new HashMap<>();
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
                la.add(taskInfo.getC_id());
                la.add(taskInfo.getTaskDoInfoList().size());
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
                <td><a href="doTask?time=<%=th.get(entry.getKey())%>&sId=${user.id}"><button class="linkbutton">查看详情</button></a></td>
            </tr>
            <%}%>

        </table>
    </div>



    <%
        MessageInfoList messageInfoListTemp = new MessageInfoList();
        DataSelect.select(messageInfoListTemp);
        MessageInfoList messageInfoList = new MessageInfoList();
        HashMap<Integer,String> iname = new HashMap<>();
        for (MessageInfo messageInfo :
                messageInfoListTemp) {
            if (messageInfo.isMyT_id(request)){
                iname.put(messageInfo.getS_id(),messageInfo.getS_name());
            }
        }


        UserInfoList tt = new UserInfoList();
        DataSelect.select(tt);

    %>
    <div id="question" style="display:none">
        <table>
            <tr>
                <th>老师id</th>
                <th>老师姓名</th>
                <th></th>
            </tr>
            <%
                for (Object obj:
                        tt) {
                    UserInfo teacher = (UserInfo) obj;

                    if (teacher.getRoleReal()==1)
                    {
            %>
            <tr>
                <td><%=teacher.getId()%></td>
                <td><%=teacher.getName()%></td>
                <td><a href="ChatRoom.jsp?t_id=<%=teacher.getId()%>&s_id=${user.id}"><button class="linkbutton">发起聊天</button></a></td>
            </tr>
            <%}}%>

        </table>
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
    function handleAddCourseBtnClick(courseId) {
        var btn = document.getElementById(courseId);
        btn.disabled = true;
        btn.innerText = '已添加';
        btn.classList.add('added');
        location.reload();
    }
</script>

<script>
    function showContent(event, contentId) {
        // 阻止链接的默认行为
        event.preventDefault();

        // 隐藏所有内容区域
        var contents = document.getElementsByClassName("content");
        for (var i = 0; i < contents.length; i++) {
            contents[i].style.display = "none";
        }

        // 显示目标内容区域
        var content = document.getElementById(contentId);
        content.style.display = "block";
    }
</script>


<script>
    // 暂时无用，缺少库
    // 发送AJAX请求获取数据
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'getDataServlet');
    xhr.onload = function() {
        if (xhr.status === 200) {
            var data = JSON.parse(xhr.responseText);
            // 将数据动态插入到表格中
            var tableBody = document.querySelector('#myTable tbody');
            data.forEach(function(item) {
                var row = document.createElement('tr');
                var cell = document.createElement('td');
                cell.textContent = item.name;
                row.appendChild(cell);
                tableBody.appendChild(row);
            });
        }
    };
    xhr.send();
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



</body>
</html>


