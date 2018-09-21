


import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author 031602435 xyq
 * @version 1
 * 
 */

public class Main {
	public static void main(String[] args) throws Exception {
		lib l = new lib();
		
    	FileUtil fileUtil = new FileUtil();
    	//String path = "D:\\java_project\\wordcount_0910\\src\\wordcount_0910\\input.txt";
    	String path = args[0];
    	File file = fileUtil.getFile(path);
    	int charcount = l.charCounter(file);
    	int wordscount = l.wordsCounter(file);
    	int linecount = l.lineCounter(file);
    	
    	List<Map.Entry<String, Integer>> list = l.wordsNumCounter(file);
    	
    	l.writeFile(charcount, linecount, wordscount, list);
    	
    	System.out.println("finished");
    	
    	
    }
}
