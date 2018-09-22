import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = null;
		if (0 == args.length) {
			Scanner scan = new Scanner(System.in);
			fileName = scan.nextLine();
			scan.close();
		} else {
			fileName = args[0];
		}
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println(file + "文件不存在");
			System.exit(0);
		}
		if (!file.isFile()) {
			System.out.println("文件错误");
			System.exit(0);
		}
		if (fileName != null) {
			String code = witchCode(file);// code是文本的编码
			int charCount = lib.charCount(file, code);
			int lineCount = lib.lineCount(file, code);
			int wordCount = lib.wordCount(file, code);
			List<Entry<String, Integer>> wordCountTopTen = lib.wordCountTopTen(file, code);
			writeInfo(charCount, wordCount, lineCount, wordCountTopTen);
		}
	}

	/*
	 * 判断文档字符编码
	 */
	public static String witchCode(File file) {
		byte[] head = new byte[3];
		String code = "gb2312";
		try {
			InputStream inputStream = new FileInputStream(file);
			inputStream.read(head);
			if (head[0] == -1 && head[1] == -2) {
				code = "UTF-16";
			} else if (head[0] == -2 && head[1] == -1) {
				code = "Unicode";
			} else if (head[0] == -17 && head[1] == -69 && head[2] == -65) {
				code = "UTF-8";
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return code;
	}

	/*
	 * 信息输出和控制台打印
	 */
	public static void writeInfo(int charCount, int wordCount, int lineCount,
			List<Entry<String, Integer>> wordCountTopTen) {
		BufferedWriter writeFile = null;
		File file = new File("result.txt");
		try {
			if (file.exists() && file.isFile()) {
				file.delete();
			}
			file.createNewFile();

			writeFile = new BufferedWriter(new FileWriter(file, true));
			// 写入字符个数
			writeFile.write("characters: " + String.valueOf(charCount));
			System.out.println("characters: " + String.valueOf(charCount));

			writeFile.newLine();
			// 写入单词个数
			writeFile.write("words: " + String.valueOf(wordCount));
			System.out.println("words: " + String.valueOf(wordCount));

			writeFile.newLine();
			// 写入行数
			writeFile.write("lines: " + String.valueOf(lineCount));
			System.out.println("lines: " + String.valueOf(lineCount));

			for (Map.Entry<String, Integer> entry : wordCountTopTen) {
				writeFile.newLine();
				writeFile.write("<" + entry.getKey() + ">: " + entry.getValue());
				System.out.println("<" + entry.getKey() + ">: " + entry.getValue());
			}
			writeFile.flush();
			writeFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
