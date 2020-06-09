package dao;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private Connection connection;

    public UserJdbcDAO() {
        this.connection = DBHelper.getMysqlConnection();
    }

    @Override
    public void insert(User user) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("insert into users (name, age) VALUES ('" + user.getName() + "', " + user.getAge() + ")");
        } catch (SQLException e) {
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE users SET name = ? where id = ?")) {
            stmt.setString(1, user.getName());
            stmt.setLong(2, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void delete(long id) {
        try (Statement stmt = connection.createStatement();) {
            stmt.execute("DELETE FROM users WHERE id=" + id);
        } catch (SQLException e) {
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("select * from users");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                users.add(new User(result.getLong("id"),
                        result.getNString("name"), result.getInt("age")));
            }
            result.close();
        } catch (SQLException e) {
        }
        return users;
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("select * from users where id=" + id);
            ResultSet result = stmt.getResultSet();
            result.next();
            user = new User(id,
                    result.getNString("name"),
                    result.getInt("age")
            );
            result.close();
        } catch (SQLException e) {
        }
        return user;
    }

    @Override
    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256), age bigint, primary key (id))");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
