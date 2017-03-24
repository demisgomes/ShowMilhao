package socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import domain.Question;

public class ShowMilhaoImplementation implements IShowMilhao {
	
	//nome do arquivo de perguntas
	private final String FILENAME="questions.txt";
	
	public HashMap<Integer,Question> importQuestions(){
		HashMap <Integer, Question> questions=new HashMap<>();
		try {
			  String dir=System.getProperty("user.dir");
			  File file = new File(dir+"/src/config/"+FILENAME);
		      FileReader fileReader=new FileReader(file);
		      BufferedReader lerArq = new BufferedReader(fileReader);
		 
		      String linha = lerArq.readLine(); // l� a primeira linha
		// a vari�vel "linha" recebe o valor "null" quando o processo
		// de repeti��o atingir o final do arquivo texto
		      while (linha != null) {
		        System.out.printf("%s\n", linha);
		 
		        linha = lerArq.readLine(); // l� da segunda at� a �ltima linha
		      }
		 
		      fileReader.close();
		    } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }
		return questions;
	}

	@Override
	public void selectQuestion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int answerQuestion(int answerId) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
