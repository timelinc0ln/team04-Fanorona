package FanoronaGame;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import FanoronaGame.Commands;

public interface Server implements Commands extends Remote {
	//public int sum(int a, int b) throws RemoteException;
	public Server() {}

	public String sendCommand() {
		return "A connection has been established!\n";

	}
	
	public void main(String[] args) {
		try {
			Server gameClient = new Server();
			Commands newConnection = (Commands) UnicastRemoteObject.exportObject(obj, 0);
			Registry registry = LocateRegistry.getRegistry(); 
			registry.bind("Commands", stub);

			System.err.println("Server ready");
		} catch(Exception e) {
			System.err.println("Server exceptoin: " + e.toString());
			e.printStackTrace();
		}
	}
}
