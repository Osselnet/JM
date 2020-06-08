package service;

import model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUser();

    public void deleteUser(int id);

    public void addUser(User user);

    public void update(User user);

    public User getUserById(int id);
}
