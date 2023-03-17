package servlet.api;

import Util.ResponseUtil;
import Util.useDatabase.DataSelect;
import servlet.data.MessageInfo;
import servlet.data.MessageInfoList;
import servlet.data.UserInfo;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FetchMessage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        MessageInfoList messages = new MessageInfoList();
        DataSelect.select(messages);

        // Filter messages based on the current user's role
        List<MessageInfo> filteredMessages = messages.stream()
                .filter(msg -> msg.isMyT_id(req) || msg.isMyS_id(req))
                .collect(Collectors.toList());

        // Convert messages to JSON and send as the response
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        JSONArray jsonArray = new JSONArray(filteredMessages);
        resp.getWriter().write(jsonArray.toString());
    }
}
