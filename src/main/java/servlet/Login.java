package servlet;

import Util.ResponseUtil;
import database.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp,req);
        String no = req.getParameter("uname");
        String psw = req.getParameter("psw");
        Authentication authentication = new Authentication(no,psw);
        if (!authentication.validating()){
            resp.getWriter().println("<script>alert('用户或密码错误!');window.location.href = 'login.html'</script>");
            return;
        }
        String page = "";

        switch (authentication.getRole()){
            case ADMIN -> {page = "SelectUserList?page=AdminHome.jsp";break;}
            case TEACHER -> {page = "SelectCourseList?page=TeacherHome.jsp";break;}
            case STUDENT -> {page = "SelectCourseList?page=selectCrouse";break;}
        }
        req.getSession().setAttribute("homePage",page);
        req.getSession().setAttribute("user",authentication.userInfo);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
