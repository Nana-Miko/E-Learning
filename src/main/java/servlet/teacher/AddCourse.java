package servlet.teacher;

import Util.ResponseUtil;
import Util.useDatabase.DataInsert;
import servlet.data.CourseInfo;
import servlet.data.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCourse extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        // 获取用户
        UserInfo userInfo = (UserInfo)req.getSession().getAttribute("user");
        // 实例化课程对象
        CourseInfo courseInfo = new CourseInfo();
        // 设置课程名
        courseInfo.setName(req.getParameter("course-name"));
        // 设置学分
        courseInfo.setScore(Integer.parseInt(req.getParameter("course-credits")));
        // 获取老师的ID并设置
        courseInfo.setT_id(userInfo.getId());
        ResponseUtil.checkError(DataInsert.insert(courseInfo),resp,"课题发布成功！","课题发布失败！","SelectCourseList?page=TeacherHome.jsp");
    }
}
