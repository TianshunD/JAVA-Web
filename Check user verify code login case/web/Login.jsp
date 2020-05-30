<%--
  Created by IntelliJ IDEA.
  User: 10492
  Date: 2020/5/5
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>

    <script>
        window.onload = function () {
            document.getElementById("img").onclick = function () {
                this.src = "/verify/checkImg?time=" + new Date().getTime();
            }
        }
    </script>

    <style>
        div {
            color: red;
        }
    </style>

</head>
<body>
    <form action="/verify/loginServlet" method="post">
        <table>
            <tr>
                <td>Username</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>CheckCode</td>
                <td><input type="text" name="checkCode" placeholder="case not sensitive"></td>
            </tr>
            <tr>
                <td colspan="2"><img src="/verify/checkImg" id="img"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="submit"></td>
            </tr>
        </table>
    </form>
    <div><%
        if (request.getAttribute("user_error")!=null) {
            out.write(request.getAttribute("user_error").toString());
        }
                %></div>
    <div><%
        if (request.getAttribute("cc_error")!=null) {
            out.write(request.getAttribute("cc_error").toString());
        }
    %></div>

</body>
</html>
