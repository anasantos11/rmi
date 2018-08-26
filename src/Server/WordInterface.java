package Server;

import java.io.IOException;

/**
 * Interface para m�todos que ser�o implementados pela classe word
 * 
 * @author Ana Paula
 *
 */
public interface WordInterface {

	public String getWord();

	public byte[] getByteArray() throws IOException;

	public void setByteArray(byte[] b) throws IOException;
}
