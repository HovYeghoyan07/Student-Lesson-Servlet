<%@ page import="am.itspace.studentlessonservlet.model.Lesson" %>
<%@ page import="am.itspace.studentlessonservlet.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 14.11.2024
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Lesson</title>
</head>
<body>
<% Lesson lesson = (Lesson) request.getAttribute("lesson");%>
<h1>Edit Lesson</h1>
<a href="/lessons">Lessons</a> | <a href="index.jsp">Main</a>

<form action="/editLesson" method="post">
  <input type="hidden" name="id" value="<%=lesson.getId()%>"> <br>
  Name: <input type="text" name="name" value="<%=lesson.getName()%>"><br>
  Duration: <input type="date" name="duration"
                           value="<%=DateUtil.fromDateToWebString(lesson.getDuration())%>"><br>
  Lecturer_Name: <input type="text" name="lecturer_name" value="<%=lesson.getLecturerName()%>"><br>
  Price: <input type="number" name="price" value="<%=lesson.getPrice()%>"><br>
  <input type="submit" value="UPDATE">

</form>
</body>
</html>
