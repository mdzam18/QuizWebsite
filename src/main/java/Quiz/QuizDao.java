package Quiz;

import java.sql.Connection;
import java.sql.SQLException;

public interface QuizDao {
	
	public Quiz addQuiz(int creatorId) throws SQLException;
	
	public Quiz getQuiz(int quizId) throws SQLException;
	
	public boolean deleteQuiz(Quiz quiz) throws SQLException;

	public Connection getConnection() throws SQLException;

	public int getQuizIdByName(String description) throws SQLException;

}
