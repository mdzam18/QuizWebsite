package ProfilePackage;


import java.sql.*;
import java.util.ArrayList;

public class MailSqlDao implements MailDao {
    private static final String MailId = "MailId";
    private static final String SenderId = "SenderId";
    private static final String ReceiverId = "ReceiverId";
    private static final String Type = "Type";
    private static final String Message = "Message";
    private static final String Date = "Date";
    private static final String Seen = "Seen";
    private Connection con;

    public MailSqlDao() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
         //con = NanukaDatabase.getConnection();
    }

    private Mail getMail(ResultSet rs) throws SQLException {
        return new Mail(rs.getInt(MailId),
                rs.getInt(SenderId),
                rs.getInt(ReceiverId),
                rs.getString(Type),
                rs.getString(Message),
                rs.getDate(Date),
                rs.getInt(Seen)
        );
    }

    @Override
    public Mail getMailById(int mailId) throws SQLException {
        //NanukaDatabase.closeConnection();
        //con = NanukaDatabase.getConnection();

        PreparedStatement stm = con.prepareStatement("Select * from " + CreateTablesForTests.MailsTable + " where MailId = ?;");
        stm.setInt(1, mailId);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return getMail(rs);
        }
        return null;
    }

    @Override
    public boolean sendMail(Mail mail) throws SQLException {
        //NanukaDatabase.closeConnection();
        //con = NanukaDatabase.getConnection();

        PreparedStatement statement = con.prepareStatement(
                "SELECT max(MailId) FROM " + CreateTablesForTests.MailsTable + ";");
        ResultSet res = statement.executeQuery();
        int id = 0;
        res.next();
        if (res != null) {
            id = res.getInt(1);
        }
        id++;
        PreparedStatement ps = con.prepareStatement("insert into " + CreateTablesForTests.MailsTable + " values (?, ?, ?,?,?,?,?);");
        ps.setInt(1, id);
        ps.setInt(2, mail.getSenderId());
        ps.setInt(3, mail.getReceiverId());
        ps.setString(4, mail.getType());
        ps.setString(5, mail.getMessage());
        ps.setDate(6, mail.getDate());
        ps.setBoolean(7, mail.isSeen());
        ps.executeUpdate();
        return true;
    }

    @Override
    public ArrayList<Mail> getMails(int userId) throws SQLException {
        //NanukaDatabase.closeConnection();
        //con = NanukaDatabase.getConnection();

        ArrayList<Mail> result = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("Select * from " + CreateTablesForTests.MailsTable + " where ReceiverId = ?;");
        stm.setInt(1, userId);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            result.add(getMail(rs));
        }
        return result;
    }

    @Override
    public boolean deleteMailById(int mailId) throws SQLException {
        //NanukaDatabase.closeConnection();
        //con = NanukaDatabase.getConnection();

        PreparedStatement stm = con.prepareStatement("Delete from " + CreateTablesForTests.MailsTable + " Where MailId = ?;");
        stm.setInt(1, mailId);
        stm.executeUpdate();
        return true;
    }
}
