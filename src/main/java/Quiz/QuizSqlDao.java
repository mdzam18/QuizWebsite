package Quiz;

import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class QuizSqlDao implements QuizDao{

    private Connection con;
    private String quizTable;
    
//    private Map<Integer, Quiz> data;
//
//    public final static String TABLE_NAME  = "oop_base.Quiz";
//
//    private static final int QUIZ_ID = 1;
//    private static final int IS_RANDOM = 2;
//    private static final int IS_ONE_PAGE = 3;
//    private static final int IS_IMMEDIATE = 4;
//    private static final int IN_PRACTICE_MODE = 5;
//    private static final int NUMBER_QUESTIONS = 6;
//    private static final int DESCRIPTION = 7;
//    private static final int CATEGORY = 8;
//    private static final int CREATOR_ID = 9;


    public QuizSqlDao() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        quizTable = CreateTablesForTests.QuizTableTest;
    }
    
    @Override
    public Quiz addQuiz(int creatorId) throws SQLException {
        Quiz quiz = null;
        PreparedStatement stm =
                con.prepareStatement("SELECT max(QuizId) FROM " + quizTable);
        ResultSet rs = stm.executeQuery();
        int quizId = 0;
        if (rs.next()) {
            quizId = rs.getInt(1);
        }
        quizId++;
        
        stm = con.prepareStatement("INSERT INTO " + quizTable + "  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        stm.setInt(1, quizId);
        stm.setBoolean(2, false);
        stm.setBoolean(3, false);
        stm.setBoolean(4, false);
        stm.setBoolean(5, false);
        stm.setInt(6, 0);
        stm.setString(7, null);
        stm.setString(8, null);
        stm.setInt(9, creatorId);
        stm.executeUpdate();
        quiz = new Quiz(quizId, creatorId);
        return quiz;
    }
    
    @Override
    public Quiz getQuiz(int quizId) throws SQLException {
        PreparedStatement stm =
                con.prepareStatement("SELECT * FROM " + quizTable + " WHERE QuizId = ?;");
        stm.setInt(1, quizId);
        ResultSet rs = stm.executeQuery();
        if(!rs.next()) return null;
        Quiz quiz = new Quiz(quizId, rs.getInt(9));
        return quiz;
    }
    
    @Override
    public boolean deleteQuiz(Quiz quiz) throws SQLException {
        PreparedStatement stm =
                con.prepareStatement("DELETE FROM " + quizTable + " WHERE QuizId = ?;");
        stm.setInt(1, quiz.getQuizId());
        int n = stm.executeUpdate();
        if(n == 1) return true;
        return false;
    }

    @Override
    public Connection getConnection(){
        return con;
    }

//    private void getData() throws SQLException {
//        Statement stmt = connection.createStatement();
//        ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + TABLE_NAME);
//
//        data = new HashMap<Integer, Quiz>();
//
//        while (resultSet.next()){
//            int quizId = resultSet.getInt(QUIZ_ID);
//            int creatorId = resultSet.getInt(CREATOR_ID);
//
//            Quiz quiz = new Quiz(quizId, creatorId);
//
//            quiz.setQuestionCount(resultSet.getInt(NUMBER_QUESTIONS));
//            quiz.setDescription(resultSet.getString(DESCRIPTION));
//            quiz.setCategory(resultSet.getString(CATEGORY));
//
//            data.put(quizId, quiz);
//        }
//    }
//
//    private void addToData(Quiz quiz){
//        if (data.containsKey(quiz.getQuizId()) == false)
//            data.put(quiz.getQuizId(), quiz);
//    }
}
