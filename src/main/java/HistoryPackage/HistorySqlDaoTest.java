package HistoryPackage;

import ProfilePackage.CreateTablesForTests;
import Quiz.*;
import UserPackage.*;
import org.junit.jupiter.api.*;

import javax.jws.soap.SOAPBinding;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HistorySqlDaoTest {

    private final long ONE_HOUR = 1000 * 60 * 60;    // Number milliseconds in an Hour
    private final long ONE_DAY = 24 * ONE_HOUR;  // Number milliseconds in a Day
    private CreateTablesForTests tables;

    /* Testing HistorySqlDao Class */
    private HistoryDao historyDao;
    private UserSqlDao userDao;
    private QuizSqlDao quizDao;
    private QuestionDao qDao;

    private String reName;

    /**
     * For Testing HistorySqlDao, update your database name in this class
     **/

    @BeforeEach
    void setUpDataBase() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        reName = CreateTablesForTests.UsersTable;
        CreateTablesForTests.UsersTable = CreateTablesForTests.UsersTableTest;
        CreateTablesForTests.HistoryTable = CreateTablesForTests.HistoryTableTest;
        CreateTablesForTests.QuizTable = CreateTablesForTests.QuizTableTest;
        CreateTablesForTests.QuestionTable = CreateTablesForTests.QuestionTableTest;
        tables = new CreateTablesForTests();

        assertTrue(tables.createUserTable());
        assertTrue(tables.createQuizTable());
        assertTrue(tables.createHistoryTable());
        assertTrue(tables.createQuestionTable());

        userDao = new UserSqlDao();
        quizDao = new QuizSqlDao();
        historyDao = new HistorySqlDao();
        qDao = new QuestionDao();
    }

    @AfterEach
    void dropDataBase() throws SQLException {
        assertTrue(tables.dropTable(CreateTablesForTests.QuestionTableTest));
        assertTrue(tables.dropTable(CreateTablesForTests.HistoryTableTest));
        assertTrue(tables.dropTable(CreateTablesForTests.QuizTableTest));
        assertTrue(tables.dropTable(CreateTablesForTests.UsersTableTest));
        CreateTablesForTests.UsersTable = reName;
        CreateTablesForTests.HistoryTable = "History";
        CreateTablesForTests.QuizTable = "Quiz";
        CreateTablesForTests.QuestionTable = "Question";
    }

    @Test
    void testMaxScore() throws SQLException {
        userDao.addUser("sasuke", "uchiha", true);
        Quiz quiz = quizDao.addQuiz(1, false, false, false,
                false, 2, "narutoQuiz", "hard", new java.sql.Date(10, 12, 12));

        quiz = quizDao.addQuiz(1, false, false, false,
                false, 2, "narutoQuiz", "hard", new java.sql.Date(10, 12, 12));
        Date newStartDate = new Date(getModuloCurrentTime() - 4 * ONE_DAY);
        Date newEndDate = new Date(getModuloCurrentTime() - 15 * ONE_HOUR);
        historyDao.addToHistory(1, 1, 100, newStartDate, newEndDate);
        assertEquals(historyDao.getMaxScore(1, 1), 100);

        historyDao.addToHistory(1, 1, 90, newStartDate, newEndDate);
        assertEquals(historyDao.getMaxScore(1, 1), 100);

        historyDao.addToHistory(1, 2, 100, newStartDate, newEndDate);
        assertEquals(historyDao.getMaxScore(1, 1), 100);

        historyDao.addToHistory(1, 1, 150, newStartDate, newEndDate);
        assertEquals(historyDao.getMaxScore(1, 1), 150);
    }

    @Test
    void testGetAllHistories() throws SQLException {
        userDao.addUser("sasuke", "uchiha", true);
        Quiz quiz = quizDao.addQuiz(1, false, false, false,
                false, 2, "narutoQuiz", "hard", new java.sql.Date(10, 12, 12));

        Date newStartDate = new Date(getModuloCurrentTime() - 4 * ONE_DAY);
        Date newEndDate = new Date(getModuloCurrentTime() - 15 * ONE_HOUR);
        List<History> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            historyDao.addToHistory(1, 1, i, newStartDate, newEndDate);
            list.add(new History(1, 1, i, newStartDate, newEndDate));
        }
        assertEquals(list, historyDao.getAllHistories());
    }

    @Test
    void testRemoveHistory() throws SQLException {
        userDao.addUser("sasuke", "uchiha", true);
        Quiz quiz = quizDao.addQuiz(1, false, false, false,
                false, 2, "narutoQuiz", "hard", new java.sql.Date(10, 12, 12));

        userDao.addUser("kakashi", "hatake", false);
        quiz = quizDao.addQuiz(2, false, false, false,
                false, 2, "narutoQuiz", "hard", new java.sql.Date(10, 12, 12));
        Date newStartDate = new Date(getModuloCurrentTime() - 4 * ONE_DAY);
        Date newEndDate = new Date(getModuloCurrentTime() - 15 * ONE_HOUR);
        historyDao.addToHistory(1, 1, 100, newStartDate, newEndDate);
        historyDao.addToHistory(2, 2, 100, newStartDate, newEndDate);
        List<History> list = historyDao.getAllHistories();
        assertEquals(list.size(), 2);

        historyDao.removeAllHistories();
        list = historyDao.getAllHistories();
        assertEquals(list.size(), 0);
    }

    @Test
    void testRecentlyTakenQuizzes() throws SQLException, ClassNotFoundException {
        User user = userDao.addUser("Jon Snow", "Night'sWatch", true);
        Quiz quiz = quizDao.addQuiz(1, false, false, false,
                false, 2, "ThroneQuiz", "hard", new java.sql.Date(10, 12, 12));
        Date newStartDate = new Date(getModuloCurrentTime() - 4 * ONE_DAY);
        Date newEndDate = new Date(getModuloCurrentTime() - 15 * ONE_HOUR);
        historyDao.addToHistory(1, 1, 100, newStartDate, newEndDate);
        List<Quiz> list = historyDao.getRecentlyTakenQuizzes(user);
        assertEquals(list.size(), 1);

        for (int i = 2; i < 6; i++) {
            quizDao.addQuiz(1, false, false, false, false, 2, "ThroneQuiz", "hard", new java.sql.Date(10, 12, 12));
            historyDao.addToHistory(1, i, 100, newStartDate, newEndDate);
            list = historyDao.getRecentlyTakenQuizzes(user);
            assertEquals(list.size(), i);
        }

        for (int i = 6; i < 10; i++) {
            quizDao.addQuiz(1, false, false, false, false, 2, "ThroneQuiz", "hard", new java.sql.Date(10, 12, 12));
            historyDao.addToHistory(1, i, 100, newStartDate, newEndDate);
            list = historyDao.getRecentlyTakenQuizzes(user);
            assertEquals(list.size(), 5);
        }
    }

    @Test
    void testChallenge() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        User user = userDao.addUser("Forrest Gump", "Runforrest!", false);
        Quiz quiz = quizDao.addQuiz(1, false, false, false,
                false, 2, "ForrestQuizzes", "hard", new java.sql.Date(10, 12, 12));
        Date newStartDate = new Date(getModuloCurrentTime() - 4 * ONE_DAY);
        Date newEndDate = new Date(getModuloCurrentTime() - 15 * ONE_HOUR);
        historyDao.addToHistory(1, 1, 100, newStartDate, newEndDate);
        List<String> list = historyDao.forChallenge(user.getUserId());
        assertEquals(list.size(), 1);

        quiz = quizDao.addQuiz(1, false, false, false,
                false, 2, "ForrestQuizzes", "hard", new java.sql.Date(10, 12, 12));
        historyDao.addToHistory(1, 2, 100, newStartDate, newEndDate);
        list = historyDao.forChallenge(user.getUserId());
        assertEquals(list.size() , 2);
    }

    @Test
    void testSetTableName() {
        historyDao.setTableName("tableN2");
        assertEquals(historyDao.getTableName(), "tableN2");
        historyDao.setTableName(CreateTablesForTests.HistoryTable);
    }

    /* Testing History Class */
    @Test
    void testHistory() throws SQLException {
        int userId = 1;
        int quizId = 1;
        int score = 100;
        Date startDate = new Date(getModuloCurrentTime());
        Date endDate = new Date(getModuloCurrentTime());

        userDao.addUser("Java", "Servlet", false);
        quizDao.addQuiz(userId, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));

        History testHistory = new History(userId, quizId, score, startDate, endDate);

        assertEquals(testHistory.getUserId(), userId);
        assertEquals(testHistory.getQuizId(), quizId);
        assertEquals(testHistory.getScore(), score);
        assertEquals(testHistory.getStartDate(), startDate);
        assertEquals(testHistory.getEndDate(), endDate);

        int newUserId = 2;
        int newQuizId = 2;
        int newScore = 200;
        Date newStartDate = new Date(getModuloCurrentTime() - 4 * ONE_DAY);
        Date newEndDate = new Date(getModuloCurrentTime() - 15 * ONE_HOUR);

        userDao.addUser("ABC", "DEF", false);
        quizDao.addQuiz(newUserId, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));

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

        assertTrue(testHistory.toString().contains(String.valueOf(newUserId)));
        assertTrue(testHistory.toString().contains(String.valueOf(newQuizId)));
        assertTrue(testHistory.toString().contains(String.valueOf(newScore)));

        assertEquals(testHistory.hashCode(), testHistory.hashCode());
    }

    @Test
    void testHistorySqlDaoSorting() {
        int userId = 1;
        int quizId = 1;
        int score = 1001, score2 = 120, score3 = 312;
        Date startDate = new Date(System.currentTimeMillis() - 100 * ONE_DAY);
        Date endDate, endDate2, endDate3;
        endDate = new Date(startDate.getTime() + 10 * ONE_DAY);
        endDate2 = new Date(startDate.getTime() + 70 * ONE_DAY);
        endDate3 = new Date(startDate.getTime() + 20 * ONE_DAY);

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
    void testHistorySqlDao2() throws SQLException {
        int userId = 1;
        int quizId = 1;
        int score = 200;
        Date startDate = new Date(getModuloCurrentTime() - ONE_HOUR);
        Date endDate = new Date(getModuloCurrentTime());

        userDao.addUser("test", "testpass", false);
        quizDao.addQuiz(userId, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));

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
    void testHistorySqlDao3() throws SQLException {
        historyDao.setTableName(historyDao.getTableName());

        List<History> histories = new ArrayList<>();
        int[] userIds = {1, 2, 3};
        int[] quizIds = {1, 2, 3};

        for (int i = 0; i < userIds.length; i++) {
            String username = "Bla" + String.valueOf(userIds[i]);
            assertNotNull(userDao.addUser(username, "Foo", true));
        }
        for (int i = 0; i < userIds.length; i++) {
            assertNotNull(userDao.getUser(userIds[i]));
        }

        for (int i = 0; i < quizIds.length; i++) {
            quizDao.addQuiz(userIds[0], false, false, false,
                    false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        }
        for (int i = 0; i < quizIds.length; i++) {
            assertNotNull(quizDao.getQuiz(quizIds[i]));
        }

        for (int i = 0; i < 3; i++) {
            int score = getScore();
            long start = getModuloCurrentTime() - 10 * ONE_DAY;
            long end = start + 6 * ONE_DAY;
            histories.add(new History(userIds[0], quizIds[0], score, new Date(start), new Date(end)));
        }
        for (int i = 0; i < 5; i++) {
            int score = getScore();
            long start = getModuloCurrentTime() - 20 * ONE_DAY;
            long end = start + 19 * ONE_DAY;
            histories.add(new History(userIds[1], quizIds[1], score, new Date(start), new Date(end)));
        }
        for (int i = 0; i < 4; i++) {
            int score = getScore();
            long start = getModuloCurrentTime() - 365 * ONE_DAY;
            long end = start + ONE_DAY / 24;
            histories.add(new History(userIds[2], quizIds[2], score, new Date(start), new Date(end)));
        }

        for (int i = 0; i < 3; i++) {
            History h = histories.get(i);
            assertTrue(historyDao.addToHistory(h.getUserId(), h.getQuizId(), h.getScore(), h.getStartDate(), h.getEndDate()));
        }
        for (int i = 3; i < histories.size(); i++) {
            assertTrue(historyDao.addToHistory(histories.get(i)));
        }

        for (int i = 0; i < userIds.length; i++) {
            assertTrue(historyDao.getUserIds().contains(userIds[i]));
        }

        for (int i = 0; i < userIds.length; i++) {
            List<History> historiesResult = historyDao.getHistories(userIds[i]);
            for (History his : historiesResult) {
                assertEquals(his.getQuizId(), quizIds[i]);
            }
        }

        for (int i = 0; i < quizIds.length; i++) {
            List<History> historiesResult = historyDao.getHistoriesByQuizId(quizIds[i]);
            for (History his : historiesResult) {
                assertEquals(his.getUserId(), userIds[i]);
            }
        }

        for (int i = 0; i < quizIds.length; i++) {
            assertTrue(historyDao.removeFromHistories(quizIds[i]));
        }

        for (int i = 0; i < userIds.length; i++) {
            assertEquals(historyDao.getHistories(userIds[i]).size(), 0);
        }
    }

    @Test
    public void testPopularQuizzes() throws SQLException, ClassNotFoundException {
        userDao.addUser("a", "b", false);
        userDao.addUser("b", "b", false);
        userDao.addUser("c" ,"a" ,false);
        quizDao.addQuiz(1, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        quizDao.addQuiz(2, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        quizDao.addQuiz(3, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        Date newStartDate = new Date(getModuloCurrentTime() - 4 * ONE_DAY);
        Date newEndDate = new Date(getModuloCurrentTime() - 15 * ONE_HOUR);
        for(int i = 1; i < 4; i++){
            historyDao.addToHistory(i, 1, 100, newStartDate, newEndDate);
        }
        List<Quiz> list = historyDao.getPopularQuizzes();
        assertEquals(list.size(), 1);
    }

    @Test
    void testHistorySqlDao4() throws SQLException {
        List<History> histories = new ArrayList<>();
        final int USER_NUM = 30, QUIZ_NUM = 5;
        int[] userIds = new int[USER_NUM];
        int[] quizIds = new int[QUIZ_NUM];
        for (int i = 0; i < USER_NUM; i++) {
            userIds[i] = (1 + i);
            String username = "BlaBla" + String.valueOf(userIds[i]);
            assertNotNull(userDao.addUser(username, "FooFoo", false));
        }
        for (int i = 0; i < QUIZ_NUM; i++) {
            quizIds[i] = (1 + i);
            quizDao.addQuiz(userIds[0], false, false, false,
                    false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        }

        long startingDate = System.currentTimeMillis() - 1000 * ONE_DAY;

        for (int u = 0; u < USER_NUM; u++) {
            for (int q = 0; q < QUIZ_NUM; q++) {
                for (int a = 0; a < 3; a++) {
                    Date start = new Date(startingDate);
                    Date end = new Date(startingDate + ONE_DAY);
                    History his = new History(userIds[u], quizIds[q], getScore(), start, end);
                    histories.add(his);

                    startingDate += 2 * ONE_DAY;
                }
            }
        }

        for (int i = 0; i < histories.size(); i++) {
            assertTrue(historyDao.addToHistory(histories.get(i)));
        }

        for (int i = 0; i < userIds.length; i++) {
            assertTrue(historyDao.containsUser(userIds[i]));
        }

        Set<Integer> userIdsSetResult = historyDao.getUserIds();
        for (int i = 0; i < userIds.length; i++) {
            assertTrue(userIdsSetResult.contains(userIds[i]));
            Set<Integer> quizIdsSetResult = historyDao.getQuizIds(userIds[i]);
            for (int j = 0; j < quizIds.length; j++) {
                assertTrue(quizIdsSetResult.contains(quizIds[j]));
            }
        }

        List<History> allHistories = new ArrayList<>();
        for (int i = 0; i < userIds.length; i++) {
            allHistories.addAll(historyDao.getHistories(userIds[i]));
        }

        allHistories = HistorySqlDao.sortByEndDate(allHistories);
        Collections.reverse(allHistories);
        assertEquals(histories, allHistories);

        allHistories = HistorySqlDao.sortByScore(allHistories);

        for (int i = 0; i < allHistories.size() - 1; i++) {
            assertTrue(allHistories.get(i).getScore() >= allHistories.get(i + 1).getScore());
        }

        for (int i = 0; i < quizIds.length; i++) {
            List<History> curHistories = historyDao.getHistoriesByQuizId(quizIds[i]);
            for (History his : curHistories) {
                assertEquals(his.getQuizId(), quizIds[i]);
            }
        }
    }

    /* Some necessary methods */
    private int getScore() {
        return 1 + (int) (System.currentTimeMillis() % 300);
    }

    private long getModuloCurrentTime() {
        long curr = System.currentTimeMillis();
        return (curr - curr % 1000);
    }

}
