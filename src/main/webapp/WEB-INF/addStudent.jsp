<%@ page import="am.itspace.studentlessonservlet.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="am.itspace.studentlessonservlet.model.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 14.11.2024
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add Student</title>
</head>
<body>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons"); %>
<h1>Add Student</h1>
<a href="/students.jsp">Students Page</a> | <a href="index.jsp">Main</a> <br>

<form action="/addStudent" method="post">
  Name: <input type="text" name="name"><br>
  Surname: <input type="text" name="surname"><br>
  Email: <input type="text" name="email"><br>
  Age: <input type="number" name="age"><br>
  Lesson: <select name="lesson_id">
  <% for (Lesson lesson : lessons) { %>
  <option value="<%=lesson.getId()%>"><%=lesson.getName()%>
  </option>
  <% }%>
</select><br>
  <input type="submit" value="ADD">

</form>

</body>
</html>
