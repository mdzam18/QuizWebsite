package Quiz;

import ProfilePackage.ProfileDataSrc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class QuizDAO {

    private Connection connection;
    private Map<Integer, Quiz> data;

    public final static String TABLE_NAME  = "oop_base.Quiz";

    private static final int QUIZ_ID = 1;
    private static final int IS_RANDOM = 2;
    private static final int IS_ONE_PAGE = 3;
    private static final int IS_IMMEDIATE = 4;
    private static final int IN_PRACTICE_MODE = 5;
    private static final int NUMBER_QUESTIONS = 6;
    private static final int DESCRIPTION = 7;
    private static final int CATEGORY = 8;
    private static final int CREATOR_ID = 9;


    public QuizDAO() throws SQLException, ClassNotFoundException {
        connection = ProfileDataSrc.getConnection();
        getData();
    }

    private void getData() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + TABLE_NAME);

        data = new HashMap<Integer, Quiz>();

        while (resultSet.next()){
            int quizId = resultSet.getInt(QUIZ_ID);
            int creatorId = resultSet.getInt(CREATOR_ID);

            Quiz quiz = new Quiz(quizId, creatorId);

            quiz.setQuestionCount(resultSet.getInt(NUMBER_QUESTIONS));
            quiz.setDescription(resultSet.getString(DESCRIPTION));
            quiz.setCategory(resultSet.getString(CATEGORY));

            data.put(quizId, quiz);
        }
    }

    private void addToData(Quiz quiz){
        if (data.containsKey(quiz.getQuizId()) == false)
            data.put(quiz.getQuizId(), quiz);
    }
}
