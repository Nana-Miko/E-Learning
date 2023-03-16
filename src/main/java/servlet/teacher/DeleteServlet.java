package servlet.teacher;
import Util.ResponseUtil;

import java.io.*;
import javax.servlet.*;

import javax.servlet.http.*;


public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseUtil.setCharset(response,request);

        String id = request.getParameter("id");
        String fileName = request.getParameter("file");
        String filePath = getServletContext().getRealPath("") + File.separator + "file" + File.separator + id + File.separator + fileName;
        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }

        response.sendRedirect("SelectCourseList?id="+id+"&page=CourseFile.jsp"); // 重定向到文件列表页面
    }
}
