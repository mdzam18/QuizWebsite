package Quiz;

import java.util.*;

public class Question {

    private final int GENERAL_TYPE = 0;
    protected String question;
    protected Set<String> answers;
    private int quizId;
    private int questionId;
    protected int score;

    public Question(String question, Set<String> answers) {
        this.question = question;
        this.answers = new HashSet<>(answers);
        this.score = 0;
    }

    public String getQuestion() {
        return question;
    }

    public Set<String> getAnswerSet() {
        return answers;
    }

    public boolean checkAnswer(String answer) {
        return answers.contains(answer);
    }

    public int getType() {
        return GENERAL_TYPE;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getQuizId() { return quizId; }

    public void setQuizId(int quizId) { this.quizId = quizId; }

    public int getQuestionId() { return questionId; }

    public void setQuestionId(int questionId) { this.questionId = questionId; }

}
