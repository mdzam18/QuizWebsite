package ProfilePackage;

import ProfilePackage.Mail;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface MailDao {
    Mail getMailById(int mailId) throws SQLException;

    void sendMail(Mail mail) throws SQLException;

    ArrayList<Mail> getMails(int userId) throws SQLException;

    void deleteMailById(int userId) throws SQLException;

    void addFriendship(int user1, int user2, Timestamp time) throws SQLException;

    public boolean sendFriendRequest(int from, int to) throws SQLException;

    public boolean confirmFriendRequest(int senderId, int receiverId) throws SQLException;

    public boolean createMailsTable() throws SQLException, ClassNotFoundException;

    public boolean createFriendsTable() throws SQLException, ClassNotFoundException;

    public boolean dropTable(String tableName) throws SQLException;

    public boolean requested(int SenderId, int ReceiverId) throws SQLException;

    public boolean areFriends(int user1, int user2) throws SQLException;

}
