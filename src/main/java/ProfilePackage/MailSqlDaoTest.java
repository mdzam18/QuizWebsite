package ProfilePackage;

import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MailSqlDaoTest {
    private MailSqlDao mailDao;
    private UserSqlDao userDao;
    private Connection con;

    @BeforeAll
    public void getConnection() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        //MyDatabase db = new MyDatabase();
        //con = db.connect();
    }

    @BeforeEach
    public void setUp() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        mailDao = new MailSqlDao();
        userDao = new UserSqlDao();
        assertEquals(mailDao.createMailsTable(), true);
        assertEquals(mailDao.createFriendsTable(), true);
        assertEquals(mailDao.createUserTable(), true);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        assertEquals(mailDao.dropTable("Users2"), true);
        assertEquals(mailDao.dropTable("Friends2"), true);
    }

    @Test
    void test1() throws SQLException{
        userDao.addUser("nanuka", "123");
        userDao.addUser("ana", "123");
        mailDao.sendFriendRequest(1,2);
        mailDao.confirmFriendRequest(1,2);
        assertTrue(mailDao.areFriends(1,2));
    }

    @Test
    void test2() throws SQLException{
        userDao.addUser("nanuka", "123");
        userDao.addUser("ana", "123");
        assertTrue(!mailDao.areFriends(1,2));
    }

    @Test
    void test3() throws SQLException{
        userDao.addUser("nanuka", "123");
        userDao.addUser("ana", "123");
        assertTrue(!mailDao.requested(1,2));
        mailDao.sendFriendRequest(1,2);
        assertTrue(mailDao.requested(1,2));
    }

    @Test
    void test4() throws SQLException{
        userDao.addUser("nanuka", "123");
        userDao.addUser("ana", "123");
        Mail mail = new Mail(1,1,2,Mail.noteType,"hello",new Date(2020,1,1),1);
        mailDao.sendMail(mail);
        ArrayList<Mail> list = mailDao.getMails(1);
        assertEquals(list.size(),1);
        mailDao.deleteMailById(1);
        assertEquals(mailDao.getMails(1).size(),0);
    }

    @Test
    void test5() throws SQLException{
    }

    @Test
    void test6() {
    }

    @Test
    void test7() {
    }
}