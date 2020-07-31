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

	public int checkAnswers(Set<String> userAnswers) {
		int result = 0;
		if(userAnswers.size() <= choices.size()) {
			Set<String> choicesNew = new HashSet<>();
			Set<String> userAnswersNew = new HashSet<>();
			for(String str : choices) {
				choicesNew.add(str.toUpperCase());
			}
			for(String str : userAnswers) {
				userAnswersNew.add(str.toUpperCase());
			}
			for(String str : userAnswersNew) {
				if(choicesNew.contains(str)) {
					result++;
				}
			}
		}
		return result;
	}

	@Override
	public int getType() {
		return TYPE;
	}

}
