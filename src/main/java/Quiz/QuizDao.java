package Quiz;

import java.sql.Connection;
import java.sql.SQLException;

public interface QuizDao {
	
	public Quiz addQuiz(int creatorId) throws SQLException;
	
	public Quiz getQuiz(int quizId) throws SQLException;
	
	public boolean deleteQuiz(Quiz quiz) throws SQLException;

	public Connection getConnection() throws SQLException;

	public int getQuizIdByName(String description) throws SQLException;

	public boolean userHasQuizByName(int userId, String	quizName) throws SQLException;
	
	List<Quiz> getPopularQuizzes() throws SQLException;

    	List<Quiz> recentlyCreatedQuizzes() throws SQLException;

    	List<Quiz> recentlyTakenQuizzes() throws SQLException;

}
