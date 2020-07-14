package ProfilePackage;

import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MailSqlDaoTest {
    private MailSqlDao mailDao;
    private UserSqlDao userDao;
    private Connection con;
    private CreateTablesForTests tables;
    private FriendsSqlDao friendDao;

    @BeforeAll
    public void getConnection() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        //con = ProfileDataSrc.getConnection();
        con = NanukaDatabase.getConnection();
        tables = new CreateTablesForTests();
        friendDao = new FriendsSqlDao();
        mailDao = new MailSqlDao();
        userDao = new UserSqlDao();
    }

    @BeforeEach
    public void setUp() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {

        CreateTablesForTests.MailsTable = CreateTablesForTests.MailsTableTest;
        CreateTablesForTests.UsersTable = CreateTablesForTests.UsersTableTest;
        assertEquals(tables.createMailsTable(), true);
        assertEquals(tables.createUserTable(), true);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        assertEquals(tables.dropTable(CreateTablesForTests.UsersTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.MailsTableTest), true);
        CreateTablesForTests.UsersTable = "test.Users";
        CreateTablesForTests.MailsTable = "test.Mails";
    }
    @Test
    void myTest() throws SQLException{
        userDao.addUser("nanuka", "123");
        userDao.addUser("ana", "123");
        friendDao.sendFriendRequest(1,2);
        friendDao.confirmFriendRequest(1,2);
        assertTrue(friendDao.areFriends(1,2));
    }

   /* @Test
    void test1() throws SQLException{
        userDao.addUser("nanuka", "123");
        userDao.addUser("ana", "123");
        mailDao.sendFriendRequest(1,2);
        mailDao.confirmFriendRequest(1,2);
        assertTrue(mailDao.areFriends(1,2));
    }
    */

    /*@Test
    void test2() throws SQLException{
        userDao.addUser("nanuka", "123");
        userDao.addUser("ana", "123");
        assertTrue(!mailDao.areFriends(1,2));
    }

     */

   /* @Test
    void test3() throws SQLException{
        userDao.addUser("nanuka", "123");
        userDao.addUser("ana", "123");
        assertTrue(!mailDao.requested(1,2));
        mailDao.sendFriendRequest(1,2);
        assertTrue(mailDao.requested(1,2));
    }

    */

    @Test
    public void test4() throws SQLException{
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
