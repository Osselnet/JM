package dao;

public class JdbcUserDAOFactory extends UserDaoFactory{
    @Override
    public UserDAO getUserDAO() {
        return new UserJdbcDAO();
    }
}
