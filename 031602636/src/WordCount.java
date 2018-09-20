import java.io.IOException;

public interface WordCount {
	
	/**
	 * 返回行数
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	int linesCount(String filepath) throws IOException;
	
	/**
	 * 返回合法单词数
	 * @param filepath
	 * @return
	 * @throws IOException
	 */
	int wordsCount(String filepath) throws IOException;
}
