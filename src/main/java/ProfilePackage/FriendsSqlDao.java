package ProfilePackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendsSqlDao implements FriendsDao {
    private Connection con;

    public FriendsSqlDao() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
    }


    @Override
    public boolean sendFriendRequest(User from, User to) {
        return false;
    }

    @Override
    public boolean confirmFriendRequest(User from, User to) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteFriend(User from, User to) throws SQLException {
        return false;
    }

    @Override
    public List<User> getSentRequests(User user) throws SQLException {
        return null;
    }

    @Override
    public List<User> getReceivedRequests(User user) throws SQLException {
        return null;
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
