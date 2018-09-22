import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lib.Store;
public class Main{ 
	  public static void main(String[] args) throws Exception {  
	    	try {
	    		File file = new File(args[0]);
	        	String pathname = file.getPath();
//	    	Scanner scanner=new Scanner(System.in);
//	    	String pathname=scanner.nextLine();
	    	Reader myReader = new FileReader(pathname);
	    	Reader myBufferedReader = new BufferedReader(myReader);  	 	
	    	//对文本处理
	    	
	    	CharArrayWriter  tempStream = new CharArrayWriter();
	    	int i = -1;
	    	do {
	    	if(i!=-1)
	    	tempStream.write(i);
	    	i = myBufferedReader.read();
	    	if(i >= 65 && i <= 90){
	    	i += 32;
	    	}
	    	}while(i != -1);
	    	myBufferedReader.close();
	    	Writer myWriter = new FileWriter(pathname);
	    	tempStream.writeTo(myWriter);
	    	tempStream.flush();
	    	tempStream.close();
	    	myWriter.close();
	    	
	    	 BufferedReader br = new BufferedReader(new FileReader(pathname));  
	    	  
	       int characterscount=0;  
	       int wordline = 0;
	       int wordcount = 0;
	        List<String> lists = new ArrayList<String>();  //存储过滤后单词的列表  
	        String readLine = null;
			while((readLine = br.readLine()) != null){  
				wordline++;
	            String[] wordsArr1 = readLine.split("[^a-zA-Z0-9]");  //过滤出只含有字母的单词
	            characterscount+=readLine.length();
	            for (String word : wordsArr1) {  
	                if(word.length() != 0){  //去除长度为0的行
	                	
	                	/*while(!(word.charAt(0)>=97&&word.charAt(0)<=122))
	                	{
	                		word = word.substring(1, word.length());
	                	}*/
	                	if((word.length()>=4)&&(Character.isLetter(word.charAt(0))&&Character.isLetter(word.charAt(1))&&Character.isLetter(word.charAt(2))&&Character.isLetter(word.charAt(3)))) 	
	                	{ wordcount++;
	                    lists.add(word);  
	                }  
	            }  
	        }
			}
			
		
	          
	        br.close();  
	          
	        Map<String, Integer> wordsCount = new TreeMap<String,Integer>();  
	          
	        //单词的词频统计  
	        for (String li : lists) {  
	            if(wordsCount.get(li) != null){ 
	                wordsCount.put(li,wordsCount.get(li) + 1);  
	            }else{  
	                wordsCount.put(li,1);  
	            }  
	  
	        }  
	      Store st= new Store();
	      st.store_result(wordsCount, wordline, wordcount, characterscount);      
	  }catch (Exception e) {
			e.printStackTrace();
		}		      
} 
}