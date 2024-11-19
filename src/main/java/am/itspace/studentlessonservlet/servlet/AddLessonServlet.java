package am.itspace.studentlessonservlet.servlet;

import am.itspace.studentlessonservlet.model.Lesson;
import am.itspace.studentlessonservlet.model.User;
import am.itspace.studentlessonservlet.service.LessonService;
import am.itspace.studentlessonservlet.util.DateUtil;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

@WebServlet("/addLesson")
public class AddLessonServlet extends HttpServlet {

    private LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {


            String name = req.getParameter("name");
            Lesson lesson = lessonService.getLessonByName(name);
            User user = (User) req.getSession().getAttribute("user");
            if (lesson == null) {
                lesson = Lesson.builder()
                        .name(name)
                        .duration(DateUtil.fromWebStringToDate(req.getParameter("duration")))
                        .lecturerName(req.getParameter("lecturer_name"))
                        .price(Double.parseDouble(req.getParameter("price")))
                        .user(user)
                        .build();
                lessonService.add(lesson);
                resp.sendRedirect("/lesson");
            } else {
                req.getSession().setAttribute("msg", "Lesson already exists");
                resp.sendRedirect("/addLessons");
            }
        } catch ( ParseException e) {
            e.printStackTrace();
        }

    }
}
