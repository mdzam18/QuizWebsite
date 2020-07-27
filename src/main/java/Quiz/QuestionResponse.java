package Quiz;

import java.util.*;

public class QuestionResponse extends Question {

	private static final int TYPE = QuestionType.QUESTION_RESPONSE;

	public QuestionResponse(String question, Set<String> answers) {
		super(question, answers);
	}

	@Override
	public int getType() {
		return TYPE;
	}

}
