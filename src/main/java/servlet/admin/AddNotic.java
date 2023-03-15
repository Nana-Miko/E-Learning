package servlet.admin;

import Util.ResponseUtil;
import Util.useDatabase.DataInsert;
import Util.useDatabase.DataUpdate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNotic extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        String notic = req.getParameter("notic");
        if (DataInsert.insert(notic)){
            resp.getWriter().println("<script>alert('发布成功!');window.location.href = 'SelectUserList?page=AdminHome.jsp'</script>");
        }
        else {
            resp.getWriter().println("<script>alert('发布失败!');window.location.href = 'SelectUserList?page=AdminHome.jsp'</script>");
        }
    }
}
