package Quiz;

import java.util.*;

public class MultipleAnswerQuestion extends Question {

    private boolean keepOrder;
    private List<String> orderedAnswers;

    private static final int TYPE = QuestionType.MULTI_ANSWER_QUESTION;

    public MultipleAnswerQuestion(String question, List<String> answers, boolean keepOrder) {
        super(question, new HashSet<>(answers));
        this.keepOrder = keepOrder;
        this.orderedAnswers = new ArrayList<>(answers);
    }

    public boolean isOrdered() {
        return keepOrder;
    }

    public List<String> getOrderedAnswers() {
        return orderedAnswers;
    }

    @Override
    public int getType() {
        return TYPE;
    }

}
