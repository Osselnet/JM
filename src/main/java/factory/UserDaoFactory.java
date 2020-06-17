package factory;

import dao.UserDAO;

public interface UserDaoFactory {

    UserDAO getUserDAO();
}
