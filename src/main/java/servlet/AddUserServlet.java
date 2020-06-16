package servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;
import util.Utils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add_user")
public class AddUserServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        if (Utils.requestIsValid(req)) {

            final String name = req.getParameter("name");
            final String age = req.getParameter("age");

            final User user = new User();
            user.setAge(Long.parseLong(age));
            user.setName(name);

            userService.addUser(user);
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
