package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ShowMilhaoServer {
	public static void main(String[] args) throws IOException {
		
		ShowMilhaoImplementation show=new ShowMilhaoImplementation();
		show.importQuestions();

        ServerSocket server = new ServerSocket(12345);
        System.out.println("Porta 12345 aberta!");
        // a continuação do servidor deve ser escrita aqui
        
        Socket client = server.accept();
        System.out.println("Nova conexão com o cliente " +
            client.getInetAddress().getHostAddress()
        ); // imprime o ip do cliente
        
        ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
        String message;
        while (true){
        	try {
    			message = (String) inputStream.readObject();
    			System.out.println(message);
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
           
        }
        //inputStream.close();
        //client.close();
        //server.close();

    }

}
