<%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 11.11.2024
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Lesson</title>
</head>
<body>
<h1>Add Lesson</h1>
<a href="/lessons">Lessons</a>  <br>

<form action="/addLesson" method="post">
    Name: <input type="text" name="name"><br>
    Duration: <input type="date" name="duration"><br>
    Lecturer_Name: <input type="text" name="lecturer_name"><br>
    Price: <input type="number" name="price"><br>
    <input type="submit" value="ADD">

</form>

</body>
</html>