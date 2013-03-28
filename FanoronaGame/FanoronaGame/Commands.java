package FanoronaGame;
import java.rmi.*;

public interface Commands extends Remote {
	String sendCommand() throws RemoteException;
}