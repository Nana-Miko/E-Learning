package servlet.teacher;

import Util.ResponseUtil;
import Util.useDatabase.DataSelect;
import servlet.data.TaskInfo;
import servlet.data.TaskInfoList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SeeTask extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        int c_id = Integer.parseInt(req.getParameter("c_id"));
        long time = Long.parseLong(req.getParameter("time"));

        TaskInfoList taskInfoListTemp = new TaskInfoList();

        TaskInfoList taskInfoList = new TaskInfoList();


        DataSelect.select(taskInfoListTemp);
        for (TaskInfo taskInfo:
             taskInfoListTemp) {
            if (taskInfo.getC_id()==c_id&&taskInfo.getTime()==time){
                taskInfoList.add(taskInfo);
            }
        }

        req.setAttribute("taskInfoList",taskInfoList);

        req.getRequestDispatcher("SeeTask.jsp").forward(req, resp);


    }
}
