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
import java.text.ParseException;
import java.util.Date;

@WebServlet("/addLesson")
public class AddLessonServlet extends HttpServlet {

    private LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addLesson.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String name = req.getParameter("name");
            Date duration = DateUtil.fromWebStringToDate(req.getParameter("duration"));
            String lecturerName = req.getParameter("lecturer_name");
            Double price = Double.parseDouble(req.getParameter("price"));
            System.out.println(duration);
            Lesson lesson = Lesson.builder()
                    .name(name)
                    .duration(duration)
                    .lecturerName(lecturerName)
                    .price(price)
                    .build();

            lessonService.add(lesson);
        }catch (ParseException e){
            e.printStackTrace();
        }


        resp.sendRedirect("/lessons");

    }


}
