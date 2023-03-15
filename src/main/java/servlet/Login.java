package servlet;

import Util.ResponseUtil;
import Util.useDatabase.DataSelect;
import database.Authentication;
import database.JDBC.DBUtil;
import servlet.admin.data.UserInfoList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class Login extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp,req);
        int no = Integer.parseInt(req.getParameter("uname"));
        String psw = req.getParameter("psw");
        Authentication authentication = new Authentication(no,psw);
        if (!authentication.validating()){
            resp.getWriter().println("<script>alert('用户或密码错误!');window.location.href = 'login.html'</script>");
            return;
        }
        String page = "";

        switch (authentication.getRole()){
            case ADMIN -> {page = "AdminHome.jsp";break;}
            case TEACHER -> {page = "TeacherHome.jsp";break;}
            case STUDENT -> {page = "StudentHome.jsp";break;}
        }

        req.getSession().setAttribute("name",authentication.getName());
        req.getRequestDispatcher("SelectUserList?"+"page="+page).forward(req, resp);
    }
}
