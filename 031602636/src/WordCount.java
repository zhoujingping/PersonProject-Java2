import java.io.IOException;

public interface WordCount {
	
	/**
	 * ��������
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	int linesCount(String filepath) throws IOException;
	
	/**
	 * ���غϷ�������
	 * @param filepath
	 * @return
	 * @throws IOException
	 */
	int wordsCount(String filepath) throws IOException;
	
	/**
	 * �����ַ���
	 * @param filepath
	 * @return
	 * @throws IOException
	 */
	int charsCount(String filepath) throws IOException;
	
	/**
	 * ��Ƶǰʮ�ĵ���
	 * @param filepath
	 * @throws IOException
	 */
	void wordDetail(String filepath) throws IOException;
}
