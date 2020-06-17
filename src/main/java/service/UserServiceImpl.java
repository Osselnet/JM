package service;

import dao.UserDAO;
import factory.UserDaoFactory;
import model.User;
import util.AppConfigure;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    private static UserServiceImpl userService;

    private UserServiceImpl() {
        UserDaoFactory daoFactory = AppConfigure.configure();
        userDAO = daoFactory.getUserDAO();
    }

    public static UserServiceImpl getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Override
    public void deleteUser(int id) {
        userDAO.delete(id);
    }

    @Override
    public void addUser(User user) {
        userDAO.insert(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Override
    public void createTable() {
        userDAO.createTable();
    }

    @Override
    public boolean userIsExist(String login, String password) {
        return userDAO.userIsExist(login, password);
    }

    @Override
    public String getRoleByLoginPassword(String login, String password) {
        return userDAO.getRoleByLoginPassword(login, password);
    }
}
