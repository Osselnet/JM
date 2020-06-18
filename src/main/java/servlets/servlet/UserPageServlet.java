package servlets.servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user")
public class UserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/view/user_menu.jsp").forward(req, resp);
    }
}
