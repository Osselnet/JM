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
        session.save(user);
    }

    @Override
    public void update(User user) {
        Query query = session.createQuery("update User set name = :userName where id = :userId");
        query.setParameter("userName", user.getName());
        query.setParameter("userId", user.getId());
        query.executeUpdate();
    }

    @Override
    public void delete(long id) {
        Query query = session.createQuery("delete User where id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
     }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUser() {
        return (List<User>) session.createQuery("from User").list();
    }

    @Override
    public void createTable() {
    }

    @Override
    public User getUserById(long id) {
        Query query = session.createQuery("FROM User WHERE id = :userId");
        query.setParameter("userId", id);
        return (User) query.uniqueResult();
    }
}
