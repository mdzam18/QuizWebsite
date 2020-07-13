package ProfilePackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserSqlDao implements UserDao {
    private Connection con;
    private String userTable;
    private String friendsTable;
    private static MessageDigest md;

    public UserSqlDao() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        con = ProfileDataSrc.getConnection();
        userTable = "test.Users";
        friendsTable = "test.Friends";
        md = MessageDigest.getInstance("SHA");
    }


    private String createSalt() {
        String str = "";
        Random random = new Random();
        int length = 1 + random.nextInt(5);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(129);
            char c = (char) index;
            str = str + c;
        }
        return str;
    }

    @Override
    public User addUser(String userName, String password) throws SQLException {
        String salt = createSalt();
        password = password + salt;
        password = findHashCode(password);
        PreparedStatement statement = con.prepareStatement(
                "SELECT * FROM " + userTable + " WHERE UserName = ?;");
        statement.setString(1, userName);
        ResultSet res = statement.executeQuery();
        if (res.next()) return null;
        statement = con.prepareStatement(
                "SELECT max(UserId) FROM " + userTable + ";");
        res = statement.executeQuery();
        int id = 0;
        res.next();
        if (res != null) {
            id = res.getInt(1);
        }
        id++;
        statement = con.prepareStatement("insert into " + userTable + "  values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setInt(1, id);
        statement.setString(2, userName);
        statement.setString(3, password);
        statement.setBoolean(4, false);
        statement.setString(5, salt);
        statement.setString(6, null);
        statement.setString(7, null);
        statement.setDate(8, null);
        statement.setString(9, null);
        statement.setString(10, null);
        statement.executeUpdate();
        User user = new User(userName, id, password);
        return user;
    }

    @Override
    public User getUser(int userId) throws SQLException {
        PreparedStatement stm = null;
        stm = con.prepareStatement(
                "SELECT * FROM " + userTable + " WHERE UserId = ?;");
        stm.setInt(1, userId);
        ResultSet res = stm.executeQuery();
        if (!res.next()) return null;
        User user = new User(res.getString(2), res.getInt(1), res.getString(3));
        user.setName(res.getString(6));
        user.setSurname(res.getString(7));
        user.setBirthDate(res.getDate(8));
        user.setBirthPlace(res.getString(9));
        user.setStatus(res.getString(10));
        return user;
    }

  /*  @Override
    public boolean deleteUser(User user) throws SQLException {
        PreparedStatement stm = null;
        stm = con.prepareStatement(
                "delete from " + userTable + " where UserId = ?;");
        stm.setInt(1, user.getUserId());
        stm.executeUpdate();
        return true;
    }
   */


    @Override
    public boolean isCorrectPassword(String userName, String password) throws SQLException {
        PreparedStatement stm = null;
        stm = con.prepareStatement(
                "SELECT * FROM " + userTable + " WHERE UserName = ?;");
        stm.setString(1, userName);
        ResultSet res = stm.executeQuery();
        res.next();
        int id = res.getInt(1);
        String salt = getSalt(id);
        password = password + salt;
        password = findHashCode(password);
        stm = con.prepareStatement(
                "SELECT * FROM " + userTable + " WHERE UserName = ? and Password = ?;");
        stm.setString(1, userName);
        stm.setString(2, password);
        res = stm.executeQuery();
        if (!res.next()) return false;
        return true;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        PreparedStatement stm = null;
        List<User> result = new ArrayList<>();
        stm = con.prepareStatement(
                "SELECT * FROM " + userTable + ";");
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            User user = new User(res.getString(2), res.getInt(1), res.getString(3));
            result.add(user);
        }
        return result;
    }

    @Override
    public List<User> getFriends(User user) throws SQLException {
        PreparedStatement stm = null;
        List<User> result = new ArrayList<>();
        stm = con.prepareStatement(
                "SELECT * FROM " + friendsTable + "WHERE (SenderId = ? or ReceiverId = ?) and Confirmed = ?;");
        stm.setInt(1, user.getUserId());
        stm.setInt(2, user.getUserId());
        stm.setBoolean(3, true);
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            User user2 = new User(res.getString(2), res.getInt(1), res.getString(3));
            user2.setName(res.getString(6));
            user2.setSurname(res.getString(7));
            user2.setBirthDate(res.getDate(8));
            user2.setBirthPlace(res.getString(9));
            user2.setStatus(res.getString(10));
            result.add(user2);
        }
        return result;
    }

   /* @Override
    public boolean sendFriendRequest(User from, User to) throws SQLException {
        if (from.getUserId() == to.getUserId()) return false;
        PreparedStatement statement = con.prepareStatement("select * from " + friendsTable + " where SenderId = ? and ReceiverId = ?;");
        statement.setInt(1, from.getUserId());
        statement.setInt(2, to.getUserId());
        ResultSet res = statement.executeQuery();
        if (res.next()) return false;
        statement = con.prepareStatement("select * from " + friendsTable + " where SenderId = ? and ReceiverId = ?;");
        statement.setInt(1, to.getUserId());
        statement.setInt(2, from.getUserId());
        if (res.next()) return false;
        statement = con.prepareStatement("insert into " + friendsTable + " values (?, ?, ?)");
        statement.setInt(1, from.getUserId());
        statement.setInt(2, to.getUserId());
        statement.setBoolean(3, false);
        statement.executeUpdate();
        return true;
    } */

    /*@Override
    public boolean confirmFriendRequest(MailSqlDao mDao) throws SQLException {
        Mail mail = new Mail();
        mDao.sendMail();
        PreparedStatement statement = con.prepareStatement("update " + friendsTable + " set Confirmed = ? where SenderId = ? and ReceiverId = ?;");
        statement.setBoolean(1, true);
        statement.setInt(2, from.getUserId());
        statement.setInt(3, to.getUserId());
        statement.executeUpdate();
        return true;
    } */

    @Override
    public boolean deleteFriend(User from, User to) throws SQLException {
        PreparedStatement stm = null;
        stm = con.prepareStatement(
                "delete from " + friendsTable + " where (SenderId = ? and ReceiverId = ?) or (SenderId = ? and ReceiverId = ?);");
        stm.setInt(1, from.getUserId());
        stm.setInt(2, to.getUserId());
        stm.setInt(3, to.getUserId());
        stm.setInt(4, from.getUserId());
        stm.executeUpdate();
        return true;
    }

    @Override
    public boolean createUserTable() throws SQLException, ClassNotFoundException {
        Statement s = con.createStatement();
        userTable = "test.Users2";
        s.executeUpdate("CREATE TABLE " + userTable + " (\n" + "UserId int primary key, \n" +
                "UserName varchar(255),\n" +
                "Password varchar(255),\n" +
                "IsAdministrator boolean,\n" +
                "Salt varchar (255),\n" +
                "Name varchar(255),\n" +
                "Surname varchar(255),\n" +
                "Birth_Date Date,\n" +
                "Birth_Place varchar(255),\n" +
                "Status varchar(255)" + ");");
        // userTable = "test.Users";
        return true;
    }

    @Override
    public boolean createFriendsTable() throws SQLException, ClassNotFoundException {
        Statement s = con.createStatement();
        friendsTable = "test.Friends2";
        s.executeUpdate("CREATE TABLE " + friendsTable + " (\n" + "SenderId int ,\n" +
                "ReceiverId int ,\n" +
                "Confirmed boolean,\n" +
                "foreign key (SenderId) references Users(UserId),\n" +
                "foreign key (ReceiverId) references Users(UserId));");
        //friendsTable = "test.Friends";
        return true;
    }

    @Override
    public boolean dropTable(String tableName) throws SQLException {
        Statement stm = null;
        stm = con.createStatement();
        stm.executeUpdate("drop table " + tableName);
        return true;
    }

    @Override
    public boolean addProfile(int userId, String name, String surname, Date birthDate, String birthPlace, String status) throws SQLException {
        PreparedStatement statement = con.prepareStatement("update " + userTable + " set Name = ? , Surname = ?, Birth_Date = ? , Birth_Place = ? , Status = ? where UserId = ?;");
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setDate(3, birthDate);
        statement.setString(4, birthPlace);
        statement.setString(5, status);
        statement.setInt(6, userId);
        statement.executeUpdate();
        return true;
    }

    @Override
    public List<User> getSentRequests(User user) throws SQLException {
        PreparedStatement stm = null;
        List<User> result = new ArrayList<>();
        stm = con.prepareStatement(
                "SELECT * FROM " + friendsTable + "WHERE (SenderId = ?) and Confirmed = ?;");
        stm.setInt(1, user.getUserId());
        stm.setBoolean(3, false);
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            User user2 = new User(res.getString(2), res.getInt(1), res.getString(3));
            user2.setName(res.getString(6));
            user2.setSurname(res.getString(7));
            user2.setBirthDate(res.getDate(8));
            user2.setBirthPlace(res.getString(9));
            user2.setStatus(res.getString(10));
            result.add(user2);
        }
        return result;
    }

    @Override
    public List<User> getReceivedRequests(User user) throws SQLException {
        PreparedStatement stm = null;
        List<User> result = new ArrayList<>();
        stm = con.prepareStatement(
                "SELECT * FROM " + friendsTable + "WHERE (ReceiverId = ?) and Confirmed = ?;");
        stm.setInt(1, user.getUserId());
        stm.setBoolean(3, false);
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            User user2 = new User(res.getString(2), res.getInt(1), res.getString(3));
            user2.setName(res.getString(6));
            user2.setSurname(res.getString(7));
            user2.setBirthDate(res.getDate(8));
            user2.setBirthPlace(res.getString(9));
            user2.setStatus(res.getString(10));
            result.add(user2);
        }
        return result;
    }

    /*
     Given a byte[] array, produces a hex String,
     such as "234a6f". with 2 chars for each byte in the array.
     (provided code)
    */
    @Override
    public String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;  // remove higher bits, sign
            if (val < 16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }

    //Returns hashcode of the string.
    @Override
    public String findHashCode(String str) {
        byte[] buffer = str.getBytes();
        byte[] res = md.digest(buffer);
        return (hexToString(res));
    }

    @Override
    public String getSalt(int userId) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select * from " + userTable + " where UserId = ?");
        statement.setInt(1, userId);
        ResultSet res = statement.executeQuery();
        res.next();
        return res.getString(5);
    }

}
