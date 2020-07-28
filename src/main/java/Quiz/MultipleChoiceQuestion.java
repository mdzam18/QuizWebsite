package Quiz;

import java.util.*;

public class MultipleChoiceQuestion extends Question {

	private String choice;

	private static final int TYPE = QuestionType.MULTIPLE_CHOICE_QUESTION;

	public MultipleChoiceQuestion(String question, Set<String> answers, String choice) {
		super(question, answers);
		this.choice = choice;
	}

	public String getChoices() {
		return choice;
	}

	@Override
	public int getType() {
		return TYPE;
	}

}
