package Server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Classe que os dados que ser�o armazenados pelo dicion�rio - palavra e
 * significado
 * 
 * @author Ana Paula
 *
 */
public class Word implements WordInterface {

	private String word;
	private String meaning;
	private ByteArrayOutputStream output;
	private DataOutputStream dataOutput;
	private ByteArrayInputStream input;
	private DataInputStream dataInput;

	public Word() {
		this.word = "";
		this.meaning = "";
	}

	public Word(String word, String meaning) {
		this.word = word;
		this.meaning = meaning;
	}

	/**
	 * M�todo que retornas a inst�ncia da classe atual em um array de byte para ser
	 * usada no armazenamento do arquivo
	 */
	@Override
	public byte[] getByteArray() throws IOException {
		this.output = new ByteArrayOutputStream();
		this.dataOutput = new DataOutputStream(this.output);
		this.dataOutput.writeUTF(this.word);
		this.dataOutput.writeUTF(this.meaning);
		return this.output.toByteArray();
	}

	/**
	 * M�todo que recebe um array de byte e atualiza os atributos da inst�ncia da
	 * classe com os valores nele contido
	 */
	@Override
	public void setByteArray(byte[] b) throws IOException {
		this.input = new ByteArrayInputStream(b);
		this.dataInput = new DataInputStream(this.input);
		this.word = this.dataInput.readUTF();
		this.meaning = this.dataInput.readUTF();
	}

	@Override
	public String getWord() {
		return this.word;
	}

	@Override
	public String toString() {
		return "Significado de " + word + " : " + meaning;
	}

}
