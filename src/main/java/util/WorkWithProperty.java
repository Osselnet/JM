package util;

import dao.UserDaoFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WorkWithProperty {

    private static Properties property = new Properties();

    public static String getProperty() {

        try (final InputStream stream = UserDaoFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            property.load(stream);
            return property.getProperty("daotype");

        } catch (IOException e) {
            return "error";
        }
    }

}
