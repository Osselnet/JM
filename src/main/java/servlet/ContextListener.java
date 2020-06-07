package servlet;

import util.DBHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //userService.createTable();
        DBHelper dbHelper = new DBHelper();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}