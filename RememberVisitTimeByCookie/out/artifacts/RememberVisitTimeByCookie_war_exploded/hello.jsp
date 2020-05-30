<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: 10492
  Date: 2020/5/5
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <%
        Cookie[] cookies = request.getCookies();
        boolean flag = true;
        if (cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if("lastTime".equals(cookie.getName())) {
                    flag = false;
                    out.write("<h1>Welcome back, your last visit time is " + URLDecoder.decode(cookie.getValue(),"utf-8") + "!</h1>");

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
            out.write("<h1>Hello, welcome for your first visit!</h1>");

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
    %>

</body>
</html>
