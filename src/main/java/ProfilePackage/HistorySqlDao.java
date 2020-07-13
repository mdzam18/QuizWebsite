package ProfilePackage;

import ProfilePackage.*;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class HistorySqlDao implements HistoryDao {

    private Map<Integer, List<History>> data;
    private Connection connection;
    private boolean useTables;
    private static final int USER_ID_COL = 1;
    private static final int QUIZ_ID_COL = 2;
    private static final int SCORE_COL = 3;
    private static final int START_DATE_COL = 4;
    private static final int END_DATE_COL = 5;
    public final static String TABLE_NAME  = "History";  // TODO - add schema name

    public HistorySqlDao() {
        this(true);
    }

    /* Constructor For Testing */
    public HistorySqlDao(boolean useTables) {
        data = new HashMap<>();

        this.useTables = useTables;
        if(useTables) {
            try {
                connection = ProfileDataSrc.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            setUpTableData();   // Get data from table
        }
    }

    private void setUpTableData() {
        try {
            Statement state = connection.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM " + TABLE_NAME);

            while (result.next()){
                int userId = result.getInt(USER_ID_COL);
                int quizId = result.getInt(QUIZ_ID_COL);
                int score = result.getInt(SCORE_COL);
                Date startDate = result.getDate(START_DATE_COL);
                Date endDate = result.getDate(END_DATE_COL);

                addToData(userId, quizId, score, startDate, endDate);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void addToData(int userId, int quizId, int score, Date startDate, Date endDate) {
        if(!data.containsKey(userId)) {
            data.put(userId, new ArrayList<History>());
        }
        List<History> histories = data.get(userId);
        histories.add(new History(userId, quizId, score, startDate, endDate));
    }

    @Override
    public boolean addToHistory(History history) {
        return addToHistory(history.getUserId(), history.getQuizId(), history.getScore(),
                history.getStartDate(), history.getEndDate());
    }

    @Override
    public boolean addToHistory(int userId, int quizId, int score, Date startDate, Date endDate) {
        if(useTables) {
            try {
                String sqlString = "INSERT INTO " + TABLE_NAME + " VALUES(?, ?, ?, ?, ?);";

                PreparedStatement prepState = connection.prepareStatement(sqlString);
                prepState.setInt(1, userId);
                prepState.setInt(2, quizId);
                prepState.setInt(3, score);
                prepState.setDate(4, new java.sql.Date(startDate.getTime()));
                prepState.setDate(5, new java.sql.Date(endDate.getTime()));
                prepState.execute();
            } catch (Exception throwables) {
                throwables.printStackTrace();
                return false;
            }
        }

        addToData(userId, quizId, score, startDate, endDate);
        return true;
    }

    @Override
    public List<History> getHistories(int userId) {
        return data.get(userId);
    }

    @Override
    public List<History> getHistoriesByQuizId(int quizId) {
        ArrayList<History> histories = new ArrayList<>();
        for(List<History> list : data.values()) {
            for(History history : list) {
                if(history.getQuizId() == quizId) {
                    histories.add(history);
                }
            }
        }
        return histories;
    }

    @Override
    public Set<Integer> getQuizIds(int userId) {
        Set<Integer> quizIds = new HashSet<>();
        for(History history : data.get(userId)) {
            quizIds.add(history.getQuizId());
        }
        return quizIds;
    }

    @Override
    public Set<Integer> getUserIds() {
        return new HashSet<>(data.keySet());
    }

    @Override
    public void removeFromHistories(int quizId) {
        if(useTables) {
            try {
                String sqlString = "DELETE FROM " + TABLE_NAME + " WHERE QuizId = ?;";
                PreparedStatement prepState = connection.prepareStatement(sqlString);
                prepState.setInt(1, quizId);
                prepState.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        for(Integer userId : data.keySet()) {
            List<History> histories = data.get(userId);
            List<History> toRemove = new ArrayList<>();
            for(History history : histories) {
                if(history.getQuizId() == quizId) {
                    toRemove.add(history);
                }
            }
            histories.removeAll(toRemove);
        }
    }

    @Override
    public boolean containsUser(int userId) {
        return data.containsKey(userId);
    }

    @Override
    public void removeUser(int userId) {
        data.remove(userId);
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

}
