package dao;

import util.WorkWithProperty;

import java.util.Objects;

public class UserDaoFactory {

    public static UserDAO configure() {
        switch (Objects.requireNonNull(WorkWithProperty.getProperty())) {
            case "UserJdbcDAO":
                return new UserJdbcDAO();
            case "UserHibernateDAO":
                return new UserHibernateDAO();
            default:
                return null;
        }
    }
}
