package servlet.student;

import Util.ResponseUtil;
import Util.useDatabase.DataInsert;
import Util.useDatabase.DataSelect;
import servlet.data.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// 根据学生学号，获取到对应的课程号，并从另一张表中取出该课程所对应的所有信息
public class SelectCrouse extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ResponseUtil.setCharset(resp, req);
        // 学号

        UserInfo userInfo = (UserInfo) req.getSession().getAttribute("user");
        int s_id = userInfo.getId();

        // 查询出该学生所选择的所有课程，并推到前端
        req.setAttribute("sc",DataSelect.select(s_id,new SelectStudentCourseList()));
        // 查询出该学生没有选择的课
        req.setAttribute("noSelect",DataSelect.select(s_id,new NotCourseInfoList()));

        System.out.println(DataSelect.select(s_id,new SelectStudentCourseList()));
        req.getRequestDispatcher("StudentHome.jsp").forward(req,resp);




//        // 将数据转换为JSON格式
//        Gson gson = new Gson();
//        String json = gson.toJson(dataList);
//
//        // 设置响应类型为JSON
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        // 将JSON数据写入响应输出流中
//        PrintWriter out = response.getWriter();
//        out.print(json);
//        out.flush();







    }
}
