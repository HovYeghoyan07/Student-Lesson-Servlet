package am.itspace.studentlessonservlet.servlet;


import am.itspace.studentlessonservlet.model.Student;
import am.itspace.studentlessonservlet.model.User;
import am.itspace.studentlessonservlet.model.UserType;
import am.itspace.studentlessonservlet.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Student> students;
        if (user.getUserType() == UserType.ADMIN) {
            students = studentService.getAllStudents();
            req.setAttribute("students", students);
            req.getRequestDispatcher("/WEB-INF/students.jsp").forward(req, resp);
        } else if (user.getUserType() == UserType.USER){
            students = studentService.getStudentsByUserId(user.getId());
            req.setAttribute("students", students);
            req.getRequestDispatcher("/WEB-INF/students.jsp").forward(req, resp);
        }
    }
}