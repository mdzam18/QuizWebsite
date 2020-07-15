package ProfilePackage;

import ProfilePackage.*;

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
    public HistorySqlDao() {
        tableName = CreateTablesForTests.HistoryTableTest;

        try {
            connection = ProfileDataSrc.getConnection("test", "root", "01234567");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addToHistory(History history) {
        return addToHistory(history.getUserId(), history.getQuizId(), history.getScore(),
                history.getStartDate(), history.getEndDate());
    }

    @Override
    public boolean addToHistory(int userId, int quizId, int score, Date startDate, Date endDate) {
        try {
            String sqlString = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?);";

            PreparedStatement prepState = connection.prepareStatement(sqlString);
            prepState.setInt(USER_ID_COL, userId);
            prepState.setInt(QUIZ_ID_COL, quizId);
            prepState.setInt(SCORE_COL, score);
            prepState.setDate(START_DATE_COL, new java.sql.Date(startDate.getTime()));
            prepState.setDate(END_DATE_COL, new java.sql.Date(endDate.getTime()));
            prepState.execute();
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<History> getHistories(int userId) {
        List<History> histories = new ArrayList<>();
        try {
            String sqlString = "SELECT * FROM " + tableName + " WHERE UserId = ?;";
            PreparedStatement prepState = connection.prepareStatement(sqlString);
            prepState.setInt(1, userId);
            ResultSet rset = prepState.executeQuery();
            while(rset.next()) {
                History history = new History(
                        rset.getInt(USER_ID_COL),
                        rset.getInt(QUIZ_ID_COL),
                        rset.getInt(SCORE_COL),
                        new Date(rset.getDate(START_DATE_COL).getTime()),
                        new Date(rset.getDate(END_DATE_COL).getTime()));
                histories.add(history);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return histories;
    }

    @Override
    public List<History> getHistoriesByQuizId(int quizId) {
        List<History> histories = new ArrayList<>();
        try {
            String sqlString = "SELECT * FROM " + tableName + " WHERE QuizId = ?;";
            PreparedStatement prepState = connection.prepareStatement(sqlString);
            prepState.setInt(1, quizId);
            ResultSet rset = prepState.executeQuery();
            while(rset.next()) {
                History history = new History(
                        rset.getInt(USER_ID_COL),
                        rset.getInt(QUIZ_ID_COL),
                        rset.getInt(SCORE_COL),
                        new Date(rset.getDate(START_DATE_COL).getTime()),
                        new Date(rset.getDate(END_DATE_COL).getTime()));
                histories.add(history);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return histories;
    }

    @Override
    public Set<Integer> getQuizIds(int userId) {
        Set<Integer> quizIds = new HashSet<>();
        try {
            String sqlString = "SELECT QuizId From " + tableName + " WHERE UserId = ?;";
            PreparedStatement prepState = connection.prepareStatement(sqlString);
            prepState.setInt(1, userId);
            ResultSet rset = prepState.executeQuery();
            while(rset.next()) {
                quizIds.add(rset.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return quizIds;
    }

    @Override
    public Set<Integer> getUserIds() {
        Set<Integer> usersIds = new HashSet<>();
        try {
            String sqlString = "SELECT UserId FROM " + tableName + " GROUP BY UserId;";
            Statement state = connection.createStatement();
            ResultSet rset = state.executeQuery(sqlString);
            while(rset.next()) {
                usersIds.add(rset.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usersIds;
    }

    @Override
    public boolean removeFromHistories(int quizId) {
        try {
            String sqlString = "DELETE FROM " + tableName + " WHERE QuizId = ?;";
            PreparedStatement prepState = connection.prepareStatement(sqlString);
            prepState.setInt(1, quizId);
            prepState.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean containsUser(int userId) {
        return getUserIds().contains(userId);
    }

    @Override
    public boolean removeUser(int userId) {
        try {
            String sqlString = "DELETE FROM " + tableName + " WHERE UserId = ?;";
            PreparedStatement prepState = connection.prepareStatement(sqlString);
            prepState.setInt(1, userId);
            prepState.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    /* Sorting by Descent */
    public static List<History> sortByEndDate(List<History> histories) {
        ArrayList<History> list = new ArrayList<>(histories);
        list.sort(new Comparator<History>() {
            @Override
            public int compare(History hist1, History hist2) {
                return (int)(hist2.getEndDate().getTime() - hist1.getEndDate().getTime());
            }
        });
        return list;
    }

    public static List<History> sortByScore(List<History> histories) {
        ArrayList<History> list = new ArrayList<>(histories);
        list.sort(new Comparator<History>() {
            @Override
            public int compare(History hist1, History hist2) {
                return (int)(hist2.getScore() - hist1.getScore());
            }
        });
        return list;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

}
