package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    UserService userService = new UserService();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        userService.createTable();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}