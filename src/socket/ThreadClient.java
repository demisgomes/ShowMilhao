package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;

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
		
			System.out.println("Nova conex�o com o cliente " +
	           clientSocket.getInetAddress().getHostAddress()
			   ); // imprime o ip do cliente

			outputStream.flush();
			outputStream.writeObject("Seja Bem vindo ao Show do Milhão!\nDigite seu nome: ");
			
			String name=(String)inputStream.readObject();
			
			Player player=new Player(name);
			
			outputStream.flush();
			outputStream.writeObject("Bem vindo, "+player.getName()+"! Boa sorte!");
			
			Question question = show.askQuestion();
			
			outputStream.flush();
			outputStream.writeObject(question.getTitle()+
					"\n1 - "+question.getAnswers().get(1)+
					"\n2 - "+question.getAnswers().get(2)+
					"\n3 - "+question.getAnswers().get(3)+
					"\n4 - "+question.getAnswers().get(4));
			
			String response=(String)inputStream.readObject();
			try{
				if(Integer.parseInt(response)==question.getCorrectAnswer()){
					outputStream.flush();
					outputStream.writeObject("CERTA A RESPOSTA!");

					player.setMoney(player.getMoney()+1000);
				}
				
				else{
					outputStream.flush();
					outputStream.writeObject("QUE PENA, a resposta seria "+question.getAnswers().get(question.getCorrectAnswer()));
				}
			}
			catch(Exception e){
				outputStream.flush();
				outputStream.writeObject("QUE PENA, a resposta seria "+question.getAnswers().get(question.getCorrectAnswer()));
			
			}
			
			outputStream.flush();
			outputStream.writeObject("Você ficou com R$ "+player.getMoney());
		
			
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
