import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class lib implements WordCount{

	@Override
	public int linesCount(String filepath) throws IOException {
		int linecount = 0;
		try {
			FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() != 0 && !line.matches("\\s+")) {
                    linecount++;
                }
            }
            bufferedReader.close();
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    }
		return linecount;
	}

	@Override
	public int wordsCount(String filepath) throws IOException {
		String line = null;
		int countword = 0;
		try {
			FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				String[] words = line.split("[^a-zA-Z0-9]+");
				for (String word : words) {
					word = word.toLowerCase();
					if (word.matches("[a-zA-Z]{4}[a-zA-Z0-9]*") ) {
						countword++;
					}
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return countword;
	}

	@Override
	public int charsCount(String filepath) throws IOException {
		int value;
		int charcount = 0;
		try {
			FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((value = bufferedReader.read()) != -1) {
			//	if (value >= 0 && value<=255) {
					charcount++;
			//	}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return charcount;
	}

	@Override
	public void wordDetail(String filepath) throws IOException {
		String buffer;
		Map<String,Integer> map=new HashMap<String,Integer>();
		try {
			FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((buffer=bufferedReader.readLine())!=null){
				String[] words = buffer.split("[^a-zA-Z0-9]+");
				for (String word : words) {
					word = word.toLowerCase();
					if (word.matches("[a-zA-Z]{4}[a-zA-Z0-9]*") ) {
						if(map.containsKey(word)) 
							map.put(word, map.get(word)+1);
						else map.put(word, 1);
					}
				}
			}
			Set<WordEntity> set=new TreeSet<WordEntity>();
			for(String s:map.keySet()){
				WordEntity wordEntry=new WordEntity(s,map.get(s));
				set.add(wordEntry);     
			}
			Iterator<WordEntity>  ite=set.iterator();
			int count=0;
			while(ite.hasNext()){
				if(count>=10) 
					break;
				System.out.println(ite.next());
				count++;
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
