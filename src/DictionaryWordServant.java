import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DictionaryServant extends UnicastRemoteObject implements Dictionary {

	private static final long serialVersionUID = 1L;
	
	public DictionaryServant() throws java.rmi.RemoteException {
		super();
	}

	@Override
	public String query(String word) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(String word, String meaning) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String word) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}