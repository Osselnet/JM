package servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/admin")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();

        final String role = (String) session.getAttribute("role");

        if (nonNull(role)) {
            if (role.equals("admin")) {
                res.sendRedirect(req.getContextPath() + "/admin");

            } else if (role.equals("user")) {
                res.sendRedirect(req.getContextPath() + "/user");
            }
        }
        res.sendRedirect(req.getContextPath() + "/");
    }
}
