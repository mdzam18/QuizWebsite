package Quiz;

import java.util.*;

public class MultipleChoiceQuestion extends Question {

	private Set<String> choices;

	private static final int TYPE = QuestionType.MULTIPLE_CHOICE_QUESTION;

	public MultipleChoiceQuestion(String question, Set<String> answers, Set<String> choices) {
		super(question, answers);
		this.choices = new HashSet<>(choices);
	}

	public Set<String> getChoices() {
		return choices;
	}

	@Override
	public int getType() {
		return TYPE;
	}

}
