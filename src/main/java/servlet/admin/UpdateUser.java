package servlet.admin;

import Util.ResponseUtil;
import Util.useDatabase.DataDelete;
import Util.useDatabase.DataUpdate;
import servlet.admin.data.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

public class UpdateUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Integer.parseInt(req.getParameter("id")));
        userInfo.setNo(Integer.parseInt(req.getParameter("username")));
        userInfo.setPsw(req.getParameter("password"));
        userInfo.setName(req.getParameter("name"));
        userInfo.setRole(Integer.parseInt(req.getParameter("type")));


        if (DataUpdate.update(userInfo)){
            resp.getWriter().println("<script>alert('修改成功!');window.location.href = 'SelectUserList?page=AdminHome.jsp'</script>");
        }
        else {
            resp.getWriter().println("<script>alert('修改失败!');window.location.href = 'SelectUserList?page=AdminHome.jsp'</script>");
        }
    }
}
