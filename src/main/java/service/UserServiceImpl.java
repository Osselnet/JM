package service;

import dao.UserDAO;
import dao.UserJdbcDAO;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userService;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public List<User> getAllUser() {
        return getUsertDAO().getAllUser();
    }

    @Override
    public void deleteUser(int id) {
        getUsertDAO().delete(id);
    }

    @Override
    public void addUser(User user) {
        getUsertDAO().insert(user);
    }

    @Override
    public void update(User user) {
        getUsertDAO().update(user);
    }

    @Override
    public User getUserById(int id) {
        return getUsertDAO().getUserById(id);
    }

    public void createTable() {
        getUsertDAO().createTable();
    }

    private UserDAO getUsertDAO() {
        return new UserJdbcDAO();
    }
}
