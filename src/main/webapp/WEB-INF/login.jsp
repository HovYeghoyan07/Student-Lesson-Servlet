<%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 19.11.2024
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<%
    if (session.getAttribute("msg") != null) { %>
<h3><%=session.getAttribute("msg")%>
</h3>
<% session.removeAttribute("msg");
}
%>

<form action="/login" method="post">
email:<input type="text" name="email">
password:<input type="password" name="password">
<input type="submit"><br>
</form>
<a href="/register">Register</a>

</body>
</html>
