package servlets.filter;

import service.UserService;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/")
public class AuthFilter implements Filter {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {

            final String role = (String) session.getAttribute("role");

            moveToMenu(req, res, role, login);

        } else if (userService.userIsExist(login, password)) {

            final String role = userService.getRoleByLoginPassword(login, password);

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);

            moveToMenu(req, res, role, login);

        } else {

            moveToMenu(req, res, "unknown", login);
        }
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String role,
                            final String login)
            throws ServletException, IOException {

        if (role.equals("admin")) {
            res.sendRedirect(req.getContextPath() + "/admin");

        } else if (role.equals("user")) {
            req.setAttribute("user", userService.getUserByName(login));
            req.getRequestDispatcher("/WEB-INF/view/user_menu.jsp").forward(req, res);

        } else {
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {
    }
}
