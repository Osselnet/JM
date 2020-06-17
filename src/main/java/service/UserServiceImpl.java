package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    private static UserServiceImpl userService;

    private UserServiceImpl() {
        UserDaoFactory daoFactory = UserDaoFactory.configure();
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
}
