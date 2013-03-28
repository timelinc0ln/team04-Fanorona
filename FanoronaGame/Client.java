package FanoronaGame;

import java.rmi.*;
import java.rmi.server.*;

public class Client {
	public static void main(String[] args) {
		
		// set the security manager for the client
		System.setSecurityManager(new RMISecurityManager());
		// get the remote object from the registry
		
		try {
			
			System.out.println("Security Manager loaded");
			String url = "//localhost/SERVER";
			Server remoteObject = (Server) Naming.lookup(url);
			System.out.println("Got remote object");
			//System.out.println(" 1 + 2 = " + remoteObject.sum(1, 2));
			
		} catch (RemoteException exc) {
			
			System.out.println("Error in lookup: " + exc.toString());
			
		} catch (java.net.MalformedURLException exc) {
			
			System.out.println("Malformed URL: " + exc.toString());
			
		} catch (java.rmi.NotBoundException exc) {
			
			System.out.println("NotBound: " + exc.toString());
			
		}
	}
}
