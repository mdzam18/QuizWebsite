package ProfilePackage;

import ProfilePackage.Mail;


import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;

public class MailSqlDao implements MailDao{
    private Connection con;
    private static String friendsTable = "Friends";
    private static String friendsTableTest = "Friends2";
    private static String MailsTable = "Mails";
    private static String MailsTableTest = "Mails2";
    private static String UsersTableTest = "Users2";

    public MailSqlDao() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        //MyDatabase db = new MyDatabase();
        //con = db.connect();
    }
    @Override
    public boolean createFriendsTable() throws SQLException, ClassNotFoundException {
        Statement s = con.createStatement();
        s.executeUpdate("CREATE TABLE " + friendsTableTest + " (\n" + "SenderId int ,\n" +
                "ReceiverId int ,\n" +
                "Confirmed boolean,\n" +
                "DateSent Date,\n" +
                "foreign key (SenderId) references Users(UserId),\n" +
                "foreign key (ReceiverId) references Users(UserId));");
        return true;
    }

    @Override
    public boolean dropTable(String tableName) throws SQLException {
        Statement stm = null;
        stm = con.createStatement();
        stm.executeUpdate("drop table " + tableName);
        return true;
    }

    @Override
    public boolean createMailsTable() throws SQLException, ClassNotFoundException {
        Statement s = con.createStatement();
        String query = "CREATE TABLE " + MailsTableTest +
                " (\n" + "MailId int primary key ,\n" +
                "SenderId int ,\n" +
                "ReceiverId int ,\n" +
                "Type varchar(255) ,\n" +
                "Message varchar(255) ,\n" +
                "Date Date ,\n" +
                "Seen int ,\n" +
                "foreign key (SenderId) references Users(UserId),\n" +
                "foreign key (ReceiverId) references Users(UserId));";
        s.executeUpdate(query);
        return true;
    }
    public boolean sendFriendRequest(int from, int to) throws SQLException {
        if (from == to) return false;
        /*
        PreparedStatement statement = con.prepareStatement("select * from " + friendsTable + " where SenderId = ? and ReceiverId = ?;");
        statement.setInt(1, from.getUserId());
        statement.setInt(2, to.getUserId());
        ResultSet res = statement.executeQuery();
        if (res.next()) return false;
        statement = con.prepareStatement("select * from " + friendsTable + " where SenderId = ? and ReceiverId = ?;");
        statement.setInt(1, to.getUserId());
        statement.setInt(2, from.getUserId());
        if (res.next()) return false;
        */if(areFriends(from,to) || requested(from,to) || requested(to,from)) return false;
        Date date = new Date(2020,1,1);
        PreparedStatement statement = con.prepareStatement("insert into " + friendsTable + " values (?, ?, ?, ?)");
        statement.setInt(1, from);
        statement.setInt(2, to);
        statement.setBoolean(3, false);
        statement.setDate(4, date);
        statement.executeQuery();
        return true;
    }

    public boolean createUserTable() throws SQLException, ClassNotFoundException {
        Statement s = con.createStatement();
        s.executeUpdate("CREATE TABLE " + UsersTableTest + " (\n" + "UserId int primary key, \n" +
                "UserName varchar(255),\n" +
                "Password varchar(255),\n" +
                "IsAdministrator boolean,\n" +
                "Salt varchar (255),\n" +
                "Name varchar(255),\n" +
                "Surname varchar(255),\n" +
                "Birth_Date Date,\n" +
                "Birth_Place varchar(255),\n" +
                "Status varchar(255)" + ");");
        // userTable = "test.Users";
        return true;
    }
    @Override
    public boolean requested(int SenderId, int ReceiverId) throws SQLException{
        PreparedStatement statement = con.prepareStatement("select * from " + friendsTable + " where SenderId = ? and ReceiverId = ? and Confirmed = 0;");
        statement.setInt(1, SenderId);
        statement.setInt(2, ReceiverId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) return true;
        return false;
    }
    @Override
    public boolean areFriends(int user1, int user2) throws SQLException{
        PreparedStatement statement = con.prepareStatement("select * from " + friendsTable + " where SenderId = ? and ReceiverId = ? and Confirmed = 1;");
        statement.setInt(1, user1);
        statement.setInt(2, user2);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) return true;
        return false;
    }
    @Override
    public boolean confirmFriendRequest(int senderId, int receiverId) throws SQLException {
        PreparedStatement statement = con.prepareStatement("update " + friendsTable + " set Confirmed = ? where SenderId = ? and ReceiverId = ?;");
        statement.setBoolean(1, true);
        statement.setInt(2, senderId);
        statement.setInt(3, receiverId);
        statement.executeUpdate();
        return true;
    }

    private Mail getMail(ResultSet rs)throws SQLException{
        return new Mail(rs.getInt("MailId"),
                rs.getInt("SenderId"),
                rs.getInt("ReceiverId"),
                rs.getString("Type"),
                rs.getString("Message"),
                rs.getDate("Date"),
                rs.getInt("Seen")
        );
    }

    @Override
    public Mail getMailById(int mailId) throws SQLException {
        ResultSet rs = con.prepareStatement("Select * from `Mails` where `MailId` = " + mailId).executeQuery();
        if(rs.next()){
            return getMail(rs);
        }
        return null;
    }

    @Override
    public void sendMail(Mail mail) throws SQLException{
        PreparedStatement statement = con.prepareStatement(
                "SELECT max(MailId) FROM " + MailsTable + ";");
        ResultSet res = statement.executeQuery();
        int id = 0;
        res.next();
        if (res != null) {
            id = res.getInt(1);
        }
        id++;
        PreparedStatement ps = con.prepareStatement("insert into `Mails` (`SenderId`, `ReceiverId`, `Type`, `Message`, `Date`, `Seen`) value (?, ?, ?,?,?,?,?)");
        ps.setInt(1,id);
        ps.setInt(2,mail.getSenderId());
        ps.setInt(3,mail.getReceiverId());
        ps.setString(4,mail.getType());
        ps.setString(5,mail.getMessage());
        ps.setDate(6,mail.getDate());
        ps.setBoolean(7,mail.isSeen());
        ps.executeQuery();
    }

    @Override
    public ArrayList<Mail> getMails(int userId) throws SQLException{
        ArrayList<Mail> result = new ArrayList<>();
        ResultSet rs = con.prepareStatement("Select * from `Mails` where `ReceiverId` = " + userId + "order by `Time` desc")
                .executeQuery();
        while(rs.next()){
            result.add(getMail(rs));
        }

        return result;
    }

    @Override
    public void deleteMailById(int mailId) throws SQLException{
        con.prepareStatement("Delete from `Mails` Where `MailId` = " + mailId).executeQuery();

    }

    @Override
    public void addFriendship(int user1, int user2, Timestamp time)throws SQLException{
        Connection cn = null;
        PreparedStatement ps = cn.prepareStatement("Insert into `Friends` (`SenderId`, `ReceiverId`, `Confirmed`, `Date`) VALUE (?,?,?,?)");
        ps.setInt(1,user1);
        ps.setInt(2,user2);
        ps.setInt(3,1);
        ps.setTimestamp(4, time);
        ps.executeQuery();
    }
}
