package ProfilePackage;

import java.sql.SQLException;
import java.util.*;

public interface HistoryDao {

    public boolean addToHistory(History history) throws SQLException;

    public boolean addToHistory(int userId, int quizId, int score, Date startDate, Date endDate) throws SQLException;

    public List<History> getHistories(int userId) throws SQLException;

    public List<History> getHistoriesByQuizId(int quizId) throws SQLException;

    public Set<Integer> getQuizIds(int userId) throws SQLException;

    public Set<Integer> getUserIds() throws SQLException;

    public boolean removeFromHistories(int quizId) throws SQLException;

    public boolean containsUser(int userId) throws SQLException;

    public boolean removeUser(int userId) throws SQLException;

    public void setTableName(String tableName);

    public String getTableName();

}
