package dao;

import model.User;
import java.util.List;

public interface UserDAO {
    void insert(User user);

    void update(User user);

    void delete(long id);

    List<User> getAllUser();

    void createTable();

    User getUserById(long id);
}
