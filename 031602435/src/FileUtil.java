

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author 031602435 xyq
 * @version 1
 * 
 */
public class FileUtil {
	/**
	 * @param path
	 * @return file 
	 */
	public File getFile(String  path) {
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("file not found");
		}
		System.out.println("locate:"+path);
		return file;
	}
}
