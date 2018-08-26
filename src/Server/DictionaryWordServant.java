package Server;

import java.io.RandomAccessFile;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import Interface.DictionaryWord;

public class DictionaryWordServant extends UnicastRemoteObject implements DictionaryWord {

	private static final long serialVersionUID = 1L;
	private String db;
	private RandomAccessFile file;

	public DictionaryWordServant() throws java.rmi.RemoteException {
		super();
		this.db = "bd.txt";
	}

	@Override
	public String query(String word) throws RemoteException {
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
		throw new RemoteException("Palavra não encontrada no dicionário");
	}

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

	@Override
	public void remove(String word) throws RemoteException {
		// TODO Auto-generated method stub

	}

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
