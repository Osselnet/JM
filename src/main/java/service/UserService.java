package service;

import dao.UserDAO;
import dao.UserJDBCDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public UserService() {
    }

    public List<User> getAllUser() {
        try {
            return getUsertDAO().getAllUser();
        } catch (SQLException e) {
        }
        return new ArrayList<>();
    }

    public boolean deleteUser(int userId) {
        try {
            getUsertDAO().delete(userId);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addUser(User user) {
        try {
            getUsertDAO().insert(user);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void update(User user) {
        try {
            getUsertDAO().update(user);
        } catch (SQLException e) {
        }
    }

    public User getClientById(int id) {
        try {
            return getUsertDAO().getClientById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    public void createTable() {
        UserDAO dao = getUsertDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=ca5e59f2");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUsertDAO() {
        return new UserJDBCDAO(getMysqlConnection());
    }
}
