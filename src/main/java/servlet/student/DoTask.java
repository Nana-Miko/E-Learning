package servlet.student;


import Util.ResponseUtil;
import Util.useDatabase.DataSelect;
import servlet.data.StudentTask;
import servlet.data.StudentTaskList;
import servlet.data.TaskDoInfo;
import servlet.data.TaskDoInfoList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 根据传入进来的时间，去数据库寻找数据，然后返回给前端
// 点击按钮后，先去Task_do表格里查看是否已经有成绩，如果有成绩，则直接跳转到成绩页面，如果没成绩就跳转到考试页面
public class DoTask extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        // 时间标志
        long time = Long.parseLong(req.getParameter("time"));
        // 用户id
        int sid = Integer.parseInt(req.getParameter("sId"));

        // 根据时间表示去taskdo表格里查询，如果差到了结果，就把成绩输出来

        TaskDoInfo TDI = new TaskDoInfo();


        // 如果已经有成绩了，就显示该学生成绩
        DataSelect.select(sid,time,TDI);
        req.setAttribute("studenttest",TDI);

        // 查询考题详情
        StudentTaskList stl = new StudentTaskList();
        DataSelect.select(time,stl);
        req.setAttribute("StudentTL",stl);


        if(TDI.getScore() != null)
        {
            req.getRequestDispatcher("TaskOver.jsp").forward(req, resp);
        }else {
            req.getRequestDispatcher("Test.jsp").forward(req, resp);
        }


    }
}
