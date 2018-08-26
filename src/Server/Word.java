package Server;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Word implements WordDao {

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

	@Override
	public byte[] getByteArray() throws IOException {
		this.output = new ByteArrayOutputStream();
		this.dataOutput = new DataOutputStream(this.output);
		this.dataOutput.writeUTF(this.word);
		this.dataOutput.writeUTF(this.meaning);
		return this.output.toByteArray();
	}

	@Override
	public void setByteArray(byte[] b) throws IOException {
		this.input = new ByteArrayInputStream(b);
		this.dataInput= new DataInputStream(this.input);
		this.word = this.dataInput.readUTF();
		this.meaning = this.dataInput.readUTF();
	}

	@Override
	public void setWord(String word) {
		this.word = word;

	}

	@Override
	public void setMeaning(String meaning) {
		this.meaning = meaning;

	}

	@Override
	public String getWord() {
		return this.word;
	}

	@Override
	public String getMeaning() {
		return this.meaning;
	}

	@Override
	public String toString() {
		return "Significado de " + word + " : " + meaning;
	}
	
	

}
