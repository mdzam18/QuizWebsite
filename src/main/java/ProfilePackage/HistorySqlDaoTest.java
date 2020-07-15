package ProfilePackage;

import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HistorySqlDaoTest {

    private final long oneHour = 1000*60*60;
    private final long oneDay = 1000*60*60*24;// Number milliseconds in a day
    private CreateTablesForTests tables;

    /**
     * For Testing HistorySqlDao, update your database name in this class
     * **/

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
        historyDao = new HistorySqlDao();

        int userId = 12312;
        int quizId = 7221;
        int score = 71;
        Date startDate = new Date(System.currentTimeMillis() - oneHour);
        Date endDate = new Date(System.currentTimeMillis());
        History testHistory = new History(userId, quizId, score, startDate, endDate);

        historyDao.addToHistory(testHistory);

        assertTrue(historyDao.containsUser(userId));
        assertTrue(historyDao.getQuizIds(userId).contains(quizId));

        List<History> historyList = historyDao.getHistories(userId);
        assertEquals(historyList.size(), 1);

        History thisHistory = historyList.get(0);
        assertEquals(testHistory, thisHistory);

        historyDao.removeFromHistories(quizId);
        historyList = historyDao.getHistories(userId);
        assertTrue(historyList.isEmpty());

        historyDao.removeUser(userId);
        assertFalse(historyDao.containsUser(userId));
    }

    @Test
    void testHistorySqlDaoSorting() {
        historyDao = new HistorySqlDao();

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

    @BeforeEach
    void setUpDataBase() throws SQLException, ClassNotFoundException {
        tables = new CreateTablesForTests();
        assertTrue(tables.createUserTable());
        assertTrue(tables.createQuizTable());
        assertTrue(tables.createHistoryTable());
    }

    @AfterEach
    void dropDataBase() throws SQLException {
        assertTrue(tables.dropTable(CreateTablesForTests.HistoryTableTest));
        assertTrue(tables.dropTable(CreateTablesForTests.QuizTableTest));
        assertTrue(tables.dropTable(CreateTablesForTests.UsersTableTest));
    }

    /**
     * For Testing HistorySqlDao, update your database name in this class
     * **/

    @Test
    void testHistorySqlDaoMySql() {
        historyDao = new HistorySqlDao();

        List<History> histories = new ArrayList<>();
        int[] userIds = {12, 75, 41};
        int[] quizIds = {412, 800, 1020};

        for(int i = 0; i<3; i++) {
            int score = getScore();
            long start = System.currentTimeMillis() - 10*oneDay;
            long end = start + 6*oneDay;
            histories.add(new History(userIds[0], quizIds[0], score, new Date(start), new Date(end)));
        }
        for(int i = 0; i<5; i++) {
            int score = getScore();
            long start = System.currentTimeMillis() - 20*oneDay;
            long end = start + 19*oneDay;
            histories.add(new History(userIds[1], quizIds[1], score, new Date(start), new Date(end)));
        }
        for(int i = 0; i<4; i++) {
            int score = getScore();
            long start = System.currentTimeMillis() - 365*oneDay;
            long end = start + oneDay/24;
            histories.add(new History(userIds[2], quizIds[2], score, new Date(start), new Date(end)));
        }

        for(int i = 0; i<3; i++) {
            History h = histories.get(i);
            assertTrue(historyDao.addToHistory(h.getUserId(), h.getQuizId(), h.getScore(), h.getStartDate(), h.getEndDate()));
        }
        for(int i = 3; i<histories.size(); i++) {
            assertTrue(historyDao.addToHistory(histories.get(i)));
        }

        for(int i = 0; i<userIds.length; i++) {
            assertTrue(historyDao.getUserIds().contains(userIds[i]));
        }

        for(int i = 0; i<userIds.length; i++) {
            List<History> historiesResult = historyDao.getHistories(userIds[i]);
            for(History his: historiesResult) {
                assertEquals(his.getQuizId(), quizIds[i]);
            }
        }

        for(int i = 0; i<quizIds.length; i++) {
            List<History> historiesResult = historyDao.getHistoriesByQuizId(quizIds[i]);
            for(History his: historiesResult) {
                assertEquals(his.getUserId(), userIds[i]);
            }
        }

        for(int i = 0; i<quizIds.length; i++) {
            assertTrue(historyDao.removeFromHistories(quizIds[i]));
        }

        for(int i = 0; i<userIds.length; i++) {
            assertEquals(historyDao.getHistories(userIds[i]).size(), 0);
        }
    }

    @Test
    void testHistorySqlDaoMySql2() {
        historyDao = new HistorySqlDao();

        List<History> histories = new ArrayList<>();
        final int USER_NUM = 30, QUIZ_NUM = 5;
        int[] userIds = new int[USER_NUM];
        int[] quizIds = new int[QUIZ_NUM];
        for(int i = 0; i<USER_NUM; i++)
            userIds[i] = (1 + i);
        for(int i = 0; i<QUIZ_NUM; i++)
            quizIds[i] = (1001 + i);
        long startingDate = System.currentTimeMillis() - 1000*oneDay;

        for(int u = 0; u<USER_NUM; u++) {
            for(int q = 0; q<QUIZ_NUM; q++) {
                for(int a = 0; a<3; a++) {
                    Date start = new Date(startingDate);
                    Date end = new Date(startingDate + oneDay);
                    History his = new History(userIds[u], quizIds[q], getScore(), start, end);
                    histories.add(his);

                    startingDate += 2*oneDay;
                }
            }
        }

        for(int i = 0; i<histories.size(); i++) {
            assertTrue(historyDao.addToHistory(histories.get(i)));
        }

        for(int i = 0; i<userIds.length; i++) {
            assertTrue(historyDao.containsUser(userIds[i]));
        }

        Set<Integer> userIdsSetResult = historyDao.getUserIds();
        for(int i = 0; i<userIds.length; i++) {
            assertTrue(userIdsSetResult.contains(userIds[i]));
            Set<Integer> quizIdsSetResult = historyDao.getQuizIds(userIds[i]);
            for(int j = 0; j<quizIds.length; j++) {
                assertTrue(quizIdsSetResult.contains(quizIds[j]));
            }
        }

        List<History> allHistories = new ArrayList<>();
        for(int i = 0; i<userIds.length; i++) {
            allHistories.addAll(historyDao.getHistories(userIds[i]));
        }

        allHistories = HistorySqlDao.sortByEndDate(allHistories);
        Collections.reverse(allHistories);
        assertEquals(histories, allHistories);

        allHistories = HistorySqlDao.sortByScore(allHistories);

        for(int i = 0; i<allHistories.size() - 1; i++) {
            assertTrue(allHistories.get(i).getScore() >= allHistories.get(i + 1).getScore());
        }

        for(int i = 0; i<quizIds.length; i++) {
            List<History> curHistoris = historyDao.getHistoriesByQuizId(quizIds[i]);
            for(History his : curHistoris) {
                assertEquals(his.getQuizId(), quizIds[i]);
            }
        }

    }

    /* Some necessary methods */
    private int getScore() {
        return 1 + (int)(System.currentTimeMillis()%300);
    }

}