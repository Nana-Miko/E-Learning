package servlet.teacher;

import Util.ResponseUtil;
import Util.useDatabase.DataUpdate;
import servlet.data.CourseInfo;
import servlet.data.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCourse extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);

        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setId(Integer.parseInt(req.getParameter("id")));
        courseInfo.setName(req.getParameter("name"));
        courseInfo.setScore(Integer.parseInt(req.getParameter("score")));
        courseInfo.setT_id(Integer.parseInt(req.getParameter("t_id")));


        if (DataUpdate.update(courseInfo)){
            resp.getWriter().println("<script>alert('修改成功!');window.location.href = 'SelectCourseList?page=TeacherHome.jsp'</script>");
        }
        else {
            resp.getWriter().println("<script>alert('修改失败!');window.location.href = 'SelectCourseList?page=TeacherHome.jsp'</script>");
        }
    }
}
