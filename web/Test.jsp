<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="servlet.data.StudentTaskList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%--学生答题页面，需要根据学生点击的对应的时间戳，来确定具体是哪个测试，然后将其显示在这里--%>
<head>
    <meta charset="UTF-8">
    <title>考试页面</title>
    <link rel="stylesheet" type="text/css" href="style.css">


    <style>
        /* 设置表格边框样式 */
        table {
            border-collapse: collapse;
            width: 100%;
        }

        /* 设置表格行的鼠标悬停样式 */
        tr:hover {
            background-color: #f5f5f5;
        }

        /* 设置单元格内的文本对齐方式 */
        td {
            text-align: left;
            padding: 8px;
        }

        /* 设置提交按钮的样式 */
        button {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        /* 设置单选框和答案之间的间距 */
        input[type="radio"] {
            margin-right: 10px;
        }
    </style>

</head>
<body>


<h1>选择题测试</h1>


<%
    StudentTaskList x = (StudentTaskList) request.getAttribute("StudentTL");
    int num = 1;
%>
本考试为<%=x.get(0).getcName()%>考试，请诚信作答
<form action="studentTest?tTime=<%=x.get(0).getTime()%>&cid=<%=x.get(0).getcId()%>&length=<%=x.size()%>"  method="post">
    <div>
    <table>
        <c:forEach items="${StudentTL}" var="st" >

        <tr>
            <td>${st.qName}</td>
            <td>
                <label>
                <input type="radio" name=q<%=num%> value="A">A.${st.qA}
                </label>

                <label>
                <input type="radio" name=q<%=num%> value="B">B.${st.qB}
                </label>

                <label>
                <input type="radio" name=q<%=num%> value="C">C.${st.qC}
                </label>

                <label>
                <input type="radio" name=q<%=num%> value="D">D.${st.qD}
                </label>
                <%--用于传递正确答案--%>
                <input style="display: none"  name=r<%=num%> value="${st.qRight}"></input>
            </td>
        </tr>
            <%
                num++;
            %>>
        </c:forEach>
    </table>
    </div>
    <button type="submit">提交</button>
</form>

<%--显示得分--%>
<div id="result"></div>
<div id="result1"></div>

<%--<script src="script.js"></script>--%>

</script>
</body>
</html>
