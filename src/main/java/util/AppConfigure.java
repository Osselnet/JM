package util;

import factory.HibernateUserDAOFactory;
import factory.JdbcUserDAOFactory;
import factory.UserDaoFactory;

import java.util.Objects;

public class AppConfigure {
    private static final String Jdbc = "UserJdbcDAO";
    private static final String Hibernate = "UserHibernateDAO";

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
