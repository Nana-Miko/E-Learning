package servlet.teacher;

import Util.ResponseUtil;
import Util.useDatabase.DataSelect;
import servlet.data.CourseInfo;
import servlet.data.CourseInfoList;
import servlet.data.UserInfo;
import servlet.data.UserInfoList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentSelectCourseList extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);


        // 获取参数
        String page = req.getParameter("page");
        // 获取id
        String id = req.getParameter("id");
        // 从数据库中选择出数据，然后放在数组里
        CourseInfoList courseInfoList =  DataSelect.select(new CourseInfoList());

        if (id == null){
            req.setAttribute("courseInfoList",courseInfoList);
        }
        else {
            for (Object obj :
                    courseInfoList) {
                CourseInfo courseInfo = (CourseInfo) obj;
                if (String.valueOf(courseInfo.getId()).equals(id)){
                    req.setAttribute("courseInfo",courseInfo);
                    break;
                }
            }
        }


        req.getRequestDispatcher(page).forward(req, resp);
    }
}