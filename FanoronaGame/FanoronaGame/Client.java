package FanoronaGame;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

import FanoronaGame.Commands;

public class Client {

	//Establishing connection with server side
    Socket c_socket = null;
    InputStream c_sockInput;
    OutputStream c_sockOutput;
    
    public Client() {
        try {
            c_socket = new Socket("SERVER", 4001);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: SERVER.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to : SERVER ");
            System.exit(1);
        }
		
		//Sending and recieving data
        try {
            c_sockInput = c_socket.getInputStream();
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the input to : SERVER ");
            System.exit(1);
        }
        try {
            c_sockOutput = c_socket.getOutputStream();
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the output to : SERVER ");
            System.exit(1);
        }
        
    }
	//MEGAN WROTE ABOVE CODE.. IT WAS THE ONLINE HELP THAT WAS POSTED

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
