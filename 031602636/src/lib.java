import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class lib implements WordCount{

	@Override
	public int linesCount(String filepath) throws IOException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		String line = null;
		int countword = 0;
		try {
			FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				String[] words = line.split("[^a-zA-Z0-9]+");
				for (String word : words) {
					word.toLowerCase();
					System.out.println(word);
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
	
}
