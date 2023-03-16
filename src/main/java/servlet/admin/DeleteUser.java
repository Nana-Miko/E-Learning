package servlet.admin;

import Util.ResponseUtil;
import Util.useDatabase.DataDelete;
import servlet.data.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        if (DataDelete.delete(Integer.parseInt(req.getParameter("id")),new UserInfo())){
            resp.getWriter().println("<script>alert('删除成功!');window.location.href = 'SelectUserList?page=AdminHome.jsp'</script>");
        }
        else {
            resp.getWriter().println("<script>alert('删除失败!');window.location.href = 'SelectUserList?page=AdminHome.jsp'</script>");
        }


    }
}
