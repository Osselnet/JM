package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public void insert(User user);

    public void update(User user);

    public void delete(int userId);

    public List<User> getAllUser() throws SQLException;

    public void createTable() throws SQLException;
}
