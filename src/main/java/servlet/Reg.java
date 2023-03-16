package servlet;

import Util.ResponseUtil;
import database.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Reg extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        Authentication authentication = new Authentication(req.getParameter("uname"),req.getParameter("psw"));
        authentication.role = Role.STUDENT;
        authentication.name = req.getParameter("name");
        if (authentication.insert()){
            resp.getWriter().println("<script>alert('注册成功!');window.location.href = 'login.html'</script>");
        }
        else {
            resp.getWriter().println("<script>alert('注册失败!');window.location.href = 'login.html'</script>");
        }
    }
}
