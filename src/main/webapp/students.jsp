<%@ page import="am.itspace.studentlessonservlet.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 11.11.2024
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<h1>Students</h1>
<% List<Student> students = (List<Student>) request.getAttribute("students");%>

<a href="/addStudent">Add Student</a>

<table border="1">">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>age</th>
        <th>lesson name</th>
    </tr>
    <% for (Student student : students) { %>
    <tr>
        <td><%=student.getId()%></td>
        <td><%=student.getName()%></td>
        <td><%=student.getSurname()%></td>
        <td><%=student.getEmail()%></td>
        <td><%=student.getAge()%></td>
        <td><%=student.getLesson().getName()%></td>
        <td><a href="/deleteStudent?id=<%= student.getId() %>">Delete</a> / <a href="/editStudent?id=<%= student.getId() %>">Edit</a> </td>

    </tr>
    <% } %>
</table>
</body>
</html>
