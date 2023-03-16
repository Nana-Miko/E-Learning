package servlet.teacher;

import Util.ResponseUtil;
import Util.useDatabase.DataInsert;
import servlet.data.TaskInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTask extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        int count = Integer.parseInt(req.getParameter("question-count"));
        int error = 0;
        long time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            TaskInfo taskInfo = new TaskInfo();
            taskInfo.setC_id(Integer.parseInt(req.getParameter("c_id")));
            taskInfo.setName(req.getParameter("question-"+i+"-name"));
            taskInfo.setA(req.getParameter("question-" + i + "-answer-a"));
            taskInfo.setB(req.getParameter("question-"+i+"-answer-b"));
            taskInfo.setC(req.getParameter("question-"+i+"-answer-c"));
            taskInfo.setD(req.getParameter("question-"+i+"-answer-d"));
            taskInfo.setRight(req.getParameter("question-"+i+"-correct-answer"));
            taskInfo.setTime(time);
            if (!DataInsert.insert(taskInfo)){
                error++;
            }
        }

        resp.getWriter().println("<script>alert('添加题目完毕!个"+error+"添加错误');window.location.href = 'SelectCourseList?page=TeacherHome.jsp'</script>");
    }
}
