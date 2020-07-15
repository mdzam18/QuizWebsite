package ProfilePackage;

import UserPackage.UserSqlDao;
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
        con = ProfileDataSrc.getConnection();
        //con = NanukaDatabase.getConnection();
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
        userDao.addUser("nanuka", "123", false);
        userDao.addUser("ana", "123", false);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        assertEquals(tables.dropTable(CreateTablesForTests.UsersTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.MailsTableTest), true);
        CreateTablesForTests.UsersTable = "Users";
        CreateTablesForTests.MailsTable = "Mails";
    }

    @Test
    void myTest() throws SQLException {
        userDao.addUser("nanuka", "123", false);
        userDao.addUser("ana", "123", false);
        friendDao.sendFriendRequest(1, 2);
        friendDao.confirmFriendRequest(1, 2);
        assertTrue(friendDao.areFriends(1, 2));
    }


    @Test
    public void test4() throws SQLException {

        Mail mail = new Mail(1, 1, 2, Mail.noteType, "hello", new Date(2020, 1, 1), 1);
        mailDao.sendMail(mail);
        ArrayList<Mail> list = mailDao.getMails(1);
        assertEquals(list.size(), 1);
        mailDao.deleteMailById(1);
        assertEquals(mailDao.getMails(1).size(), 0);
    }

    @Test
    void test5() throws SQLException {
        userDao.addUser("giorgi", "123", false);
        userDao.addUser("nika", "123", false);
        Mail mail1 = new Mail(1, 2, 3, Mail.noteType, "hello", new Date(2020, 1, 1), 1);
        mailDao.sendMail(mail1);
        Mail mail2 = new Mail(2, 2, 3, Mail.noteType, "hello", new Date(2020, 1, 1), 1);
        mailDao.sendMail(mail2);
        Mail mail3 = new Mail(3, 2, 3, Mail.noteType, "hello", new Date(2020, 1, 1), 1);
        mailDao.sendMail(mail3);
        assertEquals(3, mailDao.getMails(3).size());
        mailDao.deleteMailById(1);
        assertEquals(2, mailDao.getMails(3).size());
        mailDao.deleteMailById(2);
        assertEquals(1, mailDao.getMails(3).size());
    }

    @Test
    void test6() throws SQLException {
        userDao.addUser("giorgi", "123", false);
        userDao.addUser("nika", "123", false);
        mailDao.sendMail(
                new Mail(1, 1, 2, Mail.noteType, "hello",
                        new Date(2020, 1, 1), 1));
        mailDao.sendMail(
                new Mail(2, 2, 3, Mail.noteType, "hello",
                        new Date(2020, 1, 1), 1));
        mailDao.sendMail(
                new Mail(3, 3, 4, Mail.noteType, "hello",
                        new Date(2020, 1, 1), 1));
        mailDao.sendMail(
                new Mail(4, 1, 3, Mail.noteType, "hello",
                        new Date(2020, 1, 1), 1));

        assertEquals(1, mailDao.getMails(1));
        assertEquals(1, mailDao.getMails(2));
        assertEquals(1, mailDao.getMails(3));
        assertEquals(1, mailDao.getMails(4));
    }

    @Test
    void test7() throws SQLException{
        mailDao.sendMail(
                new Mail(1, 1, 2, Mail.noteType, "hello",
                        new Date(2020, 1, 1), 1));
        mailDao.sendMail(
                new Mail(2, 2, 1, Mail.noteType, "hello",
                        new Date(2020, 1, 1), 1));
        assertEquals(1,mailDao.getMailById(1).getSenderId());
        assertEquals(Mail.noteType,mailDao.getMailById(1).getType());
        assertEquals("hello",mailDao.getMailById(2).getMessage());
        assertEquals(1,mailDao.getMailById(2).getReceiverId());
    }
}
