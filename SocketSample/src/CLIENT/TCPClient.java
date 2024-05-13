package CLIENT;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sentence_to_server = "";
		String sentence_from_server = "";
		try {
			
			System.out.print("Write Message here: ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			sentence_to_server = reader.readLine() + "\n";
			Socket socket = new Socket("127.0.0.1", 8080);
			System.out.println("CLIENT: Connected to port: " + socket.getPort());
			
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			
			BufferedReader readerCTS = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			dataOutputStream.writeBytes(sentence_to_server);
			
			sentence_from_server = readerCTS.readLine();
			
			System.out.println("CLIENT: FROM SERVER: '" + sentence_from_server + "'");
			
			socket.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
