
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class writeInTxt {
	public static void writeTxt(String str) {
		FileWriter fileWriter = null;
		try {
			//����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ�
			File file = new File(".\\result.txt");
			file.createNewFile();
			fileWriter = new FileWriter(file,true);
		}catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pWriter = new PrintWriter(fileWriter);
		pWriter.println(str);
		pWriter.flush();
		try {
			fileWriter.flush();
			pWriter.close();
			fileWriter.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
