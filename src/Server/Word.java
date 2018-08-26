import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Word implements WordDao{
	
	private String word;
	private String meaning;

	@Override
	public byte[] getByteArray() throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(output);
		data.writeUTF(word);
		data.writeUTF(meaning);
		return output.toByteArray();
	}

	@Override
	public void setByteArray(byte[] b) throws IOException {
		ByteArrayInputStream input = new ByteArrayInputStream(b);
		DataInputStream data = new DataInputStream(input);
		word = data.readUTF();
		meaning = data.readUTF();
		
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

}
