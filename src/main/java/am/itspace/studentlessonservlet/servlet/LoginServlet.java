package am.itspace.studentlessonservlet.servlet;

import am.itspace.studentlessonservlet.model.User;
import am.itspace.studentlessonservlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email =req.getParameter("email");
        String password = req.getParameter("password");

        User user = userService.getUserByEmailAndPassword(email, password);
        HttpSession session = req.getSession();

        if (user != null) {
            session.setAttribute("user", user);
            resp.sendRedirect("/");
        } else {
            session.setAttribute("msg", "Username or Password is incorrect!");
            resp.sendRedirect("/login");
        }

    }
}
