package servlets.filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        boolean isAuth = session.getAttribute("userObject") != null;

        User user;
        boolean isUserRole = false;
        boolean isAdminRole = false;
         if (isAuth) {
            user = (User) session.getAttribute("userObject");
            isUserRole = user.getRole().equals("user");
            isAdminRole = user.getRole().equals("admin");
        }

        if (isAdminRole) {
            res.sendRedirect(req.getContextPath() + "/admin");
         } else if (isUserRole) {
            servletRequest.getRequestDispatcher("/user").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}