package Statistics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;
import Quiz.*;
import UserPackage.User;

public class StatisticsSqlDao implements StatisticsDao {
	private Connection con;
	private String userTable;
	private String quizTable;
	private String historyTable;
	
	public StatisticsSqlDao() throws SQLException, ClassNotFoundException {
		con = ProfileDataSrc.getConnection();
		userTable = CreateTablesForTests.UsersTable;
		quizTable = CreateTablesForTests.QuizTable;
		historyTable = CreateTablesForTests.HistoryTable;
	}
	
	public List<Quiz> getAllQuizzes(int userId) throws SQLException {
		List <Quiz> quizzes = new ArrayList<>();
		PreparedStatement stm =
				con.prepareStatement("SELECT * FROM " + historyTable + " WHERE UserId = ?;");
		stm.setInt(1, userId);
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			int quizId = rs.getInt(2);
			stm = con.prepareStatement("SELECT * FROM " + quizTable + " WHERE QuizId = ?;");
			stm.setInt(1, quizId);
			ResultSet rs2 = stm.executeQuery();
			rs2.next();
			Quiz quiz = new Quiz(rs2.getInt(1), rs2.getInt(9));
			quizzes.add(quiz);
		}
		return quizzes;
	}
	
	public List <Integer> getPastPerformances(int userId, int quizId) throws SQLException {
		List <Integer> result = new ArrayList<>();
		PreparedStatement stm =
				con.prepareStatement("SELECT * FROM " + historyTable + " WHERE UserId = ? AND QuizId = ?;");
		stm.setInt(1, userId);
		stm.setInt(2, quizId);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()){
			int score = rs.getInt(3);
			result.add(score);
		}
		return result;
	}
	
	public double getMaxScore(int userId, int quizId) throws SQLException {
		Integer max = null;
		PreparedStatement stm =
				con.prepareStatement("SELECT max(Score) FROM " + historyTable + " WHERE UserId = ? AND QuizId = ?;");
		stm.setInt(1, userId);
		stm.setInt(2, quizId);
		ResultSet rs = stm.executeQuery();
		if(rs.next()) {
			max = rs.getInt("max(Score)");
		}
		return max;
		
	}
	
	public User getBestPlayer(int quizId) throws SQLException {
		User user = null;
		long minTime = Long.MAX_VALUE;
		int maxScore = 0;
		int userId = 1;
		PreparedStatement stm =
				con.prepareStatement("SELECT * FROM " + historyTable + " WHERE QuizId = ?;");
		stm.setInt(1, quizId);
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			int score = rs.getInt(3);
			if(score >= maxScore){
				maxScore = score;
				long elapsedTime = rs.getDate(5).getTime() - rs.getDate(4).getTime();
				if(elapsedTime < minTime){
					userId = rs.getInt(1);
					minTime = elapsedTime;
				}
			}
		}
		stm = con.prepareStatement(
				"SELECT * FROM " + userTable + " WHERE UserId = ?;");
		stm.setInt(1, userId);
		ResultSet res = stm.executeQuery();
		if(res.next()){
			user = new User(res.getString(2), res.getInt(1), res.getString(3));
			user.setAdministrator(res.getBoolean(4));
			user.setName(res.getString(6));
			user.setSurname(res.getString(7));
			user.setBirthPlace(res.getString(8));
			user.setStatus(res.getString(9));
		}
		return user;
	}
	
	public double getAverageScore(int quizId) throws SQLException {
		double result = 0;
		PreparedStatement stm =
				con.prepareStatement("SELECT avg(Score) FROM " + historyTable + " WHERE QuizId = ?;");
		stm.setInt(1, quizId);
		ResultSet rs = stm.executeQuery();
		if(rs.next()) result = rs.getDouble("avg(Score)");
		return result;
	}
	
}
