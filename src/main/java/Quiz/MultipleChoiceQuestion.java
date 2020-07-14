import java.util.*;

public class MultipleChoiceQuestion extends Question {

	private String question;
	private Set<String> answers;
	private Set<String> choices;

	private static final TYPE = QuestionType.MULTIPLE_CHOICE;

	public MultipleChoiceQuestion(String question, Set<String> answers, Set<String> choices) {
		super(question, answers);
		this.choices = choices;
	}

	public boolean checkAnswer(String answer) {
		return (set.contains(answer));
	}

	public Set<String> getChoices() {
		return choices;
	}
}