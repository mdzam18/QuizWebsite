package Quiz;

import java.util.Set;

public class PictureResponseQuestion extends Question {

	private String imgURL;

	private static final int TYPE = QuestionType.PICTURE_RESPONSE_QUESTION;

	public PictureResponseQuestion(String question, Set<String> answers, String imgURL) {
		super(question, answers);
		this.imgURL = new String(imgURL);
	}

	public String getImage(){
		return imgURL;
	}

	@Override
	public int getType() {
		return TYPE;
	}

}
