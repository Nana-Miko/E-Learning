package servlet.admin;

import Util.ResponseUtil;
import Util.useDatabase.DataSelect;
import servlet.admin.data.UserInfoList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SelectUserList extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        String page = req.getParameter("page");
        UserInfoList userInfoList =  DataSelect.select(new UserInfoList());
        req.setAttribute("userinfoList",userInfoList);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
