package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDAO configure() {
        if (getProperty().equals("UserJdbcDAO")) {
            return new UserHibernateDAO();
        } else if (getProperty().equals("UserHibernateDAO")) {
            return new UserJdbcDAO();
        }
        return null;
    }

    private static String getProperty() {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);

            return property.getProperty("daotype");

        } catch (IOException e) {
            return null;
        }
    }
}
