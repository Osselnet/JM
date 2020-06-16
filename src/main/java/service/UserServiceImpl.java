package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userService;

    private UserServiceImpl() {
    }


    private UserDAO getUsertDAO() {
        return UserDaoFactory.configure();
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

    @Override
    public User getUserByName(String name) {
        return getUsertDAO().getUserByName(name);
    }

    @Override
    public void createTable() {
        getUsertDAO().createTable();
    }

    @Override
    public boolean userIsExist(String login, String password) {
        return getUsertDAO().userIsExist(login, password);
    }

    @Override
    public String getRoleByLoginPassword(String login, String password) {
        return getUsertDAO().getRoleByLoginPassword(login, password);
    }
}
