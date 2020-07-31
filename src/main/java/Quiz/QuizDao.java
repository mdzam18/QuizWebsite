package Quiz;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface QuizDao {

	public Quiz addQuiz(int creatorId) throws SQLException;

	public Quiz getQuiz(int quizId) throws SQLException;

	public boolean deleteQuiz(Quiz quiz) throws SQLException;

	public Connection getConnection() throws SQLException;

    int getQuizId(int authorId, String description) throws SQLException;

    public int getQuizIdByName(String description) throws SQLException;

	boolean userHasQuizByName(int userId, String	quizName) throws SQLException;

	Quiz getQuizByCreatorAndName(int userId, String	quizName) throws SQLException;

	List<Quiz> getPopularQuizzes() throws SQLException;

	List<Quiz> getRecentlyCreatedQuizzes() throws SQLException;

	public List<Quiz> getRecentlyCreatedQuizzesByUser(int userId) throws SQLException;

	Quiz addQuiz(int creatorId, boolean isRandom, boolean isOnePage, boolean isImmediate,
						boolean hasPracticeMode, int questionNum, String quizName, String category, Date createDate) throws SQLException;

	public boolean setDescription(int quizId, String description) throws SQLException;

	public List<Quiz> getQuizzesForUser(int userId) throws SQLException;

	public List<Quiz> getAllQuizzes() throws SQLException;

}
