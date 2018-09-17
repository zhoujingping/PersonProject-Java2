import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class lib {

	/*
	 * ���ļ��л�ȡ����
	 */
	public static int lineCount(File file, String code) {
		BufferedReader readFile = null;
		InputStream is = null;
		InputStreamReader isr = null;
		int lineCount = 0;
		String line = null;
		try {
			is = new FileInputStream(file);
			isr = new InputStreamReader(is, code);
			readFile = new BufferedReader(isr);
			while ((line = readFile.readLine()) != null) {
				if (!line.trim().equals(""))// ȥ���հ��ַ�
					lineCount++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			try {
				if (readFile != null) {
					readFile.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lineCount;
	}

	/*
	 * ���ļ���ȡ���ʸ���
	 */
	public static int wordCount(File file, String code) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader readFile = null;
		String line = null;
		int countword = 0;
		try {
			is = new FileInputStream(file);
			isr = new InputStreamReader(is, code);
			readFile = new BufferedReader(isr);
			while ((line = readFile.readLine()) != null) {
				String[] words = line.split("[^a-zA-Z0-9]+");
				for (String word : words) {
					if (word.matches("[a-zA-Z]{4}[a-zA-Z0-9]*")) {
						countword++;
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				readFile.close();
				isr.close();
				is.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return countword;
	}

	/*
	 * ���ļ��ж�ȡ�ַ�����
	 */
	public static int charCount(File file, String code) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader readFile = null;
		int countchar = 0;
		try {
			is = new FileInputStream(file);
			isr = new InputStreamReader(is, code);
			readFile = new BufferedReader(isr);
			while ((readFile.read()) != -1) {
				countchar++;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				readFile.close();
				isr.close();
				is.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return countchar;
	}

	/*
	 * ���ļ��ж�ȡ����Ƶ��ǰʮ
	 */
	public static List<Entry<String, Integer>> wordCountTopTen(File file, String code) {
		Map<String, Integer> TheNumberOfWord = new HashMap<String, Integer>();// ÿ�����ʵĸ���
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader readFile = null;
		String line = null;
		try {
			is = new FileInputStream(file);
			isr = new InputStreamReader(is, code);
			readFile = new BufferedReader(isr);
			while ((line = readFile.readLine()) != null) {
				String[] words = line.split("[^a-zA-Z0-9]+");// �Է�Ӣ���ַ�������Ϊ�ָ���
				for (String word : words) {
					if (word.matches("[a-zA-Z]{4}[a-zA-Z0-9]*")) {// �ж��Ƿ�����ǰ�ĸ��ַ�Ϊ���ʵ�����
						word = word.toLowerCase();
						if (TheNumberOfWord.containsKey(word)) {
							TheNumberOfWord.put(word, TheNumberOfWord.get(word) + 1);
						} else {
							TheNumberOfWord.put(word, 1);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				readFile.close();
				isr.close();
				is.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(TheNumberOfWord.entrySet());
		Comparator<Entry<String, Integer>> com = new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
				// TODO Auto-generated method stub
				if (arg0.getValue() != arg1.getValue()) {
					return (arg1.getValue().compareTo(arg0.getValue()));
				} else {
					return (arg0.getKey().compareTo(arg1.getKey()));
				}
			}
		};
		Collections.sort(list, com);
		int listSize = list.size();
		if (10 > listSize) {
			return list.subList(0, listSize);
		} else {
			return list.subList(0, 10);
		}
	}
}
