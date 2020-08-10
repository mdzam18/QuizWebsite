package Quiz;

import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;
import UserPackage.UserDao;
import UserPackage.UserSqlDao;
import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionDaoTest {

    private QuestionDao qDao;
    private Connection con;
    private CreateTablesForTests tables;

    @BeforeAll
    public void getConnection() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        tables = new CreateTablesForTests();
    }

    @BeforeEach
    public void setUp() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        init();
        qDao = new QuestionDao();
        createTables();
    }

    private void createTables() throws SQLException, ClassNotFoundException {
        assertEquals(tables.createUserTable(), true);
        assertEquals(tables.createQuizTable(), true);
        assertEquals(tables.createHistoryTable(), true);
        assertEquals(tables.createQuestionTable(), true);
        assertEquals(tables.createAchievementsTable(), true);
        assertEquals(tables.createQuizTagTable(), true);
    }

    private void init() {
        CreateTablesForTests.UsersTable = CreateTablesForTests.UsersTableTest;
        CreateTablesForTests.FriendsTable = CreateTablesForTests.FriendsTableTest;
        CreateTablesForTests.MailsTable = CreateTablesForTests.MailsTableTest;
        CreateTablesForTests.HistoryTable = CreateTablesForTests.HistoryTableTest;
        CreateTablesForTests.AchievementsTable = CreateTablesForTests.AchievementsTableTest;
        CreateTablesForTests.QuizTable = CreateTablesForTests.QuizTableTest;
        CreateTablesForTests.QuizTagTable = CreateTablesForTests.QuizTagTableTest;
        CreateTablesForTests.QuestionTable = CreateTablesForTests.QuestionTableTest;
    }

    @AfterEach
    public void tearDown() throws SQLException {
        dropTables();
        CreateTablesForTests.UsersTable = "Users";//chemtvis test unda davmato win.;
        CreateTablesForTests.FriendsTable = "Friends";
        CreateTablesForTests.MailsTable = "Mails";
        CreateTablesForTests.HistoryTable = "History";
        CreateTablesForTests.AchievementsTable = "Achievements";
        CreateTablesForTests.QuizTable = "Quiz";
        CreateTablesForTests.QuizTagTable = "QuizTag";
        CreateTablesForTests.QuestionTable = "Question";
    }

    private void dropTables() throws SQLException {
        assertEquals(tables.dropTable(CreateTablesForTests.HistoryTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.QuestionTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.AchievementsTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.QuizTagTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.QuizTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.UsersTableTest), true);
    }


    @Test
    public void testAddAndGetQuestions() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        UserSqlDao uDao = new UserSqlDao();
        uDao.addUser("a", "b", false);
        QuizSqlDao quizDao = new QuizSqlDao();
        quizDao.addQuiz(1, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        Question question = qDao.addQuestion("a", "b", 1, 100, 1);

        assertEquals(qDao.getQuestion(1).equals(question), true);
    }

    @Test
    public void testAddAndGetQuestions2() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
   /*     UserSqlDao uDao = new UserSqlDao();
        uDao.addUser("a", "b", false);
        QuizSqlDao quizDao = new QuizSqlDao();
        quizDao.addQuiz(1, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        for (int i = 2; i < 5; i++) {
            Question question = qDao.addQuestion("a", "b", i+1, 100, 1);
            assertEquals(qDao.getQuestion(i-1).equals(question), true);
        }

    */
    }

    @Test
    public void testDeleteQuestion() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        UserSqlDao uDao = new UserSqlDao();
        uDao.addUser("a", "b", false);
        QuizSqlDao quizDao = new QuizSqlDao();
        quizDao.addQuiz(1, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        Question question = qDao.addQuestion("a", "b", 1, 100, 1);

        assertEquals(qDao.getQuestion(1).equals(question), true);
        assertEquals(qDao.deleteQuestion("a"), true);
        assertEquals(qDao.getQuestion(question.getQuestionId()), null);
    }

    @Test
    public void deleteQuestionById() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        UserSqlDao uDao = new UserSqlDao();
        uDao.addUser("a", "b", false);
        QuizSqlDao quizDao = new QuizSqlDao();
        quizDao.addQuiz(1, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        Question question = null;
        for (int i = 0; i < 10; i++) {
            question = qDao.addQuestion("a" + i, "b", 1, 100, 1);
        }

        assertEquals(qDao.getQuizQuestions(1).size(), 10);
        assertEquals(qDao.deleteQuestionsByQuizId(1), true);
        assertEquals(qDao.getQuizQuestions(1).size(), 0);
    }

    @Test
    public void testGetQuestionQuizId() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        UserSqlDao uDao = new UserSqlDao();
        uDao.addUser("a", "b", false);
        QuizSqlDao quizDao = new QuizSqlDao();
        Question question = null;
        for (int i = 0; i < 10; i++) {
            Quiz quiz = quizDao.addQuiz(1, false, false, false,
                    false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
            question = qDao.addQuestion("a" + i, "b", 1, 100, quiz.getQuizId());
            assertEquals(qDao.getQuestionQuizId(question.getQuestionId()), i + 1);
        }
    }
}