package ProfilePackage;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User addUser(String userName, String password) throws SQLException;

    User getUser(int userId) throws SQLException;

    // boolean deleteUser(User user) throws SQLException;

    boolean isCorrectPassword(String userName, String password) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    List<User> getFriends(User user) throws SQLException;

    //  boolean sendFriendRequest(MailSqlDao mDao) throws SQLException;

    //  boolean confirmFriendRequest(MailSqlDao mDao) throws SQLException;

    boolean deleteFriend(User from, User to) throws SQLException;

    List<User> getSentRequests(User user) throws SQLException;

    List<User> getReceivedRequests(User user) throws SQLException;

    boolean createUserTable() throws SQLException, ClassNotFoundException;

    boolean createFriendsTable() throws SQLException, ClassNotFoundException;

    boolean dropTable(String tableName) throws SQLException;

    boolean addProfile(int userId, String name, String surname, Date birthDate, String birthPlace, String status) throws SQLException;

    String hexToString(byte[] bytes);

    String findHashCode(String str);

    String getSalt(int userId) throws SQLException;
}
