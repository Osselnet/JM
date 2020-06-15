package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDAO configure() {
        switch (Objects.requireNonNull(getProperty())) {
            case "UserJdbcDAO":
                return new UserJdbcDAO();
            case "UserHibernateDAO":
                return new UserHibernateDAO();
            default:
                return null;
        }
    }

    private static String getProperty() {
        Properties property = new Properties();
        try (final InputStream stream = UserDaoFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            property.load(stream);
            return property.getProperty("daotype");
        } catch (IOException e) {
            return "error";
        }
    }
}
