package servlets.filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();

        boolean isDelete = req.getRequestURI().equals("/admin/delete");
        boolean isUpdate = req.getRequestURI().equals("/admin/update");
        boolean isAddUser = req.getRequestURI().equals("/admin/add_user");

        User user = (User) session.getAttribute("userObject");

        if (user.getRole().equals("admin")) {
            if (isDelete) {
                servletRequest.getRequestDispatcher("/admin/delete").forward(servletRequest, servletResponse);
            } else if (isUpdate) {
                servletRequest.getRequestDispatcher("/admin/update").forward(servletRequest, servletResponse);
            } else if (isAddUser) {
                servletRequest.getRequestDispatcher("/admin/add_user").forward(servletRequest, servletResponse);
            } else
            servletRequest.getRequestDispatcher("/admin").forward(servletRequest, servletResponse);
        } else {
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
