import com.eventide.wordCount.service.CharCounter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharCounterTest {

    @Test
    //测试正常字符数
    public void charCounterTest1() {
        long charNum = CharCounter.countChar("normalCharTest.txt");
        assertEquals(3, charNum);
    }

    @Test
    //测试换行符
    public void charCounterTest2() {
        long charNum = CharCounter.countChar("newLineCharTest.txt");
        assertEquals(11, charNum);
    }

    @Test
    //测试空格
    public void charCounterTest3() {
        long charNum = CharCounter.countChar("spaceCharTest.txt");
        assertEquals(5, charNum);
    }
}
