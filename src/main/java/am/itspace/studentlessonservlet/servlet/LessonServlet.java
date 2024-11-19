package am.itspace.studentlessonservlet.servlet;

import am.itspace.studentlessonservlet.model.Lesson;
import am.itspace.studentlessonservlet.model.User;
import am.itspace.studentlessonservlet.model.UserType;
import am.itspace.studentlessonservlet.service.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lessons")
public class LessonServlet extends HttpServlet {
    private LessonService lessonService=new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Lesson> lessons;
        if (user.getUserType() == UserType.ADMIN) {
            lessons = lessonService.getAllLessons();
            req.setAttribute("lessons", lessons);
            req.getRequestDispatcher("/WEB-INF/lessons.jsp").forward(req, resp);
        } else if (user.getUserType() == UserType.USER){
            lessons = lessonService.getLessonsByUserId(user.getId());
            req.setAttribute("lessons", lessons);
            req.getRequestDispatcher("/WEB-INF/lessons.jsp").forward(req, resp);
        }
    }
}
