package am.itspace.studentlessonservlet.servlet;


import am.itspace.studentlessonservlet.model.Student;
import am.itspace.studentlessonservlet.service.StudentService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {

    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("id"));
        Student student = studentService.getStudentById(studentId);
        req.setAttribute("student", student);
        req.getRequestDispatcher("/WEB-INF/editStudent.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));

        Student student = Student.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .age(age)
                .build();

        studentService.update(student);


        resp.sendRedirect("/students");
    }
}
