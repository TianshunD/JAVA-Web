package download;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //字节流读取文件
        String filename = request.getParameter("filename");
        ServletContext servletContext = this.getServletContext();
        String path = servletContext.getRealPath("/img/" + filename);
        FileInputStream fis = new FileInputStream(path);

        //设置res响应头
        response.setHeader("content-type",servletContext.getMimeType(filename));
        response.setHeader("content-disposition","attachment;filename=" + filename); //"attachment;filename"固定写法，+filename弹框显示信息

        //字节输出流输出
        ServletOutputStream os = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            os.write(bytes,0,len);
        }

        fis.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
