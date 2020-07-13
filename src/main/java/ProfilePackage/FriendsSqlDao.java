package ProfilePackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendsSqlDao implements FriendsDao {
    private Connection con;
    private String friendsTable;

    public FriendsSqlDao() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        friendsTable = CreateTablesForTests.FriendsTable;
    }

    @Override
    public boolean sendFriendRequest(User from, User to) throws SQLException {
        if (from.getUserId() == to.getUserId()) return false;
        PreparedStatement statement = con.prepareStatement("select * from " + friendsTable + " where SenderId = ? and ReceiverId = ?;");
        statement.setInt(1, from.getUserId());
        statement.setInt(2, to.getUserId());
        ResultSet res = statement.executeQuery();
        if (res.next()) return false;
        statement = con.prepareStatement("select * from " + friendsTable + " where SenderId = ? and ReceiverId = ?;");
        statement.setInt(1, to.getUserId());
        statement.setInt(2, from.getUserId());
        if (res.next()) return false;
        statement = con.prepareStatement("insert into " + friendsTable + " values (?, ?, ?)");
        statement.setInt(1, from.getUserId());
        statement.setInt(2, to.getUserId());
        statement.setBoolean(3, false);
        statement.executeUpdate();
        return true;
    }

    @Override
    public boolean confirmFriendRequest(User from, User to) throws SQLException {
        PreparedStatement statement = con.prepareStatement("update " + friendsTable + " set Confirmed = ? where SenderId = ? and ReceiverId = ?;");
        statement.setBoolean(1, true);
        statement.setInt(2, from.getUserId());
        statement.setInt(3, to.getUserId());
        statement.executeUpdate();
        return true;
    }

    @Override
    public boolean deleteFriend(User from, User to) throws SQLException {
        PreparedStatement stm = null;
        stm = con.prepareStatement(
                "delete from " + friendsTable + " where (SenderId = ? and ReceiverId = ?) or (SenderId = ? and ReceiverId = ?);");
        stm.setInt(1, from.getUserId());
        stm.setInt(2, to.getUserId());
        stm.setInt(3, to.getUserId());
        stm.setInt(4, from.getUserId());
        stm.executeUpdate();
        return true;
    }

    @Override
    public List<User> getSentRequests(User user) throws SQLException {
        PreparedStatement stm = null;
        List<User> result = new ArrayList<>();
        stm = con.prepareStatement(
                "SELECT * FROM " + friendsTable + "WHERE (SenderId = ?) and Confirmed = ?;");
        stm.setInt(1, user.getUserId());
        stm.setBoolean(3, false);
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            User user2 = new User(res.getString(2), res.getInt(1), res.getString(3));
            user2.setName(res.getString(6));
            user2.setSurname(res.getString(7));
            user2.setBirthDate(res.getDate(8));
            user2.setBirthPlace(res.getString(9));
            user2.setStatus(res.getString(10));
            result.add(user2);
        }
        return result;
    }

    @Override
    public List<User> getReceivedRequests(User user) throws SQLException {
        PreparedStatement stm = null;
        List<User> result = new ArrayList<>();
        stm = con.prepareStatement(
                "SELECT * FROM " + friendsTable + "WHERE (ReceiverId = ?) and Confirmed = ?;");
        stm.setInt(1, user.getUserId());
        stm.setBoolean(3, false);
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            User user2 = new User(res.getString(2), res.getInt(1), res.getString(3));
            user2.setName(res.getString(6));
            user2.setSurname(res.getString(7));
            user2.setBirthDate(res.getDate(8));
            user2.setBirthPlace(res.getString(9));
            user2.setStatus(res.getString(10));
            result.add(user2);
        }
        return result;
    }

    @Override
    public List<User> getFriends(User user) throws SQLException {
        PreparedStatement stm = null;
        List<User> result = new ArrayList<>();
        stm = con.prepareStatement(
                "SELECT * FROM " + friendsTable + "WHERE (SenderId = ? or ReceiverId = ?) and Confirmed = ?;");
        stm.setInt(1, user.getUserId());
        stm.setInt(2, user.getUserId());
        stm.setBoolean(3, true);
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            User user2 = new User(res.getString(2), res.getInt(1), res.getString(3));
            user2.setName(res.getString(6));
            user2.setSurname(res.getString(7));
            user2.setBirthDate(res.getDate(8));
            user2.setBirthPlace(res.getString(9));
            user2.setStatus(res.getString(10));
            result.add(user2);
        }
        return result;
    }
}
