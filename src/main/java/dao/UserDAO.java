package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public void insert(User user) throws SQLException;

    public void update(User user) throws SQLException;

    public void delete(int userId) throws SQLException;

    public List<User> getAllUser() throws SQLException;

    public void createTable() throws SQLException;

    public User getClientById(int id) throws SQLException;
}
