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
	private final String QUESTIONS_FILENAME="questions.txt";
	private final String ANSWERS_FILENAME="answers.txt";
	
	public HashMap<Integer,Question> importQuestions(){
		HashMap <Integer, Question> questions=new HashMap<>();
		int questionId=0;
		try {
			//criar metodo pra isso
			  String dir=System.getProperty("user.dir");
			  File file = new File(dir+"/src/config/"+QUESTIONS_FILENAME);
		      FileReader fileReader=new FileReader(file);
		      BufferedReader readFile = new BufferedReader(fileReader);
		 
		      String line = readFile.readLine(); // l� a primeira linha
		// a vari�vel "linha" recebe o valor "null" quando o processo
		// de repeti��o atingir o final do arquivo texto
		      line = readFile.readLine(); // l� da segunda at� a �ltima linha
		       
		        
		      while (line != null) {
		       // System.out.printf("%s\n", linha);
		 
		        if (line==null){
		        	continue;
		        }
		        Question question = new Question();
			    HashMap<Integer,String> answers=new HashMap<>();
			    
			   
		        
		        if (line.length()==0){
		        	//se a linha nao estiver em branco e nem identificar a dificuldade das questoes
			        //eh uma questao
		        	//incrementa o id da questao
		        	
		        	do{
		        		line=readFile.readLine();
			        	//le a linha
		        		if (line==null){
		        			break;
		        		}
		        		if (line.length()==0){
		        			continue;
		        		}
		        		if (line.equals("### Fácil") || line.equals("### Médio") || line.equals("### Difícil")){
		        			continue;
		        		}
		        		
		        		
		        		if (line.equals("### Answers")){
		 			    	line=readFile.readLine();
		 			    	break;
		 			    }
		        		
		        		else{
		        			//String [] splittedLine=linha.split("\n");
			        		String firstLetter=line.substring(0,1);
			        		//System.out.println(firstLetter);
			        		//System.out.println(firstLetter.equals("1"));
			        		if (!firstLetter.equals("1") && !firstLetter.equals("2") && !firstLetter.equals("3") && !firstLetter.equals("4")){
			        			questionId++;
				        		question.setTitle(line);
				        		//System.out.println(question.getTitle());
				        	}
				        	//perguntas
				        	else{
				        		//System.out.println("hora do else");
				        		//System.out.println(linha);
				        		int answerId=Integer.parseInt(line.substring(0,1));
				        		String answer=line.substring(2);
				        		answers.put(answerId, answer);
				        	}
			        	}
		        		
		        	}
		        	while (line.length()>0);
		        	question.setAnswers(answers);
		        	questions.put(questionId, question);
		     
		        }
		        
		      }
		 
		      fileReader.close();
		      readFile.close();
		      
		      //criar metodo pra isso
		      File fileAnswers = new File(dir+"/src/config/"+ANSWERS_FILENAME);
		      FileReader fileReaderAnswers=new FileReader(fileAnswers);
		      BufferedReader readAns = new BufferedReader(fileReaderAnswers);
		      
		      String lineAnswer=readAns.readLine();
		      if (lineAnswer.equals("### Answers")){

			    	lineAnswer=readAns.readLine();
			    	lineAnswer=readAns.readLine();

			    	for (int i=1;i<questions.size()+1;i++){
			    		int correctAnswerId=Integer.parseInt(lineAnswer);
			    		questions.get(i).setCorrectAnswer(correctAnswerId);
			    		lineAnswer=readAns.readLine();
			    	}	
		        	
		        }
		      
		      readAns.close();
		      fileReaderAnswers.close();
		      
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
