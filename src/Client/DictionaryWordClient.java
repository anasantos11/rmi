package Client;

import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import Interface.DictionaryInterface;

public class DictionaryWordClient {
	private static DictionaryInterface look_up;
	private static String word = "";
	private static String meaning = "";

	public static void main(String[] args) throws Exception {
		look_up = (DictionaryInterface) Naming.lookup("//localhost/DictionaryWordService");
		if (args.length == 0) {
			menu(-1);
		} else {
			// String cmd = args[0];
			menu(-1);
		}
	}

	public static int menu(int option) {
		String auxOption = JOptionPane.showInputDialog(null,
				"Escolha uma das opcoes abaixo: \n  0  -  Encerrar programa \n\n  CONSULTAS: \n"
						+ "\n  1  -  Adicionar palavra" + "\n  2  -  Consultar palavra" + "\n  3  -  Remover palavra"
						+ "\n  4  -  Consultar todas palavras");
		if (auxOption == null) {
			option = 0;
		} else if (auxOption.equals("")) {
			option = 556454532;
		} else {
			try {
				option = Integer.parseInt(auxOption);
			} catch (NumberFormatException e) {
				option = 556454532;
			}
		}

		switch (option) {
		case 0: // Encerrar programa
			JOptionPane.showMessageDialog(null, "Encerrando programa...");
			return 0;
		case 1: // Adicionar palavra
			word = JOptionPane.showInputDialog(null, "Digite a palavra");
			meaning = JOptionPane.showInputDialog(null, "Digite o significado");
			try {
				look_up.add(word, meaning);
			} catch (RemoteException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			menu(-1);
			break;
		case 2: // Consultar palavra
			word = JOptionPane.showInputDialog(null, "Digite a palavra que deseja pesquisar");
			try {
				JOptionPane.showMessageDialog(null, look_up.search(word));
			} catch (RemoteException e) {
				JOptionPane.showMessageDialog(null, e.getCause().getMessage());
			}
			menu(-1);
			break;
		case 3: // Remover palavra
			word = JOptionPane.showInputDialog(null, "Digite a palavra que deseja remover");
			try {
				look_up.remove(word);
			} catch (RemoteException e) {
				JOptionPane.showMessageDialog(null, e.getCause().getMessage());
			}
			menu(-1);
			break;
		case 4: // Consultar todas palavras
			try {
				JOptionPane.showMessageDialog(null, look_up.listAll());
			} catch (RemoteException e) {
				JOptionPane.showMessageDialog(null, e.getCause().getMessage());
			}
			menu(-1);
			break;
		default:
			JOptionPane.showMessageDialog(null, "ATENCAO - Opcao invalida, verifique o menu.");
			menu(-1);
			break;
		}
		return 0;
	}

}
