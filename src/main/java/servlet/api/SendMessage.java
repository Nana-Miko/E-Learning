package servlet.api;

import Util.ResponseUtil;
import Util.useDatabase.DataInsert;
import Util.useDatabase.DataSelect;
import servlet.data.MessageInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendMessage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setCharset(resp, req);
        String message = req.getParameter("message");

        if (message != null && !message.trim().isEmpty()) {
            // Get the sender ID and receiver ID from the session or request
            // In this example, we assume they are passed as parameters
            int s_id = Integer.parseInt(req.getParameter("s_id"));
            int t_id = Integer.parseInt(req.getParameter("t_id"));

            // Create a new MessageInfo object
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setS_id(s_id);
            messageInfo.setT_id(t_id);
            messageInfo.setMessage(message);
            if (messageInfo.isMyS_id(req)){
                messageInfo.setSend(2);
            }
            if (messageInfo.isMyT_id(req)){
                messageInfo.setSend(1);
            }

            // Save the message using DataSelect static class
            // You may need to implement the 'insert' method for the DataSelect class
            DataInsert.insert(messageInfo);
        }
    }
}
