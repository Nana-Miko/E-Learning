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


    </style>
</head>
<body>
<div id="header">
    <h1>欢迎您，<span>${user.name}同学  ${user.id}</span></h1>
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
        <div>
            <table>
                <tr>
                    <th>课题编号</th>
                    <th>课题名称</th>
                    <th>学分</th>
                    <th>已选人数</th>
                    <th></th>
                    <th></th>
                </tr>
                <%--显示所有课程 --%>
                <c:forEach items="${courseInfoList}" var="courseInfo">
                    <tr>
                        <td>${courseInfo.id}</td>
                        <td>${courseInfo.name}</td>
                        <td>${courseInfo.score}</td>
                        <td>${courseInfo.count}</td>
<%--                       点击以后，向课程选择数据库里添加数据--%>
                        <td><a href="selectCrouseServlet?c_id=${courseInfo.id}&s_id=${user.id}"><button id=btn_${courseInfo.id} class="linkbutton" onclick="handleAddCourseBtnClick('btn_${courseInfo.id}')">添加课程</button></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <script>
            function handleAddCourseBtnClick(courseId) {
                var btn = document.getElementById(courseId);
                btn.disabled = true;
                btn.innerText = '已添加';
                btn.classList.add('added');
                location.reload();
            }
        </script>
        <label style="margin-top: 50px;" class="center-text" >已经选课程</label>

        <div style="margin-top: 50px;">
            <table>
                <tr>
                    <th>课题编号</th>
                    <th>课题名称</th>
                    <th>学分</th>
                </tr>
                <c:forEach items="" var="courseInfo">
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><a href="SelectCourseList?id=${courseInfo.id}&page=CourseFile.jsp"><button class="linkbutton">资源</button></a></td>
                        <td><a href="SelectCourseList?id=${courseInfo.id}&page=UpdateCourse.jsp"><button class="linkbutton">修改</button></a></td>
                    </tr>
                </c:forEach>

            </table>
        </div>





    </div>
    <div id="test"style="display:none">
       考试
    </div>
    <div id="question" style="display:none">
        问题
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


