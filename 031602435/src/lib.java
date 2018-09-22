

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 031602435 xyq
 * @version 1
 * 
 */
public class lib {
	
public static String encoding = "UTF-8";

	/**�ַ�����������
	 * 
	 * @param file
	 * @return charnum 
	 * @throws Exception
	 * @throws FileNotFoundException
	 */
	public int charCounter(File file) throws Exception,FileNotFoundException {
		InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), encoding);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		int charnum = 0;
		String str = null;
		
		while ((str = bufferedReader.readLine()) != null) {
			//String s = bufferedReader.readLine();
			charnum += str.length();
		}
		//System.out.println("char:"+charnum);
		inputStreamReader.close();
		return charnum;
	}
	
	/**����������
	 * 
	 * @param file
	 * @return linenum 
	 * @throws Exception
	 * @throws FileNotFoundException
	 */
	
	public int lineCounter(File file) throws Exception, FileNotFoundException {
		InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), encoding);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		int linenum = 0;
		String str = null;
		
		while ((str = bufferedReader.readLine()) != null) {
			//String s = bufferedReader.readLine();
			if (!str.isEmpty()) {
				linenum ++;
			}
		}
		//System.out.println("line:"+linenum);
		inputStreamReader.close();
		return linenum;
	}
	
	/**��������������
	 * 
	 * @param file
	 * @return wordsnum 
	 * @throws Exception
	 * @throws FileNotFoundException
	 */
	
	public int wordsCounter(File file) throws Exception, FileNotFoundException {
		InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), encoding);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		int wordsnum = 0;
		String str = null;
		Map<String, Integer> map = new HashMap<String,Integer>();
		
		while ((str = bufferedReader.readLine()) != null) {
			//String s = bufferedReader.readLine();
			String splited[] = str.split(",|\\.| |\\?|\\!|\\'");
			for (int i = 0; i < splited.length; i++) {
				if (splited[i].length() >= 4 ) {
					String temp = splited[i].substring(0, 4);
					temp = temp.replaceAll("[^a-zA-Z]", "");
					if (temp.length() >= 4) {
						 if (map.containsKey(splited[i].toLowerCase())) {
							 map.put(splited[i].toLowerCase(), map.get(splited[i].toLowerCase())+1);
						 }
						 else {
							 map.put(splited[i].toLowerCase(), 1);
						}
					 }
				}
			}
		}
		
		wordsnum = map.size();
		
		//System.out.println("words:"+wordsnum);
		inputStreamReader.close();
		return wordsnum;
	}
	

	/**����ͳ����
	 * 
	 * @param file
	 * @return List<Map.Entry<String, Integer>> 
	 * @throws Exception
	 * @throws FileNotFoundException
	 */
	
	public List<Map.Entry<String, Integer>> wordsNumCounter(File file) throws Exception, FileNotFoundException {
		InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), encoding);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			
		Map<String, Integer> map = new HashMap<String,Integer>();
		String str = null;
		
		while ((str = bufferedReader.readLine()) != null) {
			//String s = bufferedReader.readLine();
			String splited[] = str.split(",|\\.| |\\?|\\!|\\'");
			for (int i = 0; i < splited.length; i++) {
				if (splited[i].length() >= 4 ) {
					String temp = splited[i].substring(0, 4);
					temp = temp.replaceAll("[^a-zA-Z]", "");
					 if (temp.length() >= 4) {
						 if (map.containsKey(splited[i].toLowerCase())) {
							 map.put(splited[i].toLowerCase(), map.get(splited[i].toLowerCase())+1);
						 }
						 else {
							 map.put(splited[i].toLowerCase(), 1);
						}
					 }
				}
			}
		}
		
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        // ͨ���Ƚ�����ʵ������
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // ��������
                return o2.getValue().compareTo(o1.getValue());
            }
        });
		inputStreamReader.close();
		return list;
	}
	
	/**д���ļ�
	 * 
	 * @param charcount
	 * @param linecount
	 * @param wordscount
	 * @param list
	 * @return
	 * @throws Exception
	 * 
	 */
	
	public void writeFile(int charcount,int linecount,int wordscount,List<Map.Entry<String, Integer>> list) throws Exception {
		
		StringBuilder result = new StringBuilder("");
		
		result.append("characters:");
		result.append(charcount+linecount-1);
		result.append("\r\n");
		result.append("words:");
		result.append(wordscount);
		result.append("\r\n");
		result.append("lines:");
		result.append(linecount);
		result.append("\r\n");
		
		int count = 0;
		
		for(Map.Entry<String, Integer> map : list) {
			count++;
			result.append("<" + map.getKey() + ">:" + map.getValue());
			
			if (count>9) {
				break;
			}
			
			result.append("\r\n");
		}
		
		
		//String pathname = "D:\\java_project\\wordcount_0910\\src\\result.txt";
		String pathname = System.getProperty("user.dir")+"\\result.txt";
		//System.out.println("out.locate:"+pathname);
		File file = new File(pathname);
		if (!file.exists()) {
			System.out.println("not found result.txt");
			System.out.println("create result.txt");
			 file.createNewFile();
		}
		
		FileWriter filewriter = new FileWriter(file.getAbsoluteFile());
		//System.out.println("absolutely path:"+file.getAbsolutePath());
		BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
		
		bufferedWriter.write(result.toString());
		
		bufferedWriter.close();
	}
	
}
