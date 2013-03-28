package FanoronaGame;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

import FanoronaGame.Commands;

public class Client {

	private Client() {}

	public static void main(String[] args) {
		String host = (args.length < 1) ? null : args[0];
		// set the security manager for the client
		System.setSecurityManager(new RMISecurityManager());
		// get the remote object from the registry
		
		try {
			System.out.println("Security Manager loaded");
			String url = "//localhost/SERVER";
			Server remoteObject = (Server) Naming.lookup(url);
			System.out.println("Got remote object");
			//System.out.println(" 1 + 2 = " + remoteObject.sum(1, 2));
			Registry registry = LocateRegistry.getRegistry(host);
			Commands stub = (Commands) registry.lookup("Commands");
			String response = stub.sendCommand();
			System.out.println("response: " + response);
			
		} catch (RemoteException exc) {
			
			System.out.println("Error in lookup: " + exc.toString());
			
		} catch (java.net.MalformedURLException exc) {
			
			System.out.println("Malformed URL: " + exc.toString());
			
		} catch (java.rmi.NotBoundException exc) {
			
			System.out.println("NotBound: " + exc.toString());
			
		}
	}
}
