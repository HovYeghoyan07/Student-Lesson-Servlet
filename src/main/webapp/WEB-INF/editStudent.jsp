<%@ page import="am.itspace.studentlessonservlet.model.Student" %><%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 14.11.2024
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Student</title>
</head>
<body>
<% Student student = (Student) request.getAttribute("student");%>
<h1>Edit Student</h1>
<a href="/students">Students</a> | <a href="index.jsp">Main</a>

<form action="/editStudent" method="post">
  <input type="hidden" name="id" value="<%=student.getId()%>"> <br>
  Name: <input type="text" name="name" value="<%=student.getName()%>"><br>
  Surname: <input type="text" name="surname" value="<%=student.getSurname()%>"><br>
  Email: <input type="text" name="email" value="<%=student.getEmail()%>"><br>
  Age: <input type="number" name="age" value="<%=student.getAge()%>"><br>

<br>
  <input type="submit" value="UPDATE">

</form>
</body>
</html>
