package FanoronaGame;

import java.net.*;
import java.io.*;

public class Server {
	String command;
	String newCommand;
	ServerSocket socket = new ServerSocket(6001);

	public Server() {
		while(true) {
			try {
				Socket clientSocket = socket.accept();
				BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
				command = fromClient.readLine();
				System.out.println("Received: " + command);
				//execute code to get back newCommand
				toClient.writeBytes(newCommand);
			}
			catch (IOException e) {
				System.out.println("Could not listen on port: 6001\n");
				System.exit(-1);
			}

		}

	}
}