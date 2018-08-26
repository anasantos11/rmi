package Server;
import java.rmi.Naming;

public class DictionaryWordServer {
	public DictionaryWordServer() {
		try {
			Naming.rebind("//localhost/DictionaryWordService", new DictionaryWordServant());
			System.out.println("Servidor Dictionary em execu��o.");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) {
		new DictionaryWordServer();

	}
}
