package servlet.student;

import Util.ResponseUtil;
import Util.useDatabase.DataInsert;
import servlet.data.StudentClass;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 向数据库中写入课程号与对应的学生号
public class InsertCrouseServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);

        int c_id =Integer.parseInt( req.getParameter("c_id"));
        System.out.println(c_id);

        int s_id =Integer.parseInt( req.getParameter("s_id"));
        System.out.println(s_id);

        StudentClass studentClass = new StudentClass();

        studentClass.setC_id(c_id);
        studentClass.setS_id(s_id);

        DataInsert.insert(studentClass);

        req.getRequestDispatcher("SelectCourseList?page=selectCrouse").forward(req, resp);

//        resp.sendRedirect("StudentHome.jsp");


    }
}
