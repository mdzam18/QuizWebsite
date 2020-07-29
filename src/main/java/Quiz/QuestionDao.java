package Quiz;

import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class QuestionDao {

    private Connection con;
    private String questionTable;

    private static final int QUESTION_ID = 1;
    private static final int QUESTION = 2;
    private static final int ANSWER = 3;
    private static final int TYPE = 4;
    private static final int SCORE = 5;
    private static final int QUIZ_ID = 6;


    public QuestionDao() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        questionTable = CreateTablesForTests.QuestionTable;
    }

    public Question addQuestion(String question, String answer, int quizId, int type) throws SQLException {
        Question q = null;
        PreparedStatement stm =
                con.prepareStatement("SELECT max(QuestionId) FROM " + questionTable);
        ResultSet rs = stm.executeQuery();
        int last = 0;
        if (rs.next()) {
            last = rs.getInt(1);
        }
        last++;

        stm = con.prepareStatement("INSERT INTO " + questionTable + "  VALUES (?, ?, ?, ?, ?, ?);");
        stm.setInt(QUESTION_ID, last);
        stm.setString(QUESTION, question);
        stm.setString(ANSWER, answer);
        stm.setInt(QUIZ_ID, quizId);
        stm.setInt(TYPE, type);
        stm.setInt(SCORE, 0);

        List<String> list = AnswerDelimiter.splitAnswers(rs.getString(ANSWER));
        Set<String> answers = new HashSet<String>(list);
        answers.addAll(list);

        q = new Question(question, answers);

        return q;
    }

    public Question getQuestion(int questionId) throws SQLException {
        PreparedStatement stm =
                con.prepareStatement("SELECT * FROM " + questionTable + " WHERE QuestionId = ?;");
        stm.setInt(1, questionId);
        ResultSet rs = stm.executeQuery();
        if(!rs.next()) return null;

        int type = rs.getInt(TYPE);

        Question q = createQuestionByType(rs, type);

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

    public List<Question> getQuizQuestions(int quizId) throws SQLException {
        PreparedStatement stm =
                con.prepareStatement("SELECT * FROM " + questionTable + " WHERE QuizId = ?;");
        stm.setInt(1, quizId);

        List<Question> questions = new ArrayList<Question>();

        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            Question q;
            int type = rs.getInt(TYPE);
            q = createQuestionByType(rs, type);
            questions.add(q);
        }

        return questions;
    }

    private Question createQuestionByType(ResultSet rs, int type) throws SQLException {
        Question q;

        if (type == 1){
            List<String> list = AnswerDelimiter.splitAnswers(rs.getString(ANSWER));
            Set<String> answers = new HashSet<String>(list);
            answers.addAll(list);
            q = new QuestionResponse(rs.getString(QUESTION), answers);
            q.setQuizId(rs.getInt(QUIZ_ID));
            q.setQuestionId(rs.getInt(QUESTION_ID));
            q.setScore(rs.getInt(SCORE));
        }
        else if (type == 2){
            Set<String> allAnswerOutput = new HashSet<>();
            Set<String> trueAnswerOutput = new HashSet<>();
            AnswerDelimiter.splitFewAnswers(rs.getString(ANSWER), allAnswerOutput, trueAnswerOutput);
            Iterator<String> it = trueAnswerOutput.iterator();
            String answer = it.next();
            q = new MultipleChoiceQuestion(rs.getString(QUESTION), allAnswerOutput, answer);
            q.setQuizId(rs.getInt(QUIZ_ID));
            q.setQuestionId(rs.getInt(QUESTION_ID));
            q.setScore(rs.getInt(SCORE));
        }
        else if (type == 3){
            List<String> list = AnswerDelimiter.splitAnswers(rs.getString(ANSWER));
            Set<String> answers = new HashSet<String>(list);
            answers.addAll(list);
            String[] imageArray = AnswerDelimiter.splitImage(rs.getString(QUESTION));
            q = new PictureResponseQuestion(rs.getString(QUESTION), answers, imageArray[1]);
            q.setQuizId(rs.getInt(QUIZ_ID));
            q.setQuestionId(rs.getInt(QUESTION_ID));
            q.setScore(rs.getInt(SCORE));
        }
        else if (type == 4){
            q = new MultipleAnswerQuestion(rs.getString(QUESTION), AnswerDelimiter.splitAnswers(rs.getString(ANSWER)), false);
            q.setQuizId(rs.getInt(QUIZ_ID));
            q.setQuestionId(rs.getInt(QUESTION_ID));
            q.setScore(rs.getInt(SCORE));
        }
        else {
            Set<String> allAnswerOutput = new HashSet<>();
            Set<String> trueAnswerOutput = new HashSet<>();
            AnswerDelimiter.splitFewAnswers(rs.getString(ANSWER), allAnswerOutput, trueAnswerOutput);
            q = new MultipleChoiceAnswerQuestion(rs.getString(QUESTION), trueAnswerOutput, allAnswerOutput);
            q.setQuizId(rs.getInt(QUIZ_ID));
            q.setQuestionId(rs.getInt(QUESTION_ID));
            q.setScore(rs.getInt(SCORE));
        }

        return q;
    }
}
