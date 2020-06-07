package dao;

import model.User;
import model.Users;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public void insert(User user) {
        Users users = new Users(user.getId(), user.getName(), user.getAge());
        session.save(users);
        session.close();
    }

    @Override
    public void update(User user) throws SQLException {
        Query query = session.createQuery("UPDATE Users SET name = :userName id = :userId");
        query.setParameter("userName", user.getName());
        query.setParameter("userId", user.getId());
        query.executeUpdate();
        session.close();
    }

    @Override
    public void delete(int id) {
        Query query = session.createQuery("DELETE Users WHERE id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
        session.close();
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = session.createQuery("FROM Users").list();
        session.close();
        return users;
    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public User getUserById(int id) {
        Query query = session.createQuery("FROM Users WHERE id = :userId");
        query.setParameter("userId", id);
        User user = (User) query.list().get(0);
        session.close();
        return user;
    }
}
