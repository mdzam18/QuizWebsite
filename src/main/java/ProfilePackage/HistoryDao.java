package ProfilePackage;

import java.util.*;

public interface HistoryDao {

    public boolean addToHistory(History history);

    public boolean addToHistory(int userId, int quizId, int score, Date startDate, Date endDate);

    public List<History> getHistories(int userId);

    public List<History> getHistoriesByQuizId(int quizId);

    public Set<Integer> getQuizIds(int userId);

    public Set<Integer> getUserIds();

    public boolean removeFromHistories(int quizId);

    public boolean containsUser(int userId);

    public boolean removeUser(int userId);

}
