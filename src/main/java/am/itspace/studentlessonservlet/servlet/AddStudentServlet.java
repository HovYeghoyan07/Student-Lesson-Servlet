package am.itspace.studentlessonservlet.servlet;



import am.itspace.studentlessonservlet.model.Lesson;
import am.itspace.studentlessonservlet.model.Student;
import am.itspace.studentlessonservlet.service.LessonService;
import am.itspace.studentlessonservlet.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {

    private LessonService lessonService = new LessonService();
    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonService.getAllLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/addStudent.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        int lessonId = Integer.parseInt(req.getParameter("lesson_id")) ;

        Student student  = Student.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .age(age)
                .lesson(lessonService.getLessonById(lessonId))
                .build();

        studentService.add(student);



        resp.sendRedirect("/students");
    }
}
