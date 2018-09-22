import com.eventide.wordCount.service.LineCounter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineCounterTest {
    @Test
    //测试正常行数
    public void lineCounterTest1() {
        long lineNum = LineCounter.countLine("normalLineTest.txt");
        assertEquals(5, lineNum);
    }

    @Test
    //测试空白行数
    public void lineCounterTest2() {
        long lineNum = LineCounter.countLine("emptyLineTest.txt");
        assertEquals(0, lineNum);
    }

    @Test
    //测试混合行数
    public void lineCounterTest3() {
        long lineNum = LineCounter.countLine("mixLineTest.txt");
        assertEquals(3, lineNum);
    }
}
