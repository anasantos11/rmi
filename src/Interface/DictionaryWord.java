package Interface;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DictionaryWord extends Remote {

	public String query(String word) throws RemoteException;
	public void add(String word, String meaning) throws RemoteException;
	public void remove(String word) throws RemoteException;
	public String listAll()throws RemoteException;
}
