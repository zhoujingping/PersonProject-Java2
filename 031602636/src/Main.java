import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		String path = scanner.nextLine();
		File file = new File(path);
		File file1 = new File(".\\result.txt");
		if (file1.exists() && file.isFile()) {
			file1.delete();
		}
		if (!file.exists()) {
			System.out.println(file + "文件没有找到");
			System.exit(0);
		}else if (!file.isFile()) {
			System.out.println("文件读异常");
			System.exit(0);
		}else {
			WordCount count = new lib();
			int linescount = count.linesCount(path);
			int wordscount = count.wordsCount(path);
			int charscount = count.charsCount(path);
			writeInTxt.writeTxt("characters: " + charscount);
			writeInTxt.writeTxt("words: " + wordscount);
			writeInTxt.writeTxt("lines: " + linescount);
			count.wordDetail(path);
		//	System.out.println(linescount);
		//	System.out.println(wordscount);
		}
		scanner.close();
	}
}
