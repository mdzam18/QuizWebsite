<<<<<<< HEAD

/*
=======
=======
>>>>>>> 2a06156c4c583d1d41a42ba5695d7932222e59f0
package Quiz;

import java.util.Set;

public class PictureResponseQuestion extends Question {

	private String question;
	private String imgURL;
	private Set<String> answers;

	private static final int TYPE = QuestionType.PICTURE_RESPONSE_QUESTION;

	public PictureResponseQuestion(String question, Set<String> answers, String imgURL) {
		super(question, answers);
		this.imgURL = imgURL;
	}

	public boolean checkAnswer(String answer) {
		return (answers.contains(answer));
	}

	public String getImage(){
		return imgURL;
	}
}