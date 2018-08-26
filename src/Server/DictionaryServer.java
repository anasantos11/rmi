package Server;

import java.rmi.Naming;

/**
 * Classe que devera ser executada para conexão do servidor remoto Ela atribui
 * um determinado nome para o objeto remoto, atraves deste nome os clientes
 * poderão encontrar o servidor
 * 
 * @author Ana Paula
 *
 */
public class DictionaryServer {
	public DictionaryServer() {
		try {
			Naming.rebind("//localhost/DictionaryWordService", new DictionaryServant());
			System.out.println("Servidor Dictionary em execução.");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) {
		new DictionaryServer();

	}
}
