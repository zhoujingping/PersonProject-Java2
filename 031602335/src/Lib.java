



import java.io.*;
import java.util.*;

public class Lib {

	// 计算字符数

	public static int countChars(File f) {

		int characters = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			while (br.read() != -1) {
				characters++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return characters;

	}

	// 计算行数

	public static int countLines(File f) {

		int lines = 0;
		String str = null;
		BufferedReader br = null;
		ArrayList<String> strArray = new ArrayList<String>();// 存储每一行的字符串

		try {
			br = new BufferedReader(new FileReader(f));

			while ((str = br.readLine()) != null) {

				if (!str.trim().isEmpty()) {
					lines++;
				} // 计算行数
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		

		return lines;

	}

	// 计算单词数

	public static int countWords(File f) {

		int words = 0;
		String str = null;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(f));
			while ((str = br.readLine()) != null) {
				String[] wordArray = str.split("\\s*[^a-zA-Z0-9]+");
				for (String word : wordArray) {
					if (word.matches("[a-zA-Z]{4,}[a-zA-Z0-9]*")) {
						words++;

					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return words;

	}

	// 计算出现频率前10的单词 若相同频率按照字典序

	public static ArrayList<Map.Entry<String, Integer>> countTop10(File f) {

		BufferedReader br = null;
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();// 用于储存单词及其个数
		ArrayList<Map.Entry<String, Integer>> list; // 用于储存排序好的键值对
		String str = null;
		try {
			br = new BufferedReader(new FileReader(f));
			while ((str = br.readLine()) != null) {
				String[] wordArray = str.split("\\s*[^a-zA-Z0-9]+");
				for (String word : wordArray) {
					if (word.matches("[a-zA-Z]{4,}[a-zA-Z0-9]*")) {
						word = word.toLowerCase();
						if (hashMap.containsKey(word)) {
							hashMap.put(word, hashMap.get(word) + 1);// 计算单词出现次数
						} else {
							hashMap.put(word, 1);
						}
					}
				}

			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());

		// 排序

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
				int re = e2.getValue()-e1.getValue();
				return (re == 0) ? e1.getKey().compareTo(e2.getKey()) : re;

			}

		});
		return list;

	}
}
