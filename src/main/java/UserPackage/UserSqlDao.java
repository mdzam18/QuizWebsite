package UserPackage;

import HistoryPackage.History;
import HistoryPackage.HistorySqlDao;
import ProfilePackage.CreateTablesForTests;
import ProfilePackage.NanukaDatabase;
import ProfilePackage.ProfileDataSrc;
import Quiz.Quiz;
import Quiz.QuizSqlDao;

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
    private String MailsTable;
    private String historyTable;
    private String achievementsTable;
    private String quizTable;
    private String quizTagTable;
    private String questionTable;
    private static MessageDigest md;

    public UserSqlDao() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        con = ProfileDataSrc.getConnection();
        //con = NanukaDatabase.getConnection();
        init();
        md = MessageDigest.getInstance("SHA");
    }

    private void init() {
        userTable = CreateTablesForTests.UsersTable;
        friendsTable = CreateTablesForTests.FriendsTable;
        MailsTable = CreateTablesForTests.MailsTable;
        historyTable = CreateTablesForTests.HistoryTable;
        achievementsTable = CreateTablesForTests.AchievementsTable;
        quizTable = CreateTablesForTests.QuizTable;
        quizTagTable = CreateTablesForTests.QuizTagTable;
        questionTable = CreateTablesForTests.QuestionTable;
    }

    public int getUserIdByName(String username) throws SQLException {
        PreparedStatement stm = null;
        String s = "SELECT * FROM " + userTable + " WHERE UserName = " + username + ";";
        stm = con.prepareStatement(
                "SELECT * FROM " + userTable + " WHERE UserName = ?;");
        stm.setString(1, username);
        ResultSet res = stm.executeQuery();
        if (!res.next()) return -1;
        return res.getInt("UserId");
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
    public User addUser(String userName, String password, boolean isAdministrator) throws SQLException {
        if (containsUserName(userName)) return null;
        String salt = createSalt();
        password = password + salt;
        password = findHashCode(password);
        PreparedStatement statement = con.prepareStatement(
                "SELECT max(UserId) FROM " + userTable + ";");
        ResultSet res = statement.executeQuery();
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
        statement.setBoolean(4, isAdministrator);
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
        user.setAdministrator(res.getBoolean(4));
        user.setName(res.getString(6));
        user.setSurname(res.getString(7));
        user.setBirthDate(res.getDate(8));
        user.setBirthPlace(res.getString(9));
        user.setStatus(res.getString(10));
        return user;
    }

    private void delete(User user, String tableName) throws SQLException {
        PreparedStatement stm = null;
        if (tableName.equals(CreateTablesForTests.FriendsTable) || tableName.equals(CreateTablesForTests.MailsTable)) {
            stm = con.prepareStatement(
                    "delete from " + tableName + " where SenderId = ? or ReceiverId = ?;");
            stm.setInt(1, user.getUserId());
            stm.setInt(2, user.getUserId());
        } else {
            stm = con.prepareStatement(
                    "delete from " + tableName + " where UserId = ?;");
            stm.setInt(1, user.getUserId());
        }
        stm.executeUpdate();
    }

    private void deleteQuiz(int id, String tableName) throws SQLException {
        PreparedStatement stm = null;
        stm = con.prepareStatement(
                "delete from " + tableName + " where QuizId = ?;");
        stm.setInt(1, id);
    }

    @Override
    public boolean deleteUser(User user) throws SQLException, ClassNotFoundException {
        delete(user, friendsTable);
        delete(user, MailsTable);
        delete(user, historyTable);
        delete(user, achievementsTable);
        QuizSqlDao qDao = new QuizSqlDao();
        List<Quiz> list = qDao.getQuizzesForUser(user.getUserId());
        for (int i = 0; i < list.size(); i++) {
            deleteQuiz(list.get(i).getQuizId(), quizTagTable);
            deleteQuiz(list.get(i).getQuizId(), questionTable);
            deleteQuiz(list.get(i).getQuizId(), quizTable);
        }
        delete(user, userTable);
        return true;
    }


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
            user.setAdministrator(res.getBoolean(4));
            user.setName(res.getString(6));
            user.setSurname(res.getString(7));
            user.setBirthDate(res.getDate(8));
            user.setBirthPlace(res.getString(9));
            user.setStatus(res.getString(10));
            result.add(user);
        }
        return result;
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

    @Override
    public boolean containsUserName(String userName) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
                "SELECT * FROM " + userTable + " WHERE UserName = ?;");
        statement.setString(1, userName);
        ResultSet res = statement.executeQuery();
        if (res.next()) return true;
        return false;
    }

    @Override
    public List<Quiz> getRecentlyTakenQuizzes(User user) throws SQLException, ClassNotFoundException {
        HistorySqlDao hDao = new HistorySqlDao();
        List<History> histories = hDao.getHistories(user.getUserId());
        histories = HistorySqlDao.sortByEndDate(histories);
        List<Quiz> res = new ArrayList<>();
        QuizSqlDao qDao = new QuizSqlDao();
        int n = 0;
        for (int i = histories.size() - 1; i >= 0; i--) {
            if (n == 5) break;
            if (histories.size() >= 5) {
                n++;
            }
            res.add(qDao.getQuiz(histories.get(i).getQuizId()));
        }
        return res;
    }
}
