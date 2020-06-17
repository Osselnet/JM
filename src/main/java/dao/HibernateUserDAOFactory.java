package dao;

public class HibernateUserDAOFactory extends UserDaoFactory {
    @Override
    public UserDAO getUserDAO() {
        return new UserHibernateDAO();
    }
}
