<%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 19.11.2024
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>
<span>
    <%if (request.getAttribute("msg") != null) {%>
        <h3><%request.getAttribute("msg");%></h3>
    <%}%>
</span>

<form action="/register" method="post">
name:<input type="text" name="name"><br>
surname:<input type="text" name="surname"><br>
email:<input type="text" name="email"><br>
password:<input type="password" name="password"><br>
user type:<select name="user_type">
    <option value="USER">User</option>
    <option value="ADMIN">Admin</option>
</select><br>
<input type="submit">
</form>
</body>
</html>
