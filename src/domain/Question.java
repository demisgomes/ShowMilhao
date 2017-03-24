package domain;

import java.util.HashMap;

public class Question {

	private String title;
	private HashMap<Integer, String> answers;
	private int correctAnswer;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public HashMap<Integer, String> getAnswers() {
		return answers;
	}
	public void setAnswers(HashMap<Integer, String> answers) {
		this.answers = answers;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
}
