package MailPackage;

import FriendsPackage.FriendsSqlDao;
import MailPackage.MailSqlDao;
import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;
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
    }

    @BeforeEach
    public void setUp() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        tables = new CreateTablesForTests();
        CreateTablesForTests.MailsTable = CreateTablesForTests.MailsTableTest;
        CreateTablesForTests.UsersTable = CreateTablesForTests.UsersTableTest;
        CreateTablesForTests.FriendsTable = CreateTablesForTests.FriendsTableTest;
        friendDao = new FriendsSqlDao();
        mailDao = new MailSqlDao();
        userDao = new UserSqlDao();
        assertEquals(tables.createUserTable(), true);
        assertEquals(tables.createMailsTable(), true);
        assertEquals(tables.createFriendsTable(), true);
        userDao.addUser("nanuka", "123", false);
        userDao.addUser("ana", "123", false);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        assertEquals(tables.dropTable(CreateTablesForTests.MailsTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.FriendsTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.UsersTableTest), true);
        CreateTablesForTests.UsersTable = "Users";
        CreateTablesForTests.MailsTable = "Mails";
        CreateTablesForTests.FriendsTable = "Friends";
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
        assertEquals(mailDao.sendMail(1, 2, Mail.noteType, "hello", new Date(2020, 1, 1), true), true);
        ArrayList<Mail> list = mailDao.getMails(2);
        assertEquals(list.size(), 1);
        mailDao.deleteMailById(1);
        assertEquals(mailDao.getMails(2).size(), 0);
    }

    @Test
    void test5() throws SQLException {
        userDao.addUser("giorgi", "123", false);
        userDao.addUser("nika", "123", false);
        Mail mail1 = new Mail(1, 2, 3, Mail.noteType, "hello", new Date(2020, 1, 1), 1);
        mailDao.sendMail(mail1.getSenderId(), mail1.getReceiverId(), mail1.getType(), mail1.getMessage(), mail1.getDate(), mail1.isSeen());
        mail1 = new Mail(2, 2, 3, Mail.noteType, "hello", new Date(2020, 1, 1), 1);

        mailDao.sendMail(mail1.getSenderId(), mail1.getReceiverId(), mail1.getType(), mail1.getMessage(), mail1.getDate(), mail1.isSeen());
        mail1 = new Mail(3, 2, 3, Mail.noteType, "hello", new Date(2020, 1, 1), 1);

        mailDao.sendMail(mail1.getSenderId(), mail1.getReceiverId(), mail1.getType(), mail1.getMessage(), mail1.getDate(), mail1.isSeen());
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
        mailDao.sendMail(1, 2, Mail.noteType, "hello",
                new Date(2020, 1, 1), true);
        mailDao.sendMail(
                2, 3, Mail.noteType, "hello",
                new Date(2020, 1, 1), true);
        mailDao.sendMail(
                3, 4, Mail.noteType, "hello",
                new Date(2020, 1, 1), true);
        mailDao.sendMail(1, 3, Mail.noteType, "hello",
                new Date(2020, 1, 1), true);

        assertEquals(0, mailDao.getMails(1).size());
        assertEquals(1, mailDao.getMails(2).size());
        assertEquals(2, mailDao.getMails(3).size());
        assertEquals(1, mailDao.getMails(4).size());
    }

    @Test
    void test7() throws SQLException {
        mailDao.sendMail(
                1, 2, Mail.noteType, "hello",
                new Date(2020, 1, 1), true);
        mailDao.sendMail(
                2, 1, Mail.noteType, "hello",
                new Date(2020, 1, 1), true);
        assertEquals(1, mailDao.getMailById(1).getSenderId());
        assertEquals(Mail.noteType, mailDao.getMailById(1).getType());
        assertEquals("hello", mailDao.getMailById(2).getMessage());
        assertEquals(1, mailDao.getMailById(2).getReceiverId());
    }

    @Test
    public void testMailDoesNotExists() throws SQLException {
        assertEquals(null, mailDao.getMailById(5));
    }
}

