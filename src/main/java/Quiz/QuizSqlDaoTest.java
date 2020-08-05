package Quiz;

import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;
import UserPackage.User;
import UserPackage.UserDao;
import UserPackage.UserSqlDao;
import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuizSqlDaoTest {

    private Quiz quiz;
    private QuizSqlDao database;
    private UserSqlDao userDatabase;
    private Connection con;
    private String quizTable = CreateTablesForTests.QuizTableTest;
    private CreateTablesForTests tables;

    private static final int CREATOR = 123;
    private static final int QUIZ = 456;

    @BeforeAll
    public void init() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
    }

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        CreateTablesForTests.QuizTable = CreateTablesForTests.QuizTableTest;
        CreateTablesForTests.UsersTable = CreateTablesForTests.UsersTableTest;
        CreateTablesForTests.QuestionTable = CreateTablesForTests.QuestionTableTest;
        userDatabase = new UserSqlDao();
        database = new QuizSqlDao();
        tables = new CreateTablesForTests();
        assertTrue(tables.createUserTable());
        assertTrue(tables.createQuizTable());
        assertEquals(tables.createQuestionTable(), true);
        addData();
    }

    @AfterEach
    public void finishUp() throws SQLException {
        assertEquals(true, tables.dropTable(CreateTablesForTests.QuestionTableTest));
        assertEquals(true, tables.dropTable(CreateTablesForTests.QuizTableTest));
        assertEquals(true, tables.dropTable(CreateTablesForTests.UsersTableTest));
        CreateTablesForTests.QuizTable = "Quiz";
        CreateTablesForTests.UsersTable = "Users";
        CreateTablesForTests.QuestionTable = "Questions";
    }

    @Test
    public void testGetQuizId() throws SQLException {
        Quiz q1 = database.getQuiz(1);
        Quiz q2 = database.getQuiz(2);
        Quiz q3 = database.getQuiz(3);

        assertEquals(database.getQuizId(q1.getCreatorId(), q1.getDescription()), 1);
        assertEquals(database.getQuizId(q2.getCreatorId(), q2.getDescription()), 2);
        assertEquals(database.getQuizId(q3.getCreatorId(), q3.getDescription()), 3);
    }

    @Test
    public void testGetQuizIdByName() throws SQLException {
        Quiz q1 = database.getQuiz(1);
        Quiz q2 = database.getQuiz(2);
        Quiz q3 = database.getQuiz(3);

        assertEquals(database.getQuizIdByName(q1.getDescription()), 1);
        assertEquals(database.getQuizIdByName(q2.getDescription()), 2);
        assertEquals(database.getQuizIdByName(q3.getDescription()), 3);
    }

    @Test
    public void testQuizByCreatorAndName() throws SQLException {
        Quiz q1 = database.getQuiz(1);
        Quiz q2 = database.getQuiz(2);
        Quiz q3 = database.getQuiz(3);

        assert(database.getQuizByCreatorAndName(q1.getCreatorId(), q1.getDescription()).equals(q1));
        assert(database.getQuizByCreatorAndName(q2.getCreatorId(), q2.getDescription()).equals(q2));
        assert(database.getQuizByCreatorAndName(q2.getCreatorId(), q2.getDescription()).equals(q2));
    }


    @Test
    public void testGetQuiz() throws SQLException {
        Quiz q1 = database.getQuiz(1);
        Quiz q2 = database.getQuiz(2);
        Quiz q3 = database.getQuiz(3);

        assertEquals(q1.getCreatorId(), 1);
        assertEquals(q2.getCreatorId(), 2);
        assertEquals(q3.getCreatorId(), 3);

        assertFalse(q1.isRandom());
        assertFalse(q1.isOnePage());
        assertFalse(q1.isImmediate());
        assertFalse(q1.isInPracticeMode());
        assertEquals(q1.getQuestionCount(), 0);
        assert(q1.getDescription().equals("1"));
        assertEquals(q1.getCategory(), null);
    }

    @Test
    public void testUserHasQuizByName() throws SQLException {
        Quiz q1 = database.getQuiz(1);
        Quiz q2 = database.getQuiz(2);
        Quiz q3 = database.getQuiz(3);

        assertTrue(database.userHasQuizByName(q1.getCreatorId(), q1.getDescription()));
        assertTrue(database.userHasQuizByName(q2.getCreatorId(), q2.getDescription()));
        assertTrue(database.userHasQuizByName(q3.getCreatorId(), q3.getDescription()));

        assertFalse(database.userHasQuizByName(q1.getCreatorId(), "0"));
        assertFalse(database.userHasQuizByName(q2.getCreatorId(), "0"));
        assertFalse(database.userHasQuizByName(q3.getCreatorId(), "0"));
    }

    @Test
    public void testSetDescription() throws SQLException {
        Quiz q1 = database.getQuiz(1);
        Quiz q2 = database.getQuiz(2);
        Quiz q3 = database.getQuiz(3);

        database.setDescription(q1.getQuizId(), "4");
        database.setDescription(q2.getQuizId(), "5");
        database.setDescription(q3.getQuizId(), "6");

        q1 = database.getQuiz(1);
        q2 = database.getQuiz(2);
        q3 = database.getQuiz(3);

        assert(q1.getDescription().equals("4"));
        assert(q2.getDescription().equals("5"));
        assert(q3.getDescription().equals("6"));
    }

    @Test
    public void testGetRecentlyCreatedQuizzes() throws SQLException {
        database.addQuiz(1, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        database.addQuiz(2, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        database.addQuiz(3, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));

        database.setDescription(4, "4");
        database.setDescription(5, "5");
        database.setDescription(6, "6");

        List<Quiz> recent = database.getRecentlyCreatedQuizzes();

        Quiz q1 = database.getQuiz(1);
        Quiz q2 = database.getQuiz(2);
        Quiz q3 = database.getQuiz(3);
        Quiz q4 = database.getQuiz(4);
        Quiz q5 = database.getQuiz(5);
        Quiz q6 = database.getQuiz(6);

        assert(recent.size() == 5);
        assert(q6.equals(recent.get(0)));
        assert(q5.equals(recent.get(1)));
        assert(q4.equals(recent.get(2)));
        assert(q3.equals(recent.get(3)));
        assert(q2.equals(recent.get(4)));
    }

    @Test
    public void testAddQuiz() throws SQLException {
        database.addQuiz(3, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        assertTrue(database.getQuizzesForUser(3).size() == 2);
    }

    @Test
    public void testDeleteQuiz() throws SQLException {
        Quiz q = new Quiz(1, 1);
        boolean deleted = database.deleteQuiz(q);

        assertTrue(deleted);
        assertEquals(database.getQuiz(1), null);
        assertNotEquals(database.getQuiz(2), null);

        q = new Quiz(2, 2);
        deleted = database.deleteQuiz(q);

        assertTrue(deleted);
        assertEquals(database.getQuiz(2), null);
    }

    @Test
    public void testGetQuizzesForUser() throws SQLException {
        List<Quiz> user1 = database.getQuizzesForUser(1);

        assertEquals(user1.size(), 1);
        assertEquals(user1.get(0).getCreatorId(), 1);
        assertEquals(user1.get(0).getQuizId(), 1);
    }

    @Test
    public void testGetAllQuizzes() throws SQLException {
        database.addQuiz(1, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        database.addQuiz(2, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        database.addQuiz(3, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));

        database.setDescription(1, "4");
        database.setDescription(2, "5");
        database.setDescription(3, "6");

        List<Quiz> allQuizzes = database.getAllQuizzes();

        Quiz q1 = database.getQuiz(1);
        Quiz q2 = database.getQuiz(2);
        Quiz q3 = database.getQuiz(3);
        Quiz q4 = database.getQuiz(4);
        Quiz q5 = database.getQuiz(5);
        Quiz q6 = database.getQuiz(6);

        assert(allQuizzes.size() == 6);

        assert(q1.equals(allQuizzes.get(0)));
        assert(q2.equals(allQuizzes.get(1)));
        assert(q3.equals(allQuizzes.get(2)));
        assert(q4.equals(allQuizzes.get(3)));
        assert(q5.equals(allQuizzes.get(4)));
        assert(q6.equals(allQuizzes.get(5)));
    }

    @Test
    public void testRecentlyCreatedQuizzesByUser() throws SQLException {
        User user = userDatabase.addUser("Harry Potter", "boyWhoLived", true);
        quiz = database.addQuiz(user.getUserId(), false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        List<Quiz> list = database.getRecentlyCreatedQuizzesByUser(user.getUserId());
        assertEquals(list.size(), 1);

        for(int i  = 2 ; i < 6; i++){
            database.addQuiz(user.getUserId(), false, false, false,
                    false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
            list = database.getRecentlyCreatedQuizzesByUser(user.getUserId());
            assertEquals(list.size(), i);
        }
    }

    private void addData() throws SQLException {
        userDatabase.addUser("a" , "a" , false);
        userDatabase.addUser("b" , "b", false);
        userDatabase.addUser("c", "c", false);
        
        database.addQuiz(1, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        database.addQuiz(2, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
        database.addQuiz(3, false, false, false,
                false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));

        database.setDescription(1, "1");
        database.setDescription(2, "2");
        database.setDescription(3, "3");
    }
}
