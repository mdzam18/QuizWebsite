package ProfilePackage;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HistorySqlDaoTest {

    /* Testing History Class */
    @Test
    void testHistory() {
        int userId = 12076;
        int quizId = 6852;
        int score = 102;
        Date startDate = new Date(System.currentTimeMillis());
        Date endDate = new Date(System.currentTimeMillis());

        History testHistory = new History(userId, quizId, score, startDate, endDate);

        assertEquals(testHistory.getUserId(), userId);
        assertEquals(testHistory.getQuizId(), quizId);
        assertEquals(testHistory.getScore(), score);
        assertEquals(testHistory.getStartDate(), startDate);
        assertEquals(testHistory.getEndDate(), endDate);

        int newUserId = 8432;
        int newQuizId = 750121;
        int newScore = 83451;
        Date newStartDate = new Date(System.currentTimeMillis() - 4*(1000*60*60*24));
        Date newEndDate = new Date(System.currentTimeMillis() - 15*(1000*60*60));

        testHistory.setUserId(newUserId);
        testHistory.setQuizId(newQuizId);
        testHistory.setScore(newScore);
        testHistory.setStartDate(newStartDate);
        testHistory.setEndDate(newEndDate);

        assertEquals(testHistory.getUserId(), newUserId);
        assertEquals(testHistory.getQuizId(), newQuizId);
        assertEquals(testHistory.getScore(), newScore);
        assertEquals(testHistory.getStartDate(), newStartDate);
        assertEquals(testHistory.getEndDate(), newEndDate);
    }

    /* Testing HistorySqlDao Class */
    private HistoryDao historyDao;

    @Test
    void testHistorySqlDao() {
        historyDao = new HistorySqlDao(false);

        int userId = 12312;
        int quizId = 7221;
        int score = 71;
        Date startDate = new Date(System.currentTimeMillis() - 1000*60*60);
        Date endDate = new Date(System.currentTimeMillis());
        History testHistory = new History(userId, quizId, score, startDate, endDate);

        historyDao.addToHistory(testHistory);

        assertTrue(historyDao.containsUser(userId));
        assertTrue(historyDao.getUserIds().contains(userId));
        assertTrue(historyDao.getQuizIds(userId).contains(quizId));

        List<History> historyList = null;
        historyList = historyDao.getHistories(userId);
        assertEquals(historyList.size(), 1);

        History thisHistory = historyList.get(0);
        assertEquals(testHistory, thisHistory);

        System.out.println(historyList);
        historyDao.removeFromHistories(quizId);
        historyList = historyDao.getHistories(userId);
        assertTrue(historyList.isEmpty());

        historyDao.removeUser(userId);
        assertFalse(historyDao.containsUser(userId));
    }

    @Test
    void testHistorySqlDaoSorting() {
        historyDao = new HistorySqlDao(false);

        long oneDay = 1000*60*60*24;// Number milliseconds in a day
        int userId = 1241;
        int quizId = 746;
        int score = 1001, score2 = 120, score3 = 312;
        Date startDate = new Date(System.currentTimeMillis() - 100*oneDay);
        Date endDate, endDate2, endDate3;
        endDate = new Date(startDate.getTime() + 10*oneDay);
        endDate2 = new Date(startDate.getTime() + 70*oneDay);
        endDate3 = new Date(startDate.getTime() + 20*oneDay);

        History history, history2, history3;
        history = new History(userId, quizId, score, startDate, endDate);
        history2 = new History(userId, quizId, score2, startDate, endDate2);
        history3 = new History(userId, quizId, score3, startDate, endDate3);

        List<History> notOrdered = Arrays.asList(history, history2, history3);

        List<History> byDate = HistorySqlDao.sortByEndDate(notOrdered);
        List<History> byScore = HistorySqlDao.sortByScore(notOrdered);

        List<History> byDateActual = Arrays.asList(history2, history3, history);
        List<History> byScoreActual = Arrays.asList(history, history3, history2);

        assertEquals(byDateActual, byDate);
        assertEquals(byScoreActual, byScore);
    }

    @Test
    void testHistorySqlDaoMySql() {
        // TODO
    }

}