package servlets.servlet;

import service.UserService;
import service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        UserService userService = UserServiceImpl.getInstance();

        final HttpSession session = req.getSession();

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        if (userService.userIsExist(login, password)) {

            final String role = userService.getRoleByLoginPassword(login, password);

            session.setAttribute("password", password);
            session.setAttribute("login", login);
            session.setAttribute("role", role);
            resp.sendRedirect(req.getContextPath() + "/admin");
        }
            resp.sendRedirect(req.getContextPath() + "/");
    }
}