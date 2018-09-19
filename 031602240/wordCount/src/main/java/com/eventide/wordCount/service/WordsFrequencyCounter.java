package com.eventide.wordCount.service;

import java.io.*;
import java.util.*;

/**
 * 词频计算器，包括计算文件中各单词词频，只输出频率最高的10个.
 * 频率相同的单词，优先输出字典序靠前的单词.
 *
 * @author xyy
 * @version 1.0 2018/9/12
 * @since 2018/9/11
 */
public class WordsFrequencyCounter {
    /**
     * 读取并计算文件词频.
     *
     * @param fileName 文件名
     * @return 各单词词频
     */
    public static HashMap<String, Long> countWordsFrequency(String fileName) {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String in = null;
        String regex = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
        String delim = " ,.!?-=*/()[]{}\\\"\\';:\\n\\r\\t“”‘’·——…（）【】｛｝\\0";
        String word = "";
        HashMap<String, Long> wordMap = new HashMap<String, Long>(16);

        //读入文件
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("找不到此文件");
            e.printStackTrace();
        }
        if (inputStreamReader != null) {
            bufferedReader = new BufferedReader(inputStreamReader);
        }
        //计算单词词频
        try {
            while ((in = bufferedReader.readLine()) != null) {
                in = in.toLowerCase();
                //根据分隔符分割
                StringTokenizer tokenizer = new StringTokenizer(in, delim);
                while (tokenizer.hasMoreTokens()) {
                    word = tokenizer.nextToken();
                    //匹配单词
                    if (word.matches(regex)) {
                        if (wordMap.get(word) != null) {
                            wordMap.put(word, wordMap.get(word) + 1);
                        } else {
                            wordMap.put(word, 1L);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wordMap;
    }

    /**
     * 求频率最高的10个单词
     *
     * @param wordMap 各单词词频
     * @return 频率最高的10个单词
     */
    public static ArrayList<HashMap.Entry<String, Long>> topTenFrequentWords(HashMap<String, Long> wordMap) {
        ArrayList<HashMap.Entry<String, Long>> wordList =
                new ArrayList<HashMap.Entry<String, Long>>(wordMap.entrySet());
        Collections.sort(wordList, new Comparator<HashMap.Entry<String, Long>>() {
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                if (o1.getValue() < o2.getValue()) {
                    return 1;
                } else {
                    if (o1.getValue().equals(o2.getValue())) {
                        if (o1.getKey().compareTo(o2.getKey()) > 0) {
                            return 1;
                        } else {
                            return -1;
                        }
                    } else {
                        return -1;
                    }
                }
            }
        });
        return wordList;
    }
}
