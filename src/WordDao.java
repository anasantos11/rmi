import java.io.IOException;

public interface WordDao {

	public void setWord(String word);

	public void setMeaning(String meaning);

	public String getWord();

	public String getMeaning();

	public byte[] getByteArray() throws IOException;

	public void setByteArray(byte[] b) throws IOException;
}
