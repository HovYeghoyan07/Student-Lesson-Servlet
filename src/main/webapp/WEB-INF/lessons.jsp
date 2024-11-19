<%@ page import="java.util.List" %>
<%@ page import="am.itspace.studentlessonservlet.model.Lesson" %>
<%@ page import="am.itspace.studentlessonservlet.util.DateUtil" %>

<%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 11.11.2024
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Lessons
    </title>
</head>
<body>
<h2>Lessons:</h2>
<a href="/addLesson"> Add Lesson</a>

<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons"); %>

<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>duration</th>
        <th>lecturer_name</th>
        <th>price</th>
        <th>action</th>
    </tr>

    <% for (Lesson lesson : lessons) { %>
    <tr>
        <td><%= lesson.getId() %></td>
        <td><%= lesson.getName() %></td>
        <td><%= DateUtil.fromDateToString(lesson.getDuration()) %></td>
        <td><%= lesson.getLecturerName() %></td>
        <td><%= lesson.getPrice() %></td>
        <td><a href="/deleteLesson?id=<%= lesson.getId() %>">Delete</a> / <a href="/editLesson?id=<%= lesson.getId() %>">Edit</a> </td>
    </tr>
    <% } %>
</table>
</body>
</html>