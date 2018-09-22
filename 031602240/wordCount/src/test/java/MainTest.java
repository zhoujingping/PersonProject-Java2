import com.eventide.wordCount.service.CharCounter;
import com.eventide.wordCount.service.LineCounter;
import com.eventide.wordCount.service.WordCounter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    //测试空文件
    public void mainTest1() {
        long charNum = CharCounter.countChar("mainTest.txt");
        long wordNum = WordCounter.countWord("mainTest.txt");
        long lineNum = LineCounter.countLine("mainTest.txt");
        assertEquals(0, charNum);
        assertEquals(0, wordNum);
        assertEquals(0, lineNum);
    }
}
