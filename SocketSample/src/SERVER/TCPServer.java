package SERVER;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			String sentence_from_client = "";
			String sentence_to_client = "";
			System.out.println("SERVER: WAITING FOR CONNECT...");
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("SERVER: CLIENT CONNECTED");
				BufferedReader inputFromClient = 
						new BufferedReader(new InputStreamReader(socket.getInputStream()));
				DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
				
				sentence_from_client = inputFromClient.readLine();
				System.out.println("SERVER: Message recived: '" + sentence_from_client + "'!");
				sentence_to_client = "SERVER RESPONSE: Message: '" + sentence_from_client + "' received!\n";
				outputStream.writeBytes(sentence_to_client);
				System.out.println("SERVER CLOSING...");
				return;
			}
		} catch (Exception ex) { 
			ex.printStackTrace();
		}
	}

}
