package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ShowMilhaoServer {
	public static void main(String[] args) throws IOException {
		
		//show.selectQuestions();

        ServerSocket server = new ServerSocket(12345);
        System.out.println("Porta 12345 aberta!");
        // a continua��o do servidor deve ser escrita aqui
        
         while (true){
        	Socket client = server.accept();
        	ThreadClient threadClient = new ThreadClient(client);
        	new Thread(threadClient).start();
           
        }

    }

}
