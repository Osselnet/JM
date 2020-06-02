package dao;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserJDBCDAO implements UserDAO{

    private Connection connection;

    public UserJDBCDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int userId) {

    }

    @Override
    public List<User> getAllUser() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("select * from users");
        ResultSet result = stmt.getResultSet();
        result.close();
        stmt.close();
        return (List<User>)result;
    }

    @Override
    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256), age bigint, primary key (id))");
        stmt.close();
    }
}
