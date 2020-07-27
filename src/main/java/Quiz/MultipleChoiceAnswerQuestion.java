package Quiz;

import java.util.*;

public class MultipleChoiceAnswerQuestion extends Question {

	private Set<String> choices;

	private static final int TYPE = QuestionType.MULTIPLE_CHOICE_AND_ANSWER_QUESTION;

	public MultipleChoiceAnswerQuestion(String question, Set<String> answers, Set<String> choices) {
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
