package Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import Interface.DictionaryInterface;

/**
 * Classe que implementará os métodos que poderão ser acessados remotamente
 * pelos clientes
 * 
 * @author Ana Paula
 *
 */
public class DictionaryServant extends UnicastRemoteObject implements DictionaryInterface {

	private static final long serialVersionUID = 1L;
	private String db;
	private RandomAccessFile file;

	public DictionaryServant() throws java.rmi.RemoteException {
		super();
		this.db = "bd.txt";
	}

	/**
	 * M�todo que receber� uma palavra e ir� fazer a busca sequencial no arquivo com
	 * o intuito de encontrar o seu significado encontrando o resultado ela
	 * retornar� o par ordenado nome da palavra e significado, caso n�o encontre
	 * informar� retornar� exce��o ao cliente. Este m�todo pode ser acessado
	 * remotamente.
	 */
	@Override
	public String search(String word) throws RemoteException {
		try {
			this.file = new RandomAccessFile(db, "r");
			while (this.file.getFilePointer() < this.file.length()) {
				byte[] b = new byte[file.readInt()];
				this.file.read(b);
				Word w = new Word();
				w.setByteArray(b);
				if (w.getWord().equals(word.toLowerCase().trim())) {
					this.file.close();
					return w.toString();
				}

			}
			this.file.close();
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
		throw new RemoteException("Palavra n�o encontrada no dicion�rio");
	}

	/**
	 * M�todo que receber� uma palavra e significado, ir� acessar o arquivo e pular
	 * para a �ltima posi��o para poder inserir uma nova palavra no dicion�rio. Este
	 * m�todo pode ser acessado remotamente.
	 */
	@Override
	public void add(String word, String meaning) throws RemoteException {
		Word w = new Word(word.toLowerCase().trim(), meaning.toLowerCase().trim());
		try {
			this.file = new RandomAccessFile(db, "rw");
			this.file.seek(this.file.length());
			this.file.writeInt(w.getByteArray().length);
			this.file.write(w.getByteArray());
			this.file.close();
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * M�todo que receber� uma palavra e ir� fazer uma busca no arquivo pela
	 * palavra, encontrando ir� delet�-la. Para deletar foi utilizado a estrat�gia
	 * de direcionar as demais palavras para um arquivo tempor�rio e depois
	 * substituir os dados do arquivo atual pelo tempor�rio, garantindo que a
	 * palavra removida n�o estar� mais no arquivo. Este m�todo pode ser acessado
	 * remotamente.
	 */
	@SuppressWarnings("resource")
	@Override
	public void remove(String word) throws RemoteException {
		List<Word> words = new ArrayList<Word>();
		boolean wordExists = false;
		try {
			this.file = new RandomAccessFile(db, "r");

			while (this.file.getFilePointer() < this.file.length()) {
				byte[] b = new byte[this.file.readInt()];
				this.file.read(b);
				Word w = new Word();
				w.setByteArray(b);
				if (w.getWord().equals(word.toLowerCase().trim())) {
					wordExists = true;
				} else {
					words.add(w);
				}
			}
			file.close();
			if (wordExists) {
				File tmpFile = File.createTempFile("fileTmp", ".tmp");
				RandomAccessFile temp = new RandomAccessFile(tmpFile, "rw");
				long seek;
				for (Word w : words) {
					seek = temp.length();
					temp.seek(seek);
					temp.writeInt(w.getByteArray().length);
					temp.write(w.getByteArray());
				}
				FileChannel src = new FileInputStream(tmpFile).getChannel();
				FileChannel dest = new FileOutputStream(db, false).getChannel();
				dest.transferFrom(src, 0, src.size());

				temp.close();
				src.close();
				dest.close();
				tmpFile.deleteOnExit();
			} else {
				throw new RemoteException("N�o foi poss�vel excluir a palavra pois n�o foi encontrada no dicion�rio");
			}

		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}

	}

	/**
	 * M�todo que ir� percorrer o arquivp e retornar� todos as palavras existente no
	 * dicion�rio. Este m�todo pode ser acessado remotamente.
	 */
	public String listAll() throws RemoteException {
		List<Word> words = new ArrayList<Word>();
		try {
			this.file = new RandomAccessFile(db, "r");
			while (this.file.getFilePointer() < this.file.length()) {
				byte[] b = new byte[this.file.readInt()];
				this.file.read(b);
				Word w = new Word();
				w.setByteArray(b);
				words.add(w);
			}
			file.close();
			return words.toString();
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
	}

}
