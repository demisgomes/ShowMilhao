package socket;

import domain.Question;

public interface IShowMilhao {
	public void selectQuestion();
	public Question askQuestion();
	int answerQuestion(int answerId, Question question);

}
