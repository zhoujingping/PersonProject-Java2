import com.eventide.wordCount.service.WordCounter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordCounterTest {
    @Test
    //测试正常单词
    public void wordCounterTest1() {
        long wordNum = WordCounter.countWord("normalWordTest.txt");
        assertEquals(4, wordNum);
    }

    @Test
    //测试特殊单词
    public void wordCounterTest2() {
        long wordNum = WordCounter.countWord("specialWordTest.txt");
        assertEquals(3, wordNum);
    }

    @Test
    //测试单词大小写
    public void wordCounterTest3() {
        long wordNum = WordCounter.countWord("upLowWordTest.txt");
        assertEquals(4, wordNum);
    }
}
