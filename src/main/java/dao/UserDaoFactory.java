package dao;

import util.WorkWithProperty;

import java.util.Objects;

public abstract class UserDaoFactory {

    private static final String Jdbc = "UserJdbcDAO";
    private static final String Hibernate = "UserHibernateDAO";

    public abstract UserDAO getUserDAO();

    public static UserDaoFactory configure() {
        switch (Objects.requireNonNull(WorkWithProperty.getProperty())) {
            case Jdbc:
                return new JdbcUserDAOFactory();
            case Hibernate:
                return new HibernateUserDAOFactory();
            default:
                return null;
        }
    }
}
