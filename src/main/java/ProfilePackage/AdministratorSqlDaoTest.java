package ProfilePackage;

import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdministratorSqlDaoTest {
	private AdministratorDao adminDao;
	private UserDao userDao;
	private HistoryDao historyDao;
	private Connection con;
	
	@BeforeAll
	public void init() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		adminDao = new AdministratorSqlDao();
		userDao = new UserSqlDao();
		historyDao = new HistorySqlDao();
		con = adminDao.getConnection();
	}
	
	@BeforeEach
	public void setUp() throws SQLException {
		assertEquals(true, adminDao.createUsersTable());
		assertEquals(true, adminDao.createQuizTable());
		assertEquals(true, adminDao.createHistoryTable());
	}
	
	@AfterEach
	public void tearDown() throws SQLException {
		assertEquals(true, adminDao.dropTable("History2"));
		assertEquals(true, adminDao.dropTable("Quiz2"));
		assertEquals(true, adminDao.dropTable("Users2"));
	}
	
	@Test
	public void testGetAllAdmins() throws SQLException {
		assertEquals(0, adminDao.getAllAdmins().size());
		HashSet<User> answer = new HashSet<>();
		
		User admin1 = adminDao.addAdmin("adminN1","adminN1pass");
		User admin2 = adminDao.addAdmin("adminN2","adminN2pass");
		User admin3 = adminDao.addAdmin("adminN3", "adminN3pass");
		answer.add(admin1);
		answer.add(admin2);
		answer.add(admin3);
		
		assertEquals(3, adminDao.getAllAdmins().size());
		
		HashSet<User> result = new HashSet<>(adminDao.getAllAdmins());
		
		assertEquals(answer,result);
	}
	
	@Test
	public void testDeleteUser() throws SQLException {
		HashSet<User> answer = new HashSet<>();
		
		User admin1 = adminDao.addAdmin("adminN1","adminN1pass");
		User admin2 = adminDao.addAdmin("adminN2","adminN2pass");
		User admin3 = adminDao.addAdmin("adminN3", "adminN3pass");
		
		answer.add(admin1);
		answer.add(admin3);
		
		int admin2Id = admin2.getUserId();
		
		assertEquals(true, adminDao.deleteUser(admin2));
		assertEquals(null, adminDao.getAdmin(admin2Id));
		
		HashSet<User> result = new HashSet<>(adminDao.getAllAdmins());
		
		assertEquals(answer,result);
	}
	
	@Test
	public void testDeleteQuiz() throws SQLException {
	
	}
	
	@Test
	public void testDeleteHistory() throws SQLException {
		Quiz quiz = new Quiz();
		quiz.seId(1);
		historyDao.addToHistory(1,1,20,new Date(2020,2,1),new Time(1,1,1));
		assertEquals(true, adminDao.deleteHistory(quiz));
		assertEquals(null, historyDao.getHistories(1));
	}
	
	@Test
	public void testPromoteUser() throws SQLException {
		User user = userDao.addUser("userN1", "userN1pass");
		int userId = user.getUserId();
		assertEquals(true, adminDao.promoteUser(user));
		assertEquals(true, userDao.getUser(userId).isAdministrator());
	}
}