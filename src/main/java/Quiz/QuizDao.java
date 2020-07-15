package Quiz;

import java.sql.SQLException;

public interface QuizDao {
	
	public Quiz addQuiz(int creatorId) throws SQLException;
	
	public Quiz getQuiz(int quizId) throws SQLException;
	
	public boolean deleteQuiz(Quiz quiz) throws SQLException;

}
