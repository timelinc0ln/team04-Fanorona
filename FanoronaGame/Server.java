

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public interface Server extends Commands {
	//public int sum(int a, int b) throws RemoteException;
	public Server() {}


	
	
	public void establishConnection {
		ServerSocket serverSocket = null
		Socket connection = null; 
		ObjectOutputStream out;
		ObjectInputStream in;
		String message;

		try {
			serverSocket = new serverSocket(4001);
			System.out.println("Waiting for connection");
			connection = serverSocket.accept();
			System.out.println("Connection received from " + 
				connection.getInetAddress().getHostName());
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());

			try {
				message = (String)in.readObject();
				System.out.println("client: " + message);
				sendCommand();
			} catch (ClassNotFoundException e) {
				System.err.println("Data formatting could not be understood.\n");
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port: 4001.\n");
			System.exit(1);
		}
	}

	public void sendCommand() {
		String message = "A connection has been established!\n";
		try{
			out.writeObject(message);
			out.flush();
			System.out.println("server: " + msg);
		} catch (IOException e) {
			System.out.println("An error has occurred in writing your command.\n");
			e.printStackTrace();
		}
	}

	public void main(String[] args) {
		Server server = new Server();
		 while (true) {
		 	server.establishConnection();
		 }
	}
}



// http://zerioh.tripod.com/ressources/sockets.html