package Quiz;

import java.util.List;

public class QuestionPassResult {
    public static final byte FULL_QUESTION_PASS = 2;
    public static final byte PARTIAL_QUESTION_PASS = 1;
    public static final byte NOT_QUESTION_PASS = 0;

    public final int userId;
    public final Question question;
    public int userScore;
    public byte passType;

    public QuestionPassResult(int userId, Question question, List<String> userAnswers) {
        this.userId = userId;
        this.question = question;
        userScore = 0;
        passType = NOT_QUESTION_PASS;
    }

    public void setPassType(byte passType) {
        this.passType = passType;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

}
