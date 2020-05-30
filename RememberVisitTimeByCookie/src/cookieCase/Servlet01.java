package cookieCase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/Servlet01")
public class Servlet01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        boolean flag = true;
        if (cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if("lastTime".equals(cookie.getName())) {
                    flag = false;
                    response.getWriter().write("<h1>Welcome back, your last visit time is " + URLDecoder.decode(cookie.getValue(),"utf-8") + "!</h1>");

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    String str_date = sdf.format(date);
                    System.out.println("Date Format:" + str_date);
                    //URL encode
                    str_date = URLEncoder.encode(str_date,"utf-8");
                    System.out.println("URL Encode:" + str_date);

                    cookie.setValue(str_date);
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(cookie);

                    break;
                }
            }
        }

        if (flag) {
            response.getWriter().write("<h1>Hello, welcome for your first visit!</h1>");

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String str_date = sdf.format(date);
            System.out.println("Date Format:" + str_date);
            //URL encode
            str_date = URLEncoder.encode(str_date,"utf-8");
            System.out.println("URL Encode:" + str_date);

            Cookie cookie = new Cookie("lastTime", str_date);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
        }
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
