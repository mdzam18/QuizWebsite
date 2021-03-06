package HistoryPackage;

import ProfilePackage.*;
import Quiz.Quiz;
import Quiz.QuizSqlDao;
import UserPackage.User;
import UserPackage.UserSqlDao;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class HistorySqlDao implements HistoryDao {

    private static Connection connection;
    private static final int USER_ID_COL = 1;
    private static final int QUIZ_ID_COL = 2;
    private static final int SCORE_COL = 3;
    private static final int START_DATE_COL = 4;
    private static final int END_DATE_COL = 5;

    private String tableName;

    /* Constructor For Testing */
    public HistorySqlDao() throws SQLException, ClassNotFoundException {
        tableName = CreateTablesForTests.HistoryTable;
        connection = ProfileDataSrc.getConnection();
        //connection = NanukaDatabase.getConnection();
    }

    @Override
    public List<Quiz> getPopularQuizzes() throws SQLException, ClassNotFoundException {
        List<Quiz> res = new ArrayList<>();
        PreparedStatement stm = connection.prepareStatement("select * from " + tableName + " group by QuizId order by count(QuizId) DESC limit 5;");
        ResultSet rs = stm.executeQuery();
        QuizSqlDao qDao = new QuizSqlDao();
        while (rs.next()){
            int quizId = rs.getInt(2);
            Quiz q = qDao.getQuiz(quizId);
            res.add(q);
        }
        return res;
    }

    @Override
    public List<String> forChallenge(int userId) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        QuizSqlDao quizDao = new QuizSqlDao();
        UserSqlDao userDao = null;
        userDao = new UserSqlDao();
        Set<Integer> quizIds = getQuizIds(userId);
        List<String> result = new ArrayList<>();
        for (int quizId : quizIds) {
            Quiz quiz = quizDao.getQuiz(quizId);
            result.add(userDao.getUser(quiz.getCreatorId()).getUserName() + " - " + quiz.getDescription());
        }
        return result;
    }

    @Override
    public boolean addToHistory(History history) throws SQLException {
        return addToHistory(history.getUserId(), history.getQuizId(), history.getScore(),
                history.getStartDate(), history.getEndDate());
    }

    @Override
    public int getMaxScore(int userId, int quizId) throws SQLException {
        List<History> histories = getHistories(userId);
        int maxScore = -1;
        for (History history : histories) {
            if (history.getQuizId() == quizId && history.getScore() > maxScore) {
                maxScore = history.getScore();
            }
        }
        return maxScore;
    }

    @Override
    public boolean addToHistory(int userId, int quizId, int score, Date startDate, Date endDate) throws SQLException {
        String sqlString = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?);";

        PreparedStatement prepState = connection.prepareStatement(sqlString);
        prepState.setInt(USER_ID_COL, userId);
        prepState.setInt(QUIZ_ID_COL, quizId);
        prepState.setInt(SCORE_COL, score);
        prepState.setTimestamp(START_DATE_COL, new Timestamp(startDate.getTime()));
        prepState.setTimestamp(END_DATE_COL, new Timestamp(endDate.getTime()));
        prepState.execute();
        return true;
    }

    @Override
    public List<History> getHistories(int userId) throws SQLException {
        List<History> histories = new ArrayList<>();
        String sqlString = "SELECT * FROM " + tableName + " WHERE UserId = ?;";
        PreparedStatement prepState = connection.prepareStatement(sqlString);
        prepState.setInt(1, userId);
        ResultSet rset = prepState.executeQuery();
        while (rset.next()) {
            History history = new History(
                    rset.getInt(USER_ID_COL),
                    rset.getInt(QUIZ_ID_COL),
                    rset.getInt(SCORE_COL),
                    new Date(rset.getTimestamp(START_DATE_COL).getTime()),
                    new Date(rset.getTimestamp(END_DATE_COL).getTime()));
            histories.add(history);
        }
        return histories;
    }

    @Override
    public List<History> getAllHistories() throws SQLException {
        String sql = "SELECT * FROM " + tableName + ";";
        Statement state = connection.createStatement();

        List<History> histories = new ArrayList<>();
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            History history = new History(
                    results.getInt(USER_ID_COL),
                    results.getInt(QUIZ_ID_COL),
                    results.getInt(SCORE_COL),
                    new Date(results.getTimestamp(START_DATE_COL).getTime()),
                    new Date(results.getTimestamp(END_DATE_COL).getTime()));
            histories.add(history);
        }
        return histories;
    }

    @Override
    public List<History> getHistoriesByQuizId(int quizId) throws SQLException {
        List<History> histories = new ArrayList<>();
        String sqlString = "SELECT * FROM " + tableName + " WHERE QuizId = ?;";
        PreparedStatement prepState = connection.prepareStatement(sqlString);
        prepState.setInt(1, quizId);
        ResultSet rset = prepState.executeQuery();
        while (rset.next()) {
            History history = new History(
                    rset.getInt(USER_ID_COL),
                    rset.getInt(QUIZ_ID_COL),
                    rset.getInt(SCORE_COL),
                    new Date(rset.getTimestamp(START_DATE_COL).getTime()),
                    new Date(rset.getTimestamp(END_DATE_COL).getTime()));
            histories.add(history);
        }
        return histories;
    }

    @Override
    public List<History> getUsersHistoryForQuiz(int userId, int quizId) throws SQLException {
        List<History> histories = new ArrayList<>();
        String sql = "select * from " + tableName + " where UserId = ? and QuizId = ?;";
        PreparedStatement prepState = connection.prepareStatement(sql);
        prepState.setInt(1, userId);
        prepState.setInt(2, quizId);
        ResultSet resultSet = prepState.executeQuery();
        while (resultSet.next()) {
            History history = new History(
                    resultSet.getInt(USER_ID_COL),
                    resultSet.getInt(QUIZ_ID_COL),
                    resultSet.getInt(SCORE_COL),
                    new Date(resultSet.getTimestamp(START_DATE_COL).getTime()),
                    new Date(resultSet.getTimestamp(END_DATE_COL).getTime()));
            histories.add(history);
        }
        return histories;
    }

    @Override
    public Set<Integer> getQuizIds(int userId) throws SQLException {
        Set<Integer> quizIds = new HashSet<>();
        String sqlString = "SELECT QuizId From " + tableName + " WHERE UserId = ?;";
        PreparedStatement prepState = connection.prepareStatement(sqlString);
        prepState.setInt(1, userId);
        ResultSet rset = prepState.executeQuery();
        while (rset.next()) {
            quizIds.add(rset.getInt(1));
        }
        return quizIds;
    }

    @Override
    public Set<Integer> getUserIds() throws SQLException {
        Set<Integer> usersIds = new HashSet<>();
        String sqlString = "SELECT UserId FROM " + tableName + " GROUP BY UserId;";
        Statement state = connection.createStatement();
        ResultSet rset = state.executeQuery(sqlString);
        while (rset.next()) {
            usersIds.add(rset.getInt(1));
        }
        return usersIds;
    }

    @Override
    public boolean removeFromHistories(int quizId) throws SQLException {
        String sqlString = "DELETE FROM " + tableName + " WHERE QuizId = ?;";
        PreparedStatement prepState = connection.prepareStatement(sqlString);
        prepState.setInt(1, quizId);
        prepState.execute();
        return true;
    }

    @Override
    public boolean removeAllHistories() throws SQLException {
        Statement state = connection.createStatement();
        state.executeUpdate("DELETE FROM " + tableName);
        return true;
    }

    @Override
    public boolean containsUser(int userId) throws SQLException {
        return getUserIds().contains(userId);
    }

    @Override
    public boolean removeUser(int userId) throws SQLException {
        String sqlString = "DELETE FROM " + tableName + " WHERE UserId = ?;";
        PreparedStatement prepState = connection.prepareStatement(sqlString);
        prepState.setInt(1, userId);
        prepState.execute();
        return true;
    }

    /* Sorting by Descent */
    public static List<History> sortByEndDate(List<History> histories) {
        ArrayList<History> list = new ArrayList<>(histories);
        list.sort(new Comparator<History>() {
            @Override
            public int compare(History hist1, History hist2) {
                return (int) (hist2.getEndDate().getTime() - hist1.getEndDate().getTime());
            }
        });
        return list;
    }

    public static List<History> sortByScore(List<History> histories) {
        ArrayList<History> list = new ArrayList<>(histories);
        list.sort(new Comparator<History>() {
            @Override
            public int compare(History hist1, History hist2) {
                return (int) (hist2.getScore() - hist1.getScore());
            }
        });
        return list;
    }

    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<Quiz> getRecentlyTakenQuizzes(User user) throws SQLException, ClassNotFoundException {
        List<History> histories = getHistories(user.getUserId());
        histories = HistorySqlDao.sortByEndDate(histories);
        List<Quiz> res = new ArrayList<>();
        QuizSqlDao qDao = new QuizSqlDao();
        int n = 0;
        for (int i = histories.size() - 1; i >= 0; i--) {
            if (n == 5) break;
            if (histories.size() >= 5) {
                n++;
            }
            res.add(qDao.getQuiz(histories.get(i).getQuizId()));
        }
        return res;
    }

}
