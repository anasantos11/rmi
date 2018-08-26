package Server;

import java.rmi.Naming;

/**
 * Classe que devera ser executada para conex�o do servidor remoto Ela atribui
 * um determinado nome para o objeto remoto, atraves deste nome os clientes
 * poder�o encontrar o servidor
 * 
 * @author Ana Paula
 *
 */
public class DictionaryServer {
	public DictionaryServer() {
		try {
			Naming.rebind("//localhost/DictionaryWordService", new DictionaryServant());
			System.out.println("Servidor Dictionary em execu��o.");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) {
		new DictionaryServer();

	}
}
