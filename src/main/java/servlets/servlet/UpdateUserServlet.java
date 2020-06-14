package servlets.servlet;

import model.User;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        final String name = req.getParameter("name");

        user.setName(name);
        UserServiceImpl.getInstance().update(user);

        resp.sendRedirect(req.getContextPath() + "/admin");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (Utils.idIsNumber(req)) {
            user = UserServiceImpl.getInstance().getUserById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("/WEB-INF/view/update.jsp")
                .forward(req, resp);
    }
}
