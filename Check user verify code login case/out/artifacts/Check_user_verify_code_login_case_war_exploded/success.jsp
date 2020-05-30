<%--
  Created by IntelliJ IDEA.
  User: 10492
  Date: 2020/5/5
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h1>Welcome, <%=request.getSession().getAttribute("user")%></h1>

</body>
</html>
