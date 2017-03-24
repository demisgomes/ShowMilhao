package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ShowMilhaoClient {
	public static void main(String[] args) throws UnknownHostException, IOException{
		Socket client = new Socket("127.0.0.1",12345);
		System.out.println("O cliente se conectou ao servidor!");
		ObjectOutputStream outputStream=new ObjectOutputStream(client.getOutputStream());
		ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
		
		try {
			String message = (String) inputStream.readObject();
			System.out.println(message);
			//System.out.println(receiveMessage(client).toString());
			//System.out.println(receiveMessage(client).toString());
			
			Scanner keyboard = new Scanner(System.in);
			outputStream.flush();
			outputStream.writeObject(keyboard.nextLine());
			
			String message2 = (String) inputStream.readObject();
			System.out.println(message2);
			
			String question = (String) inputStream.readObject();
			System.out.println(question);
			
			outputStream.flush();
			outputStream.writeObject(keyboard.nextLine());
			
			String questionAnswer = (String) inputStream.readObject();
			System.out.println(questionAnswer);
			
			String money = (String) inputStream.readObject();
			System.out.println(money);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
