package servlets.servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;
import util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {

    private User user;

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        final String name = req.getParameter("name");

        user.setName(name);
        userService.update(user);

        resp.sendRedirect(req.getContextPath() + "/admin");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (Utils.idIsNumber(req)) {
            user = userService.getUserById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("userObject", user);
        }
        req.getRequestDispatcher("/WEB-INF/view/update.jsp")
                .forward(req, resp);
    }
}
