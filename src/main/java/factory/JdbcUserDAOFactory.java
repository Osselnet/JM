package factory;

import dao.UserDAO;
import dao.UserJdbcDAO;

public class JdbcUserDAOFactory implements UserDaoFactory {

    @Override
    public UserDAO getUserDAO() {
        return new UserJdbcDAO();
    }
}
