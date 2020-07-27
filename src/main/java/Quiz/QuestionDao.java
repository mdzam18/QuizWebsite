package Quiz;

import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDao {

    private Connection con;
    private String questionTable;

    private static final int QUESTION_ID = 1;
    private static final int QUESTION = 2;
    private static final int ANSWER = 3;
    private static final int QUIZ_ID = 4;
    private static final int TYPE = 5;


    public QuestionDao() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        questionTable = CreateTablesForTests.QuestionTableTest;
    }

    public Question addQuestion(int questionId, String question, String answer, int quizId) throws SQLException {
        Question q = null;
        PreparedStatement stm =
                con.prepareStatement("SELECT max(QuestionId) FROM " + questionTable);
        ResultSet rs = stm.executeQuery();
        int last = 0;
        if (rs.next()) {
            last = rs.getInt(1);
        }
        last++;

        stm = con.prepareStatement("INSERT INTO " + questionTable + "  VALUES (?, ?, ?, ?, ?);");
        stm.setInt(QUESTION_ID, questionId);
        stm.setString(QUESTION, null);
        stm.setString(ANSWER, null);
        stm.setInt(QUIZ_ID, quizId);
        stm.setInt(TYPE, 0);

        q = new Question(question, AnswerDelimiter.splitAnswer(answer));

        return q;
    }

    public Question getQuestion(int questionId) throws SQLException {
        PreparedStatement stm =
                con.prepareStatement("SELECT * FROM " + questionTable + " WHERE QuestionId = ?;");
        stm.setInt(1, questionId);
        ResultSet rs = stm.executeQuery();
        if(!rs.next()) return null;

        Question q = new Question(rs.getString(QUESTION), AnswerDelimiter.splitAnswer(rs.getString(ANSWER)));
        return q;
    }

    public boolean deleteQuestion(String question) throws SQLException {
        PreparedStatement stm =
                con.prepareStatement("DELETE FROM " + questionTable + " WHERE question = ?;");
        stm.setString(1, question);
        int n = stm.executeUpdate();

        if(n == 1) return true;
        return false;
    }
}
