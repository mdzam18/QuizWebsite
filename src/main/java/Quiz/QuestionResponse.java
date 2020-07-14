
<<<<<<< HEAD
/*
=======
>>>>>>> 2a06156c4c583d1d41a42ba5695d7932222e59f0
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
<<<<<<< HEAD
		return (answers.contains(answer));
 	}
	}
}*/

=======
        return (answers.contains(answer));
    }
}
>>>>>>> 2a06156c4c583d1d41a42ba5695d7932222e59f0
