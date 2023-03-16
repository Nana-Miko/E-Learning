package Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ResponseUtil {
    public static void setCharset(HttpServletResponse resp, HttpServletRequest req){
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void checkError(boolean bol,HttpServletResponse resp,String rightLog,String errorLog,String hrefUrl) throws IOException {
        if (bol){
            resp.getWriter().println("<script>alert('"+rightLog+"');window.location.href = '"+hrefUrl+"'</script>");
        }
        else {
            resp.getWriter().println("<script>alert('"+errorLog+"');window.location.href = '"+hrefUrl+"'</script>");
        }
    }
}
