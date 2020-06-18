package servlets.filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/admin")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        switch (Objects.requireNonNull(user.getRole())) {
            case "admin":
                filterChain.doFilter(req, res);
                break;
            case "user":
                res.sendRedirect(req.getContextPath() + "/user");
                break;
            default:
                res.sendRedirect(req.getContextPath() + "/");
        }
    }
}
