package lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class Store {
	
	  public static void store_result(Map<String,Integer> oldmap,int wordline,int wordcount,int characterscount){            
		   ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());  
		          Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){    
		                 public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
		                    return o2.getValue() - o1.getValue();  //½µÐò  
                }  
		         }); 
		          try {
			      //System.out.println(wordcount);
		          File file = new File("result.txt");
		          BufferedWriter bi = new BufferedWriter(new FileWriter(file));
		          bi.write("characters: "+(characterscount+wordline-1)+"\r\n");
		          bi.write("words: "+wordcount+"\r\n");
		          bi.write("lines: "+wordline+"\r\n");
		            int k=0;
	            for(int i = 0; i<list.size(); i++){ 
	                if(k>=10)break;
	                 if(list.get(i).getKey().length()>3)
	               {
	               bi.write("<"+list.get(i).getKey()+">"+ ": " +list.get(i).getValue()+"\r\n");  
	                k++;
		          }    
		       
		            }
	            bi.close();
	}catch (Exception e) {
		e.printStackTrace();
	}		      
		     
}
}
