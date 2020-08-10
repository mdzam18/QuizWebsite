package Administrator.dao;

import Administrator.dao.AdministratorDao;
import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;
import Quiz.Quiz;
import UserPackage.User;
import UserPackage.UserSqlDao;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Quiz.QuizSqlDao;

public class AdministratorSqlDao extends UserSqlDao implements AdministratorDao {
    private Connection con;
    private String userTable;
    private String quizTable;
    private String historyTable;

    public AdministratorSqlDao() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        con = ProfileDataSrc.getConnection();
        userTable = CreateTablesForTests.UsersTable;
        quizTable = CreateTablesForTests.QuizTable;
        historyTable = CreateTablesForTests.HistoryTable;
    }

    @Override
    public List<User> getAllAdmins() throws SQLException {
        List<User> admins = new ArrayList<>();
        PreparedStatement stm =
                con.prepareStatement("SELECT * FROM " + userTable + " WHERE IsAdministrator = ?;");
        stm.setBoolean(1, true);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            User admin = new User(rs.getString(2), rs.getInt(1), rs.getString(3));
            admin.setAdministrator(rs.getBoolean(4));
            admin.setName(rs.getString(6));
            admin.setSurname(rs.getString(7));
            admin.setBirthPlace(rs.getString(8));
            admin.setStatus(rs.getString(9));
            admins.add(admin);
        }
        return admins;
    }

    @Override
    public User addAdmin(String username, String password) throws SQLException {
        return super.addUser(username, password, true);
    }

    @Override
    public User getAdmin(int userId) throws SQLException {
        return super.getUser(userId);
    }

    @Override
    public boolean deleteUser(User user) throws SQLException, ClassNotFoundException {
        return super.deleteUser(user);
    }

    @Override
    public boolean deleteQuiz(Quiz quiz) throws SQLException, ClassNotFoundException {
        QuizSqlDao qDao = new QuizSqlDao();
        qDao.deleteQuiz(quiz);
        return true;
    }

    @Override
    public boolean deleteHistory(Quiz quiz) throws SQLException {
        PreparedStatement stm =
                con.prepareStatement("DELETE FROM " + historyTable + " WHERE QuizId = ?;");
        stm.setInt(1, quiz.getQuizId());
        stm.executeUpdate();
        return true;
    }

    @Override
    public boolean promoteUser(User user) throws SQLException {
        PreparedStatement stm =
                con.prepareStatement("UPDATE " + userTable + " SET IsAdministrator = ? WHERE UserId = ?;");
        stm.setBoolean(1, true);
        stm.setInt(2, user.getUserId());
        stm.executeUpdate();
        return true;
    }
}
