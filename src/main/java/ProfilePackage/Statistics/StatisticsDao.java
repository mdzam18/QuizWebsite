package ProfilePackage.Statistics;

import java.sql.SQLException;
import java.util.List;
import Quiz.*;
import UserPackage.User;


public interface StatisticsDao {
	
	public List<Quiz> getAllQuizzes(int userId) throws SQLException;
	
	public List<Integer> getPastPerformances(int userId, int quizId) throws SQLException;
	
	public double getMaxScore(int userId, int quizId) throws SQLException;
	
	public User getBestPlayer(int quizId) throws SQLException;
	
	public double getAverageScore(int quizId) throws SQLException;
	
}