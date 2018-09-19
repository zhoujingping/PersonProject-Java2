import com.eventide.wordCount.service.WordsFrequencyCounter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class WordFrequencyCounterTest {
    @Test
    public void wordFrequencyCounterTest1() {
        ArrayList<HashMap.Entry<String, Long>> wordList =
                WordsFrequencyCounter.topTenFrequentWords(
                        WordsFrequencyCounter.countWordsFrequency("wordFrequencyCounterTest.txt"));
        HashMap<String, Long> map = new HashMap<String, Long>();
        map.put("file123", 1L);
        map.put("aaaa", 2L);
        ArrayList<HashMap.Entry<String, Long>> testList = new ArrayList<HashMap.Entry<String, Long>>(map.entrySet());
        assertEquals(testList, wordList);
    }
}
