package socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ShowMilhaoClient {
	public static void main(String[] args) throws UnknownHostException, IOException{
		Socket client = new Socket("127.0.0.1",12345);
		System.out.println("O cliente se conectou ao servidor!");
		
		Scanner keyboard = new Scanner(System.in);
	 	ObjectOutputStream outputStream=new ObjectOutputStream(client.getOutputStream());
	    while (keyboard.hasNextLine()) {
	        // lê a linha e faz algo com ela
	   
	    	outputStream.flush();
	    	outputStream.writeObject(keyboard.nextLine());
	    }
		outputStream.close();
	    keyboard.close();
	}
    

}
