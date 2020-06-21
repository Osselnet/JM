package servlets.servlet;

import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        UserService userService = UserServiceImpl.getInstance();

        final HttpSession session = req.getSession();

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        if (userService.userIsExist(login, password)) {

            session.setAttribute("userObject", userService.getUserByName(login));

            resp.sendRedirect(req.getContextPath() + "/admin");
        } else {
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
        }
    }
}