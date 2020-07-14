package ProfilePackage;

//import Quiz.*;

import Quiz.Quiz;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdministratorSqlDao implements AdministratorDao {
	private Connection con;
	private String userTable;
	private String quizTable;
	private String historyTable;
	private UserDao userDao;
	
	public AdministratorSqlDao() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		con = ProfileDataSrc.getConnection();
		userTable = CreateTablesForTests.UsersTableTest;
		quizTable = CreateTablesForTests.QuizTableTest;
		historyTable = CreateTablesForTests.HistoryTableTest;
		userDao = new UserSqlDao();
	}
	
	@Override
	public List<User> getAllAdmins() throws SQLException {
		List <User> admins = new ArrayList<>();
		PreparedStatement stm =
				con.prepareStatement("SELECT * FROM " + userTable + " WHERE IsAdministrator = ?;");
		stm.setBoolean(1,true);
		ResultSet rs = stm.executeQuery();
		while (rs.next()){
			User admin = new User(rs.getString(2),rs.getInt(1),rs.getString(3));
			admin.setAdministrator(rs.getBoolean(4));
//			admin.setPassword(rs.getString(5))
			admin.setName(rs.getString(6));
			admin.setSurname(rs.getString(7));
			admin.setBirthDate(rs.getDate(8));
			admin.setBirthPlace(rs.getString(9));
			admin.setStatus(rs.getString(10));
			admins.add(admin);
		}
		return admins;
	}
	
	@Override
	public User addAdmin(String username, String password) throws SQLException {
		User user = userDao.addUser(username, password);
		PreparedStatement stm =
				con.prepareStatement("UPDATE " + userTable + " SET IsAdministrator = ? WHERE UserId = ?;");
		stm.setBoolean(1, true);
		stm.setInt(2, user.getUserId());
		return user;
	}
	
	@Override
	public User getAdmin(int userId) throws SQLException {
		User user = userDao.getUser(userId);
		return user;
	}
	
	@Override
	public boolean deleteUser(User user) throws SQLException {
		PreparedStatement stm =
				con.prepareStatement("DELETE FROM " + userTable + " WHERE UserId = ?;");
		stm.setInt(1, user.getUserId());
		int n = stm.executeUpdate();
		if(n == 1) return true;
		return false;
	}
	
	//	@Override
//	public boolean deleteQuiz(Quiz quiz) throws SQLException {
//		PreparedStatement stm =
//				con.prepareStatement("DELETE FROM " + quizTable + " WHERE QuizId = ?;");
//		stm.setInt(1, Integer.valueOf(quiz.getId()));
//		int n = stm.executeUpdate();
//		if(n == 1) return true;
//		return false;
//	}
//
	@Override
	public boolean deleteHistory(Quiz quiz) throws SQLException {
		PreparedStatement stm =
				con.prepareStatement("DELETE FROM " + historyTable + " WHERE QuizId = ?;");
		stm.setInt(1, Integer.valueOf(quiz.getQuizId()));
		int n = stm.executeUpdate();
		if(n > 0) return true;
		return false;
	}
	
	@Override
	public boolean promoteUser(User user) throws SQLException {
		PreparedStatement stm =
				con.prepareStatement("UPDATE " + userTable + " SET IsAdministrator = ? WHERE UserId = ?;");
		stm.setBoolean(1, true);
		stm.setInt(2, user.getUserId());
		int n = stm.executeUpdate();
		if(n == 1) return true;
		return false;
	}
}