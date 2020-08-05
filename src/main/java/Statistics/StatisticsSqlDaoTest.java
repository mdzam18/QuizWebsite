package Statistics;

import ProfilePackage.CreateTablesForTests;
import HistoryPackage.HistoryDao;
import HistoryPackage.HistorySqlDao;
import ProfilePackage.ProfileDataSrc;
import Quiz.*;
import UserPackage.UserDao;
import UserPackage.UserSqlDao;
import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StatisticsSqlDaoTest {
    private StatisticsDao statisticsDao;
    private UserDao userDao;
    private HistoryDao historyDao;
    private QuizDao quizDao;
    private QuestionDao qDao;
    private Connection con;
    private CreateTablesForTests tables;


    @BeforeAll
    public void init() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
    }

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        CreateTablesForTests.UsersTable = CreateTablesForTests.UsersTableTest;
        CreateTablesForTests.QuizTable = CreateTablesForTests.QuizTableTest;
        CreateTablesForTests.HistoryTable = CreateTablesForTests.HistoryTableTest;
        CreateTablesForTests.QuestionTable = CreateTablesForTests.QuestionTableTest;
        statisticsDao = new StatisticsSqlDao();
        userDao = new UserSqlDao();
        historyDao = new HistorySqlDao();
        quizDao = new QuizSqlDao();
        qDao = new QuestionDao();
        tables = new CreateTablesForTests();
        assertEquals(true, tables.createUserTable());
        assertEquals(true, tables.createQuizTable());
        assertEquals(true, tables.createQuestionTable());
        assertEquals(true, tables.createHistoryTable());
        addDataInTables();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        assertEquals(true, tables.dropTable(CreateTablesForTests.HistoryTableTest));
		assertEquals(true, tables.dropTable(CreateTablesForTests.QuestionTableTest));
        assertEquals(true, tables.dropTable(CreateTablesForTests.QuizTableTest));
        assertEquals(true, tables.dropTable(CreateTablesForTests.UsersTableTest));
        CreateTablesForTests.UsersTable = "Users";
        CreateTablesForTests.HistoryTable = "History";
        CreateTablesForTests.QuizTable = "Quiz";
        CreateTablesForTests.QuestionTable = "Question";
    }

    @Test
    public void testGetAllQuizzes() throws SQLException {
        assertEquals(0, statisticsDao.getAllQuizzes(1).size());
        HashSet<Quiz> quizzes = new HashSet<>();

        quizzes.add(quizDao.getQuiz(1));
        quizzes.add(quizDao.getQuiz(2));
        quizzes.add(quizDao.getQuiz(3));

        historyDao.addToHistory(2, 3, 20, new Date(), new Date());
        historyDao.addToHistory(2, 2, 23, new Date(), new Date());
        historyDao.addToHistory(1, 1, 25, new Date(), new Date());
        historyDao.addToHistory(2, 1, 24, new Date(), new Date());

        assertEquals(3, statisticsDao.getAllQuizzes(2).size());
        assertEquals(quizzes, new HashSet<>(statisticsDao.getAllQuizzes(2)));
    }

    @Test
    public void testGetPastPerformances() throws SQLException {
        HashSet<Integer> answer = new HashSet<>(Arrays.asList(20, 25, 28));
        HashSet<Integer> answer2 = new HashSet<>(Arrays.asList(23));

        historyDao.addToHistory(1, 1, 20, new Date(), new Date());
        historyDao.addToHistory(2, 1, 23, new Date(), new Date());
        historyDao.addToHistory(1, 1, 25, new Date(), new Date());
        historyDao.addToHistory(1, 2, 30, new Date(), new Date());
        historyDao.addToHistory(1, 1, 28, new Date(), new Date());

        HashSet<Integer> result =
                new HashSet<>(statisticsDao.getPastPerformances(1, 1));
        assertEquals(3, result.size());
        assertEquals(answer, result);

        HashSet<Integer> result2 =
                new HashSet<>(statisticsDao.getPastPerformances(2, 1));
        assertEquals(1, result2.size());
        assertEquals(answer2, result2);
    }

    @Test
    public void testGetMaxScore() throws SQLException {
        double answer1 = 0;
        double answer2 = 28;
        double answer3 = 25;

        historyDao.addToHistory(4, 2, 15, new Date(), new Date());
        historyDao.addToHistory(2, 1, 20, new Date(), new Date());
        historyDao.addToHistory(1, 1, 25, new Date(), new Date());
        historyDao.addToHistory(4, 2, 26, new Date(), new Date());
        historyDao.addToHistory(4, 2, 28, new Date(), new Date());
        historyDao.addToHistory(4, 4, 28, new Date(), new Date());

        assertEquals(answer1, statisticsDao.getMaxScore(4, 3));
        assertEquals(answer1, statisticsDao.getMaxScore(3, 2));
        assertEquals(answer2, statisticsDao.getMaxScore(4, 2));
        assertEquals(answer3, statisticsDao.getMaxScore(1, 1));

    }

    @Test
    public void testGetBestPlayer() throws SQLException {
        historyDao.addToHistory(1, 1, 25, new Date(120, 3, 5), new Date(120, 3, 15));
        historyDao.addToHistory(2, 1, 20, new Date(119, 2, 1), new Date(119, 2, 2));
        historyDao.addToHistory(3, 1, 25, new Date(118, 5, 3), new Date(118, 5, 9));
        historyDao.addToHistory(4, 1, 25, new Date(121, 4, 2), new Date(121, 4, 22));

        assertEquals(userDao.getUser(3), statisticsDao.getBestPlayer(1));
    }

    @Test
    public void testGetAverageScore() throws SQLException {
        historyDao.addToHistory(1, 3, 18, new Date(), new Date());
        historyDao.addToHistory(2, 2, 23, new Date(), new Date());
        historyDao.addToHistory(3, 3, 23, new Date(), new Date());
        historyDao.addToHistory(2, 2, 30, new Date(), new Date());
        historyDao.addToHistory(2, 3, 21, new Date(), new Date());
        historyDao.addToHistory(2, 1, 15, new Date(), new Date());

        assertEquals(20.6667, statisticsDao.getAverageScore(3));
        assertEquals(26.5, statisticsDao.getAverageScore(2));
        assertEquals(15.0, statisticsDao.getAverageScore(1));
    }

    private void addDataInTables() throws SQLException {
        userDao.addUser("UserN1", "userN1pass", false);
        userDao.addUser("UserN2", "userN2pass", false);
        userDao.addUser("UserN3", "userN3pass", false);
        userDao.addUser("UserN4", "userN4pass", false);

        quizDao.addQuiz(1, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        quizDao.addQuiz(1, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        quizDao.addQuiz(1, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        quizDao.addQuiz(1, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
    }

}
