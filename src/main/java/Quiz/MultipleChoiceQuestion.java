
package Quiz;

import java.util.*;

public class MultipleChoiceQuestion extends Question {

	private String question;
	private Set<String> answers;
	private Set<String> choices;

	private static final int TYPE = QuestionType.MULTIPLE_CHOICE_QUESTION;

	public MultipleChoiceQuestion(String question, Set<String> answers, Set<String> choices) {
		super(question, answers);
		this.choices = choices;
	}

	public boolean checkAnswer(String answer) {
		return (answers.contains(answer));
	}

	public Set<String> getChoices() {
		return choices;
	}
}
