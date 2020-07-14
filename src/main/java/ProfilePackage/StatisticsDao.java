package ProfilePackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import Quiz.*;


public interface StatisticsDao {
	
	public Connection getConnection();
	
	public List<Quiz> getAllQuizzes(int userId) throws SQLException;
	
	public List<Integer> getPastPerformances(int userId, int quizId) throws SQLException;
	
	public Integer getMaxScore(int userId, int quizId) throws SQLException;
	
	public User getBestPlayer(int quizId) throws SQLException;
	
	public User getBestPerformance(int quizId);
	
}