/*
package ProfilePackage;

import Quiz.Quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorSqlDao implements AdministratorDao {
	private Connection con;
<<<<<<< HEAD
	private static final String userTable = "Users2";
	private static final String quizTable = "Quiz2";
	private static final String historyTable = "History2";
	
	*//*

*/
/* THESE 3 CONSTANTS ARE FOR TESTING PURPOSES *//*


	private static final String CREATE_USERS2_TABLE = "CREATE TABLE " + userTable + " (\n" +
			"UserId int primary key,\n" +
			"UserName varchar(255),\n" +
			"Password varchar(255),\n" +
			"IsAdministrator boolean,\n" +
			"Salt varchar(255),\n" +
			"Name varchar(255),\n" +
			"Surname varchar(255),\n" +
			"Birth_Date Date,\n" +
			"Birth_Place varchar(255),\n" +
			"Status varchar(255)\n" +
			");";
	private static final String CREATE_HISTORY2_TABLE = "CREATE TABLE " + historyTable + " (\n" +
			"UserId int,\n" +
			"QuizId int,\n" +
			"Score int,\n" +
			"Date Date,\n" +
			"Time Time,\n" +
			"FOREIGN KEY (UserId) REFERENCES Users2 (UserId),\n" +
			"FOREIGN KEY (QuizId) REFERENCES Quiz2 (QuizId)\n" +
			");";
	private static final String CREATE_QUIZ2_TABLE = "CREATE TABLE " + quizTable + " (\n" +
			"QuizId int primary key,\n" +
			"IsRandom boolean,\n" +
			"IsOnePage boolean,\n" +
			"IsImmediate boolean,\n" +
			"InPracticeMode boolean,\n" +
			"NumberOfQuestions int,\n" +
			"Description varchar(255),\n" +
			"Category varchar(255),\n" +
			"CreatorId int,\n" +
			"FOREIGN KEY (CreatorId) REFERENCES Users2 (UserId)\n" +
			");";
=======
	private String userTable;
	private String quizTable;
	private String historyTable;
>>>>>>> f4fd8e79be1dd4bd2706adbd83ee1ce56cb1aa70
	
	public AdministratorSqlDao() throws SQLException, ClassNotFoundException {
		con = ProfileDataSrc.getConnection();
<<<<<<< Updated upstream
		userTable = "Users2"*/
/*CreateTablesForTests.UsersTable;
=======
		userTable = CreateTablesForTests.UsersTableTest;
>>>>>>> Stashed changes
		quizTable = CreateTablesForTests.QuizTableTest;
		historyTable = CreateTablesForTests.HistoryTableTest;
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
		PreparedStatement stm = con.prepareStatement(
				"SELECT * FROM " + userTable + " WHERE UserName = ?;");
		stm.setString(1, username);
		
		ResultSet res = stm.executeQuery();
		if (res.next()) return null;
		
		stm = con.prepareStatement(
				"SELECT max(UserId) FROM " + userTable + ";");
		res = stm.executeQuery();
		int id = 0;
		res.next();
		if (res != null) {
			id = res.getInt(1);
		}
		id++;
		
		stm = con.prepareStatement("INSERT INTO " + userTable + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		stm.setInt(1, id);
		stm.setString(2, username);
		stm.setString(3, password);
		stm.setBoolean(4, true);
		stm.setString(5, ""); //Salt?
		stm.setString(6, null);
		stm.setString(7, null);
		stm.setDate(8, null);
		stm.setString(9, null);
		stm.setString(10, null);
		stm.executeUpdate();
		User user = new User(username, id, password);
		return user;
	}
	
	@Override
	public User getAdmin(int userId) throws SQLException {
		PreparedStatement stm = con.prepareStatement(
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
	
	@Override
	public boolean deleteUser(User user) throws SQLException {
		PreparedStatement stm =
				con.prepareStatement("DELETE FROM " + userTable + " WHERE UserId = ?;");
		stm.setInt(1, user.getUserId());
		int n = stm.executeUpdate();
		if(n == 1) return true;
		return false;
	}
	
	@Override
	public boolean deleteQuiz(Quiz quiz) throws SQLException {
		PreparedStatement stm =
				con.prepareStatement("DELETE FROM " + quizTable + " WHERE QuizId = ?;");
		stm.setInt(1, Integer.valueOf(quiz.getId()));
		int n = stm.executeUpdate();
		if(n == 1) return true;
		return false;
	}
	
	@Override
	public boolean deleteHistory(Quiz quiz) throws SQLException {
		PreparedStatement stm =
				con.prepareStatement("DELETE FROM " + historyTable + " WHERE QuizId = ?;");
		stm.setInt(1, Integer.valueOf(quiz.getId()));
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
<<<<<<< HEAD
	
	public boolean createUsersTable() throws SQLException {
		Statement stm = con.createStatement();
		stm.executeUpdate(CREATE_USERS2_TABLE);
		return true;
	}
	
	public boolean createHistoryTable() throws SQLException {
		Statement stm = con.createStatement();
		stm.executeUpdate(CREATE_HISTORY2_TABLE);
		return true;
	}
	
	public boolean createQuizTable() throws SQLException {
		Statement stm = con.createStatement();
		stm.executeUpdate(CREATE_QUIZ2_TABLE);
		return true;
	}
	
	public boolean dropTable(String tableName) throws SQLException {
		Statement stm = con.createStatement();
		stm.executeUpdate("DROP TABLE " + tableName);
		return true;
	}
}
*//*

=======
}
>>>>>>> f4fd8e79be1dd4bd2706adbd83ee1ce56cb1aa70
*/
