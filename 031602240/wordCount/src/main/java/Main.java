import com.eventide.wordCount.service.CharCounter;
import com.eventide.wordCount.service.LineCounter;
import com.eventide.wordCount.service.WordCounter;
import com.eventide.wordCount.service.WordsFrequencyCounter;
import com.eventide.wordCount.tool.FilePrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;

/**
 * 主函数类，包括提交计数任务、打印结果.
 *
 * @author xyy
 * @version 1.0 2018/9/12
 * @since 2018/9/11
 */
public class Main {
    public static void main(final String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        //计算字符数
        Future<Long> futureChar = executor.submit(new Callable<Long>() {
            public Long call() {
                return CharCounter.countChar(args[0]);
            }
        });

        //计算单词数
        Future<Long> futureWord = executor.submit(new Callable<Long>() {
            public Long call() {
                return WordCounter.countWord(args[0]);
            }
        });

        //计算行数
        Future<Long> futureLine = executor.submit(new Callable<Long>() {
            public Long call() {
                return LineCounter.countLine(args[0]);
            }
        });

        //计算单词词频
        Future<ArrayList<HashMap.Entry<String, Long>>> futureWordFrequnency = executor.submit(
                new Callable<ArrayList<HashMap.Entry<String, Long>>>() {
                    public ArrayList<HashMap.Entry<String, Long>> call() {
                        return WordsFrequencyCounter.topTenFrequentWords(
                                WordsFrequencyCounter.countWordsFrequency(args[0]));
                    }
                });

        //输出至文件
        try {
            FilePrinter.printToFile("result.txt",
                    futureChar.get(), futureWord.get(), futureLine.get(), futureWordFrequnency.get());
            executor.shutdown();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
