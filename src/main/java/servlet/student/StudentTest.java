package servlet.student;

import Util.ResponseUtil;
import Util.useDatabase.DataInsert;
import Util.useDatabase.DataSelect;
import servlet.data.TaskDoInfo;
import servlet.data.TaskInfo;
import servlet.data.TaskInfoList;
import servlet.data.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 存入学生的答题情况
public class StudentTest extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        UserInfo userInfo = (UserInfo) req.getSession().getAttribute("user");
        int s_id = userInfo.getId();
        String sName = userInfo.getName();
        int score = 0;
        int count = 0;

        int length = Integer.parseInt(req.getParameter("length"));
        int cid = Integer.parseInt((req.getParameter("cid")));
        Long time = Long.valueOf(req.getParameter("tTime"));
        Long ftime = System.currentTimeMillis();


        // 依次判断
        for (int i = 1;i<=length; i++)
        {
            String StudentCheck = req.getParameter("q"+i );
            String right = req.getParameter("r"+i);
            if(StudentCheck.equals(right) )
            {
                score++;
            }
            count++;
        }

        String bili = score + "/" + count;

        // 存入学生测试信息
        TaskDoInfo TI = new TaskDoInfo();

        TI.setS_id(s_id);
        TI.setS_name(sName);
        TI.setC_id(cid);
        TI.setScore(bili);
        TI.setTime(time);
        TI.setFinish_time(ftime);
        DataInsert.insert(TI);



        resp.getWriter().println("<script>alert('你的正确率是 " +bili+  "');window.location.href = 'SelectCourseList?page=selectCrouse'</script>");


        System.out.println("你的正确率是" + bili);

    }
}
