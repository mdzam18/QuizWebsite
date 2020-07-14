<<<<<<< HEAD

/*
package Quiz;

import java.util.*;
=======
package Quiz;

import java.util.List;
>>>>>>> 2a06156c4c583d1d41a42ba5695d7932222e59f0

public class Quiz {

	private int quizId;
	private boolean isRandom;
	private boolean isOnePage;
	private boolean isImmediate;
	private boolean inPracticeMode;
	private int numberOfQuestions;
	private String description;
	private List<Question> questions;
	private String category;
	private int creatorId;

	public Quiz(int quizId, int creatorId)
	{
		this.quizId = quizId;
		this.creatorId = creatorId;
	}

	public void setQuizId(int quizId){
		this.quizId = quizId;
	}

	public void setIsRandom(boolean isRandom){
		this.isRandom = isRandom;
	}

	public void isOnePage(boolean isOnePage){
		this.isOnePage = isOnePage;
	}

	public void setIsImmediate(boolean isImmediate){
		this.isImmediate = isImmediate;
	}

	public void setQuestionCount(int numberOfQuestions){
		this.numberOfQuestions = numberOfQuestions;
	}

	public void setCreator(int creatorId){
		this.creatorId = creatorId;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setQuestionSet(List<Question> questions){
		this.questions = questions;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuizId(){
		return quizId;
	}

	public boolean isRandom(){
		return isRandom;
	}

	public boolean isOnePage(){
		return isOnePage;
	}

	public boolean isImmediate(){
		return isImmediate;
	}

	public int getQuestionCount(){
		return numberOfQuestions;
	}

	public int getCreator(){
		return creatorId;
	}

	public String getDescription(){
		return description;
	}

	public List<Question> getQuestionSet(){
		return questions;
	}

	public String getCategory() { return category; }

}
