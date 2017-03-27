package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ConnectionService {
	
	public void sendData(ObjectOutputStream outputStream, String message) throws IOException{
		outputStream.flush();
		outputStream.writeObject(message);
	
	}
	
	public Object receiveData(ObjectInputStream inputStream) throws ClassNotFoundException, IOException{
		Object data=inputStream.readObject();
		return data;
	}

}
