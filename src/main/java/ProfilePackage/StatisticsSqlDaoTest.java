package ProfilePackage;

import Quiz.Quiz;
import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StatisticsSqlDaoTest {
	private StatisticsDao statisticsDao;
	private UserDao userDao;
	private HistoryDao historyDao;
	//	private Quiz.QuizDAO quizDao;
	private Connection con;
	private CreateTablesForTests tables;
	
	
	@BeforeAll
	public void init() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		con = ProfileDataSrc.getConnection();
		statisticsDao = new StatisticsSqlDao();
		userDao = new UserSqlDao();
		historyDao = new HistorySqlDao(false);
		tables = new CreateTablesForTests();
	}
	
	@BeforeEach
	public void setUp() throws SQLException, ClassNotFoundException {
		assertEquals(true, tables.createUserTable());
		assertEquals(true, tables.createQuizTable());
		assertEquals(true, tables.createHistoryTable());
	}
	
	@AfterEach
	public void tearDown() throws SQLException {
		assertEquals(true, tables.dropTable("History2"));
		assertEquals(true, tables.dropTable("Quiz2"));
		assertEquals(true, tables.dropTable("Users2"));
	}
	
	@Test
	public void testGetAllQuizzes() throws SQLException {
		assertEquals(0, statisticsDao.getAllQuizzes(1).size());
		//...
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
		Integer answer1 = null;
		Integer answer2 = 28;
		Integer answer3 =  25;
		
		historyDao.addToHistory(4, 5, 15, new Date(), new Date());
		historyDao.addToHistory(2, 5, 20, new Date(), new Date());
		historyDao.addToHistory(1, 7, 25, new Date(), new Date());
		historyDao.addToHistory(4, 5, 26, new Date(), new Date());
		historyDao.addToHistory(4, 5, 28, new Date(), new Date());
		historyDao.addToHistory(4, 4, 28, new Date(), new Date());
		
		assertEquals(answer1, statisticsDao.getMaxScore(4, 3));
		assertEquals(answer1, statisticsDao.getMaxScore(3, 5));
		assertEquals(answer2, statisticsDao.getMaxScore(4, 5));
		assertEquals(answer3, statisticsDao.getMaxScore(1, 7));
		
	}
	
	@Test
	public void testGetBestPlayer() throws SQLException {
		userDao.addUser("UserN1", "userN1pass", false);
		userDao.addUser("UserN2", "userN2pass", false);
		userDao.addUser("UserN3", "userN3pass", false);
		userDao.addUser("UserN4", "userN4pass", false);
		
		historyDao.addToHistory(1, 1,25, new Date(1100), new Date(1500));
		historyDao.addToHistory(2, 1,20, new Date(1200), new Date(1401));
		historyDao.addToHistory(3, 1,25, new Date(1000), new Date(1300));
		historyDao.addToHistory(4, 1,25, new Date(1300), new Date(1625));
		
		User user = userDao.getUser(3);
		
		assertEquals(user, statisticsDao.getBestPlayer(1));
	}
	
	@Test
	public void testGetAverageScore() throws SQLException {
		
		historyDao.addToHistory(1, 3, 18, new Date(), new Date());
		historyDao.addToHistory(2, 2, 23, new Date(), new Date());
		historyDao.addToHistory(3, 3, 23, new Date(), new Date());
		historyDao.addToHistory(5, 2, 30, new Date(), new Date());
		historyDao.addToHistory(2, 3, 21, new Date(), new Date());
		historyDao.addToHistory(2, 1, 15, new Date(), new Date());
		
		assertEquals(62/3, statisticsDao.getAverageScore(3));
		assertEquals(53/2, statisticsDao.getAverageScore(2));
		assertEquals(15.0, statisticsDao.getAverageScore(1));
	}
	
}
