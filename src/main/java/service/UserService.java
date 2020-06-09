package service;

import model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    void deleteUser(int id);

    void addUser(User user);

    void update(User user);

    User getUserById(int id);
}
