package am.itspace.studentlessonservlet.servlet;

import am.itspace.studentlessonservlet.model.Lesson;
import am.itspace.studentlessonservlet.service.LessonService;
import am.itspace.studentlessonservlet.util.DateUtil;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editLesson")
public class EditLessonServlet extends HttpServlet {
    private LessonService lessonService=new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lessonId = Integer.parseInt(req.getParameter("id"));
        Lesson lesson = lessonService.getLessonById(lessonId);
        req.setAttribute("lesson", lesson);
        req.getRequestDispatcher("/editLesson.jsp").forward(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String duration = req.getParameter("duration");
        String lecturerName = req.getParameter("lecturer_name");
        Double price = Double.parseDouble(req.getParameter("price"));

        Lesson lesson = Lesson.builder()
                .id(Integer.parseInt(id))
                .name(name)
                .duration(DateUtil.fromWebStringToDate(duration))
                .lecturerName(lecturerName)
                .price(price)
                .build();

        lessonService.update(lesson);

        resp.sendRedirect("/lessons");

    }
}
