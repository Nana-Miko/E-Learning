package servlet.teacher;
import Util.ResponseUtil;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseUtil.setCharset(response,request);

        String id = request.getParameter("id");
        String fileName = request.getParameter("file");
        String filePath = getServletContext().getRealPath("") + File.separator + "file" + File.separator + id + File.separator + fileName;
        File file = new File(filePath);

        response.setContentType("application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        InputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }

        in.close();
        out.flush();
    }
}

