package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import util.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO() {
        this.session = DBHelper.getSessionFactory().openSession();
    }

    @Override
    public void insert(User user) {
        //User users = new User(user.getId(), user.getName(), user.getAge());
        session.save(user);
        session.close();
    }

    @Override
    public void update(User user) {
        Query query = session.createQuery("UPDATE User SET name = :userName WHERE id = :userId");
        query.setParameter("userName", user.getName());
        query.setParameter("userId", user.getId());
        query.executeUpdate();
        session.close();
    }

    @Override
    public void delete(long id) {
        Query query = session.createQuery("DELETE User WHERE id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
        session.close();
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    @Override
    public void createTable() {

    }

    @Override
    public User getUserById(long id) {
        Query query = session.createQuery("FROM User WHERE id = :userId");
        query.setParameter("userId", id);
        User user = (User) query.list().get(0);
        session.close();
        //return new User(users.getId(), users.getName(), users.getAge());
        return user;
    }
}
