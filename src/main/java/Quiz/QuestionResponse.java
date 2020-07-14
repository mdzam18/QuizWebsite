package Quiz;

import java.util.*;

public class QuestionResponse extends Question {

	private String question;
	private Set<String> answers;

	private static final int TYPE = QuestionType.QUESTION_RESPONSE;

	public QuestionResponse(String question, Set<String> answers) {
		super(question, answers);
	}

	public boolean checkAnswer(String answer) {
        return (answers.contains(answer));
    }

}
