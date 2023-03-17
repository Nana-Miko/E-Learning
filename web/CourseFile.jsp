<%@ page import="java.io.File" %>
<%@ page import="servlet.data.CourseInfo" %>
<%@ page import="servlet.data.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: 11219
  Date: 2023/3/16
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <head>
        <meta charset="UTF-8">
        <title>File List</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <%@ include file="homebtn.html" %>

<body>
<%
    UserInfo user = (UserInfo) request.getSession().getAttribute("user");
    int role = user.getRoleReal();
    CourseInfo courseInfo = (CourseInfo) request.getAttribute("courseInfo");
    String id = String.valueOf(courseInfo.getId());
    String basePath = getServletContext().getRealPath("/file");// 文件夹基本路径
    String dirPath = basePath + File.separator + id;
    File dir = new File(dirPath);

    if (!dir.exists()) {
        dir.mkdirs(); // 如果目录不存在，创建它
    }

    File[] files = dir.listFiles();
%>



<div class="container">
    <div class="jumbotron">
        <h1 class="display-4">课程"${courseInfo.name}"资源</h1>
        <p class="lead">在这里查看课程资源。</p>
    </div>
    <% if (files != null) { %>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>文件名</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <% for (File file : files) { %>
        <tr>
            <td><%= file.getName() %></td>
            <td>
                <a href="DownloadServlet?id=${courseInfo.id}&file=<%= file.getName() %>" class="btn btn-primary">下载</a>
                <% if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png") || file.getName().endsWith(".gif") || file.getName().endsWith(".mp4")) { %>
                <button class="btn btn-primary preview-btn" data-filename="<%= file.getName() %>" data-toggle="modal" data-target="#previewModal">预览</button>
                <% } %>
                <% if (role == 1){%>
                <a href="DeleteServlet?id=${courseInfo.id}&file=<%= file.getName() %>" class="btn btn-danger">删除</a>
                <% } %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>

    <% if (role == 1){%>
    <form action="UploadServlet?id=${courseInfo.id}" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="file">选择文件：</label>
            <input type="file" class="form-control-file" id="file" name="file">
        </div>
        <button type="submit" class="btn btn-primary">上传文件</button>
    </form>
    <% } %>
</div>

<!-- 预览模态窗口 -->
<div class="modal fade" id="previewModal" tabindex="-1" role="dialog" aria-labelledby="previewModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="previewModalLabel">文件预览</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <img id="previewImage" src="" alt="预览图片" class="img-fluid" style="display: none;">
                <video id="previewVideo" width="100%" height="auto" controls style="display: none;">
                    <source src="" type="video/mp4">
                    您的浏览器不支持 HTML5 video 标签。
                </video>
            </div>
        </div>
    </div>
</div>

<script>
    $('.preview-btn').on('click', function() {
        const fileName = $(this).data('filename');
        const filePath = 'file/<%= id %>/' + fileName;
        if (fileName.endsWith('.jpg') || fileName.endsWith('.png') || fileName.endsWith('.gif')) {
            $('#previewImage').attr('src', filePath).show();
            $('#previewVideo').hide();
        } else if (fileName.endsWith('.mp4')) {
            $('#previewImage').hide();
            $('#previewVideo').attr('src', filePath).show();
        }
    });
</script>
</body>
</html>
