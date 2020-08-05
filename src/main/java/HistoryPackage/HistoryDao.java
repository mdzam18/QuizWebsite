package HistoryPackage;

import Quiz.Quiz;
import UserPackage.User;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

public interface HistoryDao {

    List<String> forChallenge(int userId) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException;

    public boolean addToHistory(History history) throws SQLException;

    public boolean addToHistory(int userId, int quizId, int score, Date startDate, Date endDate) throws SQLException;

    public List<History> getHistories(int userId) throws SQLException;

    public List<History> getAllHistories() throws SQLException;

    public List<History> getHistoriesByQuizId(int quizId) throws SQLException;

    public Set<Integer> getQuizIds(int userId) throws SQLException;

    public Set<Integer> getUserIds() throws SQLException;

    public boolean removeFromHistories(int quizId) throws SQLException;

    public boolean removeAllHistories() throws SQLException;

    List<Quiz> getPopularQuizzes() throws SQLException, ClassNotFoundException;

    public boolean containsUser(int userId) throws SQLException;

    public boolean removeUser(int userId) throws SQLException;

    public void setTableName(String tableName);

    public String getTableName();

    public int getMaxScore(int userId, int quizId) throws SQLException;

    List<Quiz> getRecentlyTakenQuizzes(User user) throws SQLException, ClassNotFoundException;

}
