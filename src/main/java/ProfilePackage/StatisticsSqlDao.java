/*
package ProfilePackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Quiz.*;

public class StatisticsSqlDao implements StatisticsDao {
	private Connection con;
	private static final String userTable = "Users2";
	private static final String quizTable = "Quiz2";
	private static final String historyTable = "History2";
	
	public StatisticsSqlDao() throws SQLException, ClassNotFoundException {
		con = ProfileDataSrc.getConnection();
	}
	
	public Connection getConnection() {
		return con;
	}
	
	public List<Quiz> getAllQuizzes(int userId) throws SQLException {
		List <Quiz> quizzes = new ArrayList<>();
		PreparedStatement stm =
				con.prepareStatement("SELECT * FROM " + historyTable + " WHERE UserId = ?;");
		stm.setInt(1, userId);
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			int quizId = rs.getInt(1);
			stm = con.prepareStatement("SELECT * FROM " + quizTable + " WHERE QuizId = ?;");
			stm.setInt(1, quizId);
//			String id, boolean isRandom,
//			boolean isOnePage, boolean isImmedate,
//			int questionCount, String creator,
//			String description, List<Question> questions,
//			String category, Set<String> tags)
//			Quiz quiz =
//					new Quiz(rs.getString(1), rs.getBoolean(2), rs.getBoolean(3), rs.getBoolean(4));
//			////????
		}
		return null;
		
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
	
	public Integer getMaxScore(int userId, int quizId) throws SQLException {
		Integer max = null;
		PreparedStatement stm =
				con.prepareStatement("SELECT max(Score) FROM " + historyTable + " WHERE UserId = ? AND QuizId = ?;");
		stm.setInt(1, userId);
		stm.setInt(2, quizId);
		ResultSet rs = stm.executeQuery();
		if(rs.next()) max = rs.getInt(3);
		return max;
		
	}

	public User getBestPlayer(int quizId) throws SQLException {
		PreparedStatement stm =
				con.prepareStatement("SELECT max(Score) FROM " + historyTable + " WHERE QuizId = ?;");
		stm.setInt(2, quizId);
		ResultSet rs = stm.executeQuery();

		while(rs.next()){

		}
	}

	public User getBestPerformance(int quizId){

	}

	
}*/
