package UserPackage;

import Quiz.Quiz;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User addUser(String userName, String password, boolean isAdministrator) throws SQLException;

    User getUser(int userId) throws SQLException;

    int getUserIdByName(String username) throws SQLException;

    boolean deleteUser(User user) throws SQLException, ClassNotFoundException;

    boolean isCorrectPassword(String userName, String password) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    boolean addProfile(int userId, String name, String surname, Date birthDate, String birthPlace, String status) throws SQLException;

    String hexToString(byte[] bytes);

    String findHashCode(String str);

    String getSalt(int userId) throws SQLException;

    boolean containsUserName(String userName) throws SQLException;
}
