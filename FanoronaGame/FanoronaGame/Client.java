package FanoronaGame;

import java.net.*;
import java.io.*;


/**
* Interface for receiving commands from Client game program
*/
public class Client {
	String command;
	String newCommand; 
	Socket clientSocket = null; 

	public Client() {
		try {
			clientSocket = new Socket("Game Server", 6001);
			BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			command = "Connecting...\n";
			//command = fromUser.readLine();
			toServer.writeBytes(command + '\n');
			newCommand = fromServer.readLine();
			System.out.println("Response from Game Server: " + newCommand + '\n');
			clientSocket.close();
		} catch (UnknownHostException e) {
			System.err.println("Cannot resolve host: Game Server\n");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: Game Server\n");
			System.exit(1);
		}
	}
}