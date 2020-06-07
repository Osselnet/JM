package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(User user) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("insert into users (name, age) VALUES ('" + user.getName()  + "', " + user.getAge() + ")");
        stmt.close();
    }

    @Override
    public void update(User user) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET name = ? where id = ?");
        stmt.setString(1, user.getName());
        stmt.setLong(2, user.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("DELETE FROM users WHERE id=" + id);
        stmt.close();
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        List<User> users = new ArrayList<>();
        Statement stmt = connection.createStatement();
        stmt.execute("select * from users");
        ResultSet result = stmt.getResultSet();
        while (result.next()) {
            users.add(new User(result.getLong("id"),
                    result.getNString("name"), result.getInt("age")));
        }
        result.close();
        stmt.close();
        return users;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("select * from users where id=" + id);
        ResultSet result = stmt.getResultSet();
        result.next();
        User user = new User(id,
                result.getNString("name"),
                result.getInt("age")
        );
        result.close();
        stmt.close();
        return user;
    }

    @Override
    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256), age bigint, primary key (id))");
        stmt.close();
    }
}
