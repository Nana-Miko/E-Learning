package servlet.admin;

import Util.ResponseUtil;
import Util.useDatabase.DataSelect;
import servlet.admin.data.UserInfo;
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
        String id = req.getParameter("id");
        UserInfoList userInfoList =  DataSelect.select(new UserInfoList());
        if (id == null){
            req.setAttribute("userinfoList",userInfoList);
        }
        else {
            for (Object obj :
                    userInfoList) {
                UserInfo userInfo = (UserInfo) obj;
                if (String.valueOf(userInfo.getId()).equals(id)){
                    req.setAttribute("userinfo",userInfo);
                    break;
                }
            }
        }

        req.getRequestDispatcher(page).forward(req, resp);
    }
}
