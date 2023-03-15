package servlet.admin;

import Util.ResponseUtil;
import Util.useDatabase.DataSelect;
import database.Authentication;
import servlet.Role;
import servlet.admin.data.UserInfoList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        Authentication authentication = new Authentication(new Integer(req.getParameter("username")),req.getParameter("password"));
        authentication.name = req.getParameter("name");
        System.out.println(req.getParameter("type"));
        switch (req.getParameter("type")){
            case "teacher":
                authentication.role = Role.TEACHER;
                break;
            case "student":
                authentication.role = Role.STUDENT;
                break;
        }
        if (authentication.insert()){
            resp.getWriter().println("<script>alert('添加成功!');window.location.href = 'SelectUserList?page=AdminHome.jsp'</script>");
        }
        else {
            resp.getWriter().println("<script>alert('添加失败!');window.location.href = 'SelectUserList?page=AdminHome.jsp'</script>");
        }


    }
}
