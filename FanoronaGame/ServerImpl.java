package FanoronaGame;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class ServerImpl extends UnicastRemoteObject implements Server {

	ServerImpl() throws RemoteException {
		super();
	}

	//public int sum(int a, int b) throws RemoteException {
		//return a + b;
	//}

	public static void main(String args[]) {
		try {
			System.setSecurityManager(new RMISecurityManager());
			// set the security manager

			// create a local instance of the object
			ServerImpl Server = new ServerImpl();

			// put the local instance in the registry
			Naming.rebind("SERVER", Server);

			System.out.println("Server waiting.....");
		} catch (java.net.MalformedURLException me) {
			System.out.println("Malformed URL: " + me.toString());
		} catch (RemoteException re) {
			System.out.println("Remote exception: " + re.toString());
		}
	}
}