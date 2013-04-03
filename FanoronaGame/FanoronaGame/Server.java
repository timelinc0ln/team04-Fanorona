package FanoronaGame;

import java.net.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class Server extends Board {
	String command;
	String newCommand;
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
				ServerSocket serverSocket = new ServerSocket(6001);
				Socket clientSocket = serverSocket.accept();
				ObjectInputStream fromClient = new ObjectInputStream(clientSocket.getInputStream());
				ObjectOutputStream toClient = new ObjectOutputStream(clientSocket.getOutputStream()); 
				command = fromClient.readLine();
				StringUtils checkData = new StringUtils();
				isAscii = checkData.parseInput(command);

				if (isAscii) {
					System.out.println("Received: " + command);
					//execute code to get back newCommand
					toClient.writeBytes(newCommand);
				}
				else {
					System.out.println("Invalid non-ASCII value input received.\n" + 
						"Data has not been read for security purposes.\n" +
						"Game is now exiting...\n");
					serverSocket.close();
					currentGame = false; 
				}
			}
			catch (IOException e) {
				System.out.println("Could not listen on port: 6001\n");
				System.exit(-1);
			}

		}

	}
}