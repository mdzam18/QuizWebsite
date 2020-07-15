package ProfilePackage;

import UserPackage.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendsSqlDao implements FriendsDao {
    private Connection con;
    private String friendsTable;

    public FriendsSqlDao() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        //con = NanukaDatabase.getConnection();
        friendsTable = CreateTablesForTests.FriendsTable;
    }

    @Override
    public boolean deleteFriend(int from, int to) throws SQLException {
        PreparedStatement stm = null;
        if (!areFriends(from, to)) return false;
        stm = con.prepareStatement(
                "delete from " + friendsTable + " where (SenderId = ? and ReceiverId = ?) or (SenderId = ? and ReceiverId = ?);");
        stm.setInt(1, from);
        stm.setInt(2, to);
        stm.setInt(3, to);
        stm.setInt(4, from);
        stm.executeUpdate();
        return true;
    }

    @Override
    public List<User> getSentRequests(User user) throws SQLException {
        PreparedStatement stm = null;
        List<User> result = new ArrayList<>();
        stm = con.prepareStatement(
                "SELECT * FROM " + friendsTable + " WHERE (SenderId = ?) and Confirmed = ?;");
        stm.setInt(1, user.getUserId());
        stm.setBoolean(2, false);
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            stm = con.prepareStatement("select * from " + CreateTablesForTests.UsersTable + " where UserId = ?;");
            stm.setInt(1, res.getInt(2));
            ResultSet rs2 = stm.executeQuery();
            rs2.next();
            User user2 = new User(rs2.getString(2), rs2.getInt(1), rs2.getString(3));
            user2.setAdministrator(rs2.getBoolean(4));
            user2.setName(rs2.getString(6));
            user2.setSurname(rs2.getString(7));
            user2.setBirthDate(rs2.getDate(8));
            user2.setBirthPlace(rs2.getString(9));
            user2.setStatus(rs2.getString(10));
            result.add(user2);
        }
        return result;
    }

    @Override
    public List<User> getReceivedRequests(User user) throws SQLException {
        PreparedStatement stm = null;
        List<User> result = new ArrayList<>();
        stm = con.prepareStatement(
                "SELECT * FROM " + friendsTable + " WHERE (ReceiverId = ?) and Confirmed = ?;");
        stm.setInt(1, user.getUserId());
        stm.setBoolean(2, false);
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            stm = con.prepareStatement("select * from " + CreateTablesForTests.UsersTable + " where UserId = ?;");
            stm.setInt(1, res.getInt(1));
            ResultSet rs2 = stm.executeQuery();
            rs2.next();
            User user2 = new User(rs2.getString(2), rs2.getInt(1), rs2.getString(3));
            user2.setAdministrator(rs2.getBoolean(4));
            user2.setName(rs2.getString(6));
            user2.setSurname(rs2.getString(7));
            user2.setBirthDate(rs2.getDate(8));
            user2.setBirthPlace(rs2.getString(9));
            user2.setStatus(rs2.getString(10));
            result.add(user2);
        }
        return result;
    }

    @Override
    public List<User> getFriends(User user) throws SQLException {
        PreparedStatement stm = null;
        List<User> result = new ArrayList<>();
        stm = con.prepareStatement(
                "SELECT * FROM " + friendsTable + " WHERE (SenderId = ? or ReceiverId = ?) and Confirmed = ?;");
        stm.setInt(1, user.getUserId());
        stm.setInt(2, user.getUserId());
        stm.setBoolean(3, true);
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            stm = con.prepareStatement("select * from " + CreateTablesForTests.UsersTable + " where UserId = ?;");
            int id = res.getInt(1);
            if (res.getInt(2) != user.getUserId()) id = res.getInt(2);
            stm.setInt(1, id);
            ResultSet rs2 = stm.executeQuery();
            rs2.next();
            User user2 = new User(rs2.getString(2), rs2.getInt(1), rs2.getString(3));
            user2.setAdministrator(rs2.getBoolean(4));
            user2.setName(rs2.getString(6));
            user2.setSurname(rs2.getString(7));
            user2.setBirthDate(rs2.getDate(8));
            user2.setBirthPlace(rs2.getString(9));
            user2.setStatus(rs2.getString(10));
            result.add(user2);
        }
        return result;
    }

    @Override
    public boolean sendFriendRequest(int from, int to) throws SQLException {
        if (from == to) return false;
        if (areFriends(from, to) || isRequested(from, to) || isRequested(to, from)) return false;
        Date date = new Date(2020, 1, 1);
        PreparedStatement statement = con.prepareStatement("insert into " + friendsTable + " values (?, ?, ?, ?)");
        statement.setInt(1, from);
        statement.setInt(2, to);
        statement.setBoolean(3, false);
        statement.setDate(4, date);
        statement.executeUpdate();
        return true;
    }

    @Override
    public boolean isRequested(int SenderId, int ReceiverId) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select * from " + friendsTable + " where SenderId = ? and ReceiverId = ? and Confirmed = 0;");
        statement.setInt(1, SenderId);
        statement.setInt(2, ReceiverId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) return true;
        return false;
    }

    @Override
    public boolean areFriends(int user1, int user2) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select * from " + friendsTable + " where ((SenderId = ? and ReceiverId = ?) or (SenderId = ? and ReceiverId = ?)) and Confirmed = 1;");
        statement.setInt(1, user1);
        statement.setInt(2, user2);
        statement.setInt(3, user2);
        statement.setInt(4, user1);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) return true;
        return false;
    }

    @Override
    public boolean confirmFriendRequest(int senderId, int receiverId) throws SQLException {
        if (areFriends(senderId, receiverId)) {
            return false;
        }
        if (!isRequested(senderId, receiverId)) return false;
        PreparedStatement statement = con.prepareStatement("update " + friendsTable + " set Confirmed = ? where SenderId = ? and ReceiverId = ?;");
        statement.setBoolean(1, true);
        statement.setInt(2, senderId);
        statement.setInt(3, receiverId);
        statement.executeUpdate();
        return true;
    }

  /*  @Override
    public void addFriendship(int user1, int user2, Timestamp time) throws SQLException {
        Connection cn = null;
        PreparedStatement ps = cn.prepareStatement("Insert into `Friends` (`SenderId`, `ReceiverId`, `Confirmed`, `Date`) VALUE (?,?,?,?)");
        ps.setInt(1, user1);
        ps.setInt(2, user2);
        ps.setInt(3, 1);
        ps.setTimestamp(4, time);
        ps.executeQuery();
    }

   */

}
