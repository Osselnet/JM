package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public void insert(User user);

    public void update(User user);

    public void delete(long id);

    public List<User> getAllUser();

    public void createTable();

    public User getUserById(long id);
}
