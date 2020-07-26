package Quiz;

import java.util.*;

public class Question {

    private final int GENERAL_TYPE = 0;
    protected String question;
    protected Set<String> answers;
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

}
