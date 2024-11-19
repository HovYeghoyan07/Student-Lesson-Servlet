<%@ page import="am.itspace.studentlessonservlet.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Lesson</title>
</head>
<body>
<h1>STUDENT LESSON
</h1>

<% User user = (User) session.getAttribute("user"); %>
<%if (user != null) { %>
<span>Welcome <%=user.getName()%></span> <a href="/logout">logout</a>
<%} else { %>
<a href="/login">Login</a> <br>


<%}%>

<br/>
<a href="/students">View All Students</a>
<a href="/lessons">View All Lessons</a>
</body>
</html>
