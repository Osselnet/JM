package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/")
public class GetIndexPageServlet extends HttpServlet {

    UserService userService = new UserService();

    private Map<Integer, User> users;

    @Override
    public void init() throws ServletException {

        final Object users = getServletContext().getAttribute("users");

        if (users == null || !(users instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        } else {

            this.users = (ConcurrentHashMap<Integer, User>) users;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //req.setAttribute("users", users.values());
        req.setAttribute("users",  userService.getAllUser());
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }
}