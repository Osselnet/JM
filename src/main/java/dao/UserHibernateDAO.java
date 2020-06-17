package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import util.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private final Session session;

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
        Query query = session.createQuery("from User where id = :userId");
        query.setParameter("userId", id);
        return (User) query.uniqueResult();
    }

    @Override
    public User getUserByName(String name) {
        Query query = session.createQuery("from User where name = :userName");
        query.setParameter("userName", name);
        return (User) query.uniqueResult();
    }

    @Override
    public boolean userIsExist(String login, String password) {
        Query query = session.createQuery("from User where login = :userLogin and password = :userPassword");
        query.setParameter("userLogin", login);
        query.setParameter("userPassword", password);
        return query.list().size() > 0;
    }

    @Override
    public String getRoleByLoginPassword(String login, String password) {
        Query query = session.createQuery("select role from User where login = :userLogin and password = :userPassword");
        query.setParameter("userLogin", login);
        query.setParameter("userPassword", password);
        return (String) query.list().get(0);
    }
}
