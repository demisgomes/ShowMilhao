package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.HashMap;

import domain.Player;
import domain.Question;

public class ThreadClient implements Runnable{
	Socket clientSocket;
	ShowMilhaoImplementation show;
	
	public ThreadClient(Socket client) throws IOException {
		clientSocket=client;
		show=new ShowMilhaoImplementation();
	}

	@Override
	public void run() {
		try {
			ObjectOutputStream outputStream=new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream inputStream=new ObjectInputStream(clientSocket.getInputStream());
		
			ConnectionService connService=new ConnectionService();
			
			System.out.println("Nova conex�o com o cliente " +
	           clientSocket.getInetAddress().getHostAddress()
			   ); // imprime o ip do cliente

			connService.sendData(outputStream, "Seja Bem vindo ao Show do Milhão!\nDigite seu nome: ");
			String name=(String)connService.receiveData(inputStream);
			
			Player player=new Player(name);
			connService.sendData(outputStream, "Bem vindo, "+player.getName()+"! Boa sorte!");
			
			HashMap<Integer,Question> questionsSelected=show.selectQuestions();
			int questionsNumber=1;
			boolean wrongAnswer=false;
			//for(int i=1;i<questionsSelected.size()+1;i++)
			while (questionsNumber<questionsSelected.size() && !wrongAnswer){
				Question question=questionsSelected.get(questionsNumber);
				connService.sendData(outputStream, question.getTitle()+
						"\n1 - "+question.getAnswers().get(1)+
						"\n2 - "+question.getAnswers().get(2)+
						"\n3 - "+question.getAnswers().get(3)+
						"\n4 - "+question.getAnswers().get(4)+
						"\nAcertar: R$ "+player.getNextMoney()+" , Errar: R$ 0.00 , "+"Parar: R$ "+player.getMoney()+
						"\nP - Pular, S - Parar");
				
					String response=(String)connService.receiveData(inputStream);
					
					String correctResponse="";
					try{
						if(Integer.parseInt(response)==question.getCorrectAnswer()){
							correctResponse="CERTA A RESPOSTA!";
							player.setMoney(player.getMoney()+100000);
							questionsNumber+=1;
						}
						
						else{
							correctResponse="QUE PENA, a resposta seria "+question.getAnswers().get(question.getCorrectAnswer());
							wrongAnswer=true;
						}
					}
					catch(Exception e){
						correctResponse="QUE PENA, a resposta seria "+question.getAnswers().get(question.getCorrectAnswer());
						wrongAnswer=true;
						
					}
					finally{
						connService.sendData(outputStream, correctResponse);
					}
			}		
			connService.sendData(outputStream, "Acabou! Você ficou com "+player.getMoney());

			inputStream.close();
			outputStream.close();
			clientSocket.close();		
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
