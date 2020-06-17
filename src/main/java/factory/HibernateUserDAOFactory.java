package factory;

import dao.UserDAO;
import dao.UserHibernateDAO;

public class HibernateUserDAOFactory implements UserDaoFactory {

    @Override
    public UserDAO getUserDAO() {
        return new UserHibernateDAO();
    }
}
