package FanoronaGame;

import java.net.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.*;

public class Server {
	String command;
	String newCommand;
	ServerSocket serverSocket = new ServerSocket(6001);
	Boolean isAscii = false;
	Boolean currentGame = true; 

	/**
	* Class for checking whether data in ASCII values
	*/
	public class StringUtils {
	  CharsetEncoder asciiEncoder = 
	      Charset.forName("US-ASCII").newEncoder();

		public Boolean parseInput(String command) {
			return asciiEncoder.canEncode(command);
		}
	}

	public class Timer {
		public Timer() {
			long time = System.currentTimeMillis();
		}
	}

	/**
	* 
	*/
	public Server() {
		while(currentGame) {
			try {
				Socket clientSocket = serverSocket.accept();
				BufferedReader fromClient = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
				DataOutputStream toClient = new DataOutputStream(serverSocket.getOutputStream());
				command = fromClient.readLine();
				isAscii = parseInput(command);

				if (isAscii) {
					System.out.println("Received: " + command);
					//execute code to get back newCommand
					toClient.writeBytes(newCommand);
				}
				else {
					System.out.println("Invalid non-ASCII value input received.\n" + 
						"Data has not been read for security purposes.\n" +
						"Game is now exiting...\n");
					currentGame = false; 
				}
			}
			catch (IOException e) {
				System.out.println("Could not listen on port: 6001\n");
				System.exit(-1);
			}

		}

	}

	private Boolean parseInput(String command2) {
		// TODO Auto-generated method stub
		return null;
	}
}